package pl.knap.shop.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.knap.shop.order.model.Payment;
import pl.knap.shop.order.model.Shipment;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitOrder {
    private List<Shipment> shipments;
    private List<Payment> payments;
}