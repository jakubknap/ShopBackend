package pl.knap.shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.order.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}