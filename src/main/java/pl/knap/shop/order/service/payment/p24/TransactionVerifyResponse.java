package pl.knap.shop.order.service.payment.p24;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionVerifyResponse {
    private Data data;

    record Data (String status) {}
}
