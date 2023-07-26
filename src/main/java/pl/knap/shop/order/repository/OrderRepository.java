package pl.knap.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.order.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}