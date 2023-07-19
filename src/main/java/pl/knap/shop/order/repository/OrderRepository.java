package pl.knap.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}