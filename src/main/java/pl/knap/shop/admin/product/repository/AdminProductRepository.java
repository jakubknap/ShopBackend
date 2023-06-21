package pl.knap.shop.admin.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.admin.product.model.AdminProduct;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}