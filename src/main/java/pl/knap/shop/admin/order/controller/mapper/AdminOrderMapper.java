package pl.knap.shop.admin.order.controller.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import pl.knap.shop.admin.order.controller.dto.AdminOrderDto;
import pl.knap.shop.admin.order.model.AdminOrder;
import pl.knap.shop.common.model.OrderStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminOrderMapper {
    public static Page<AdminOrderDto> mapToPageDtos(Page<AdminOrder> orders) {
        return new PageImpl<AdminOrderDto>(mapToDtoList(orders.getContent()), orders.getPageable(), orders.getTotalElements());
    }

    public static Map<String, String> createOrderStatusesMap() {
        HashMap<String, String> statuses = new HashMap<>();
        for (OrderStatus value : OrderStatus.values()) {
            statuses.put(value.name(), value.getValue());
        }
        return statuses;
    }

    private static List<AdminOrderDto> mapToDtoList(List<AdminOrder> content) {
        return content.stream()
                .map(AdminOrderMapper::mapToAdminOrderDto)
                .toList();
    }

    private static AdminOrderDto mapToAdminOrderDto(AdminOrder adminOrder) {
        return AdminOrderDto.builder()
                .id(adminOrder.getId())
                .orderStatus(adminOrder.getOrderStatus())
                .placeDate(adminOrder.getPlaceDate())
                .grossValue(adminOrder.getGrossValue())
                .build();
    }
}