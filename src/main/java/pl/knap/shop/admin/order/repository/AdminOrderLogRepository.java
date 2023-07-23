package pl.knap.shop.admin.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.admin.order.model.AdminOrderLog;

public interface AdminOrderLogRepository extends JpaRepository<AdminOrderLog, Long> {
}