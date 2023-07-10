package pl.knap.shop.category.model;

import org.springframework.data.domain.Page;
import pl.knap.shop.product.model.Product;

public record CategoryProductsDto(Category category, Page<Product> products) {
}