package pl.knap.shop.product.controller;

import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.knap.shop.common.dto.ProductListDto;
import pl.knap.shop.common.model.Product;
import pl.knap.shop.product.service.ProductService;
import pl.knap.shop.product.service.dto.ProductDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductListDto> getProducts(Pageable pageable) {
        Page<Product> products = productService.getProducts(pageable);
        List<ProductListDto> productListDtos = getProductListDtos(products);
        return new PageImpl<>(productListDtos, pageable, products.getTotalElements());
    }

    @GetMapping("/{slug}")
    public ProductDto getProduct(@PathVariable @Pattern(regexp = "[a-z0-9\\-]+") @Length(max = 255) String slug) {
        return productService.getProduct(slug);
    }

    private List<ProductListDto> getProductListDtos(Page<Product> products) {
        return products.getContent().stream().map(product -> ProductListDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .currency(product.getCurrency())
                        .image(product.getImage())
                        .slug(product.getSlug())
                        .build())
                .toList();
    }
}