package pl.knap.shop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}