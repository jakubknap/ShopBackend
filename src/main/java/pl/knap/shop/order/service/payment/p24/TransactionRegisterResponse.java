package pl.knap.shop.order.service.payment.p24;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRegisterResponse {
    private Data data;

    record Data(String token) {}
}