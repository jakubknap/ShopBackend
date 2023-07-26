package pl.knap.shop.order.service.mapper;

import pl.knap.shop.order.model.Order;
import pl.knap.shop.order.model.dto.OrderListDto;

import java.util.List;

public class OrderDtoMapper {

    public static List<OrderListDto> mapToOrderListDto(List<Order> ordersForCustomer) {
        return ordersForCustomer.stream()
                                .map(order -> new OrderListDto(
                                        order.getId(),
                                        order.getPlaceDate(),
                                        order.getOrderStatus().getValue(),
                                        order.getGrossValue()))
                                .toList();
    }
}