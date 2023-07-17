package pl.knap.shop.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}