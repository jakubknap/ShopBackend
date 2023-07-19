package pl.knap.shop.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.knap.shop.cart.controller.dto.CartSummaryDto;
import pl.knap.shop.cart.controller.mapper.CartMapper;
import pl.knap.shop.cart.model.dto.CartProductDto;
import pl.knap.shop.cart.service.CartService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public CartSummaryDto getCart(@PathVariable Long id) {
        return CartMapper.mapToCartSummary(cartService.getCart(id));
    }

    @PutMapping("/{id}")
    public CartSummaryDto addProductToCart(@PathVariable Long id, @RequestBody CartProductDto cartProductDto) {
        return CartMapper.mapToCartSummary(cartService.addProductToCart(id, cartProductDto));
    }

    @PutMapping("/{id}/update")
    public CartSummaryDto updateCart(@PathVariable Long id, @RequestBody List<CartProductDto> cartProductDtos) {
        return CartMapper.mapToCartSummary(cartService.updateCart(id, cartProductDtos));
    }
}