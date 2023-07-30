package pl.knap.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.order.model.OrderLog;

public interface OrderLogRepository extends JpaRepository<OrderLog, Long> {
}