package pl.knap.shop.admin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.admin.order.model.AdminOrder;
import pl.knap.shop.common.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {
    List<AdminOrder> findAllByPlaceDateIsBetweenAndOrderStatus(LocalDateTime from, LocalDateTime to, OrderStatus orderStatus);
}