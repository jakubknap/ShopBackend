package pl.knap.shop.order.service.payment.p24;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import pl.knap.shop.order.model.Order;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentMethodP24 {

    private final PaymentMethodP24Config config;

    public String initPayment(Order order) {
        log.info("Inicjalizacja płatności");

        WebClient webClient = WebClient.builder()
                .filter(ExchangeFilterFunctions.basicAuthentication(config.getPosId()
                                .toString(),
                        config.isTestMode() ? config.getTestSecretKey() : config.getSecretKey()))
                .baseUrl(config.isTestMode() ? config.getTestApiUrl() : config.getApiUrl())
                .build();

        ResponseEntity<TransactionRegisterResponse> result = webClient.post().uri("/transaction/register")
                .bodyValue(TransactionRegisterRequest.builder()
                        .merchantId(config.getMerchantId())
                        .posId(config.getPosId())
                        .sessionId(createSessionId(order))
                        .amount(order.getGrossValue().movePointRight(2).intValue())
                        .currency("PLN")
                        .description("Zamówienie id: " + order.getId())
                        .email(order.getEmail())
                        .client(order.getFirstname() + " " + order.getLastname())
                        .country("PL")
                        .language("pl")
                        .urlReturn(config.isTestMode() ?
                                config.getTestApiUrl() :
                                config.getApiUrl())
                        .sign(createSign(order))
                        .encoding("UTF-8")
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> {
                            log.error("Coś poszło źle: " + clientResponse.statusCode().value());
                            return Mono.empty();
                        })
                .toEntity(TransactionRegisterResponse.class)
                .block();
        if (result != null && result.getBody() != null && result.getBody().getData() != null) {
            return (config.isTestMode() ? config.getTestUrl() : config.getUrl()) + "/trnRequest/" + result.getBody().getData().token();
        }
        return null;
    }

    private String createSign(Order order) {
        String json = "{\"sessionId\":\"" + createSessionId(order) +
                "\",\"merchantId\":" + config.getMerchantId() +
                ",\"amount\":" + order.getGrossValue().movePointRight(2).intValue() +
                ",\"currency\":\"PLN\",\"crc\":\"" + (config.isTestMode() ? config.getTestCrc() : config.getCrc()) + "\"}";
        return DigestUtils.sha384Hex(json);
    }

    private String createSessionId(Order order) {
        return "order_id_" + order.getId().toString();
    }
}