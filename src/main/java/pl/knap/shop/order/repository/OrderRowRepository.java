package pl.knap.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.order.model.OrderRow;

public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {
}