package pl.knap.shop.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.knap.shop.order.model.dto.OrderDto;
import pl.knap.shop.order.model.dto.OrderSummary;
import pl.knap.shop.order.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public OrderSummary placeOrder(@RequestBody @Valid OrderDto orderDto) {
        return orderService.placeOrder(orderDto);
    }
}