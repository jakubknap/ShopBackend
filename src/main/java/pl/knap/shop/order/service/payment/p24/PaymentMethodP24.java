package pl.knap.shop.order.service.payment.p24;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.knap.shop.order.model.Order;
import pl.knap.shop.order.model.dto.NotificationReceiveDto;
import reactor.core.publisher.Mono;

import static pl.knap.shop.order.service.payment.p24.RequestUtil.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentMethodP24 {

    private final PaymentMethodP24Config config;
    private final WebClient p24Client;

    public String initPayment(Order order) {
        log.info("Inicjalizacja płatności");

        ResponseEntity<TransactionRegisterResponse> result = p24Client.post()
                                                                      .uri("/transaction/register")
                                                                      .bodyValue(createTransactionRegisterRequest(config, order))
                                                                      .retrieve()
                                                                      .onStatus(HttpStatusCode::is4xxClientError,
                                                                                clientResponse -> {
                                                                                    log.error("Coś poszło źle: " + clientResponse.statusCode().value());
                                                                                    return Mono.empty();
                                                                                })
                                                                      .toEntity(TransactionRegisterResponse.class)
                                                                      .block();
        if (result != null && result.getBody() != null && result.getBody()
                                                                .getData() != null) {
            return (config.isTestMode() ? config.getTestUrl() : config.getUrl()) + "/trnRequest/" + result.getBody()
                                                                                                          .getData()
                                                                                                          .token();
        }
        return null;
    }

    public String receiveNotification(Order order, NotificationReceiveDto receiveDto, String remoteAddr) {
        log.info(receiveDto.toString());
        validateIpAddress(remoteAddr, config);
        validate(receiveDto, order, config);
        return verifyPayment(receiveDto, order);
    }

    private String verifyPayment(NotificationReceiveDto receiveDto, Order order) {
        ResponseEntity<TransactionVerifyResponse> result = p24Client.put()
                                                                    .uri("/transaction/verify")
                                                                    .bodyValue(createTransactionVerifyRequest(config, order, receiveDto))
                                                                    .retrieve()
                                                                    .toEntity(TransactionVerifyResponse.class)
                                                                    .block();
        log.info("Weryfikacja transakcji status: " + result.getBody()
                                                           .getData()
                                                           .status());
        return result.getBody()
                     .getData()
                     .status();
    }
}