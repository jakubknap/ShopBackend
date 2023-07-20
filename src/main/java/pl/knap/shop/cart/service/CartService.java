package pl.knap.shop.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.knap.shop.cart.model.dto.CartProductDto;
import pl.knap.shop.common.model.Cart;
import pl.knap.shop.common.model.CartItem;
import pl.knap.shop.common.model.Product;
import pl.knap.shop.common.repository.CartRepository;
import pl.knap.shop.common.repository.ProductRepository;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Cart addProductToCart(Long id, CartProductDto cartProductDto) {
        Cart cart = getInitializedCart(id);
        cart.addProduct(CartItem.builder()
                .cartId(cart.getId())
                .quantity(cartProductDto.quantity())
                .product(getProduct(cartProductDto.productId())).build());
        return cart;
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    private Cart getInitializedCart(Long id) {
        if (id == null || id <= 0) {
            return saveNewCart();
        }
        return cartRepository.findById(id).orElseGet(this::saveNewCart);
    }

    @Transactional
    public Cart updateCart(Long id, List<CartProductDto> cartProductDtos) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.getItems().forEach(cartItem -> {
            cartProductDtos.stream().filter(cartProductDto -> cartItem.getProduct().getId().equals(cartProductDto.productId()))
                    .findFirst()
                    .ifPresent(cartProductDto -> cartItem.setQuantity(cartProductDto.quantity()));
        });
        return cart;
    }

    private Cart saveNewCart() {
        return cartRepository.save(Cart.builder().created(now()).build());
    }
}