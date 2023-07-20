package pl.knap.shop.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.knap.shop.order.model.InitOrder;
import pl.knap.shop.order.model.dto.OrderDto;
import pl.knap.shop.order.model.dto.OrderSummary;
import pl.knap.shop.order.service.OrderService;
import pl.knap.shop.order.service.PaymentService;
import pl.knap.shop.order.service.ShipmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final ShipmentService shipmentService;
    private final PaymentService paymentService;

    @PostMapping
    public OrderSummary placeOrder(@RequestBody @Valid OrderDto orderDto) {
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/initData")
    public InitOrder initData() {
        return InitOrder.builder()
                        .shipments(shipmentService.getShipments())
                        .payments(paymentService.getPayments())
                        .build();
    }
}