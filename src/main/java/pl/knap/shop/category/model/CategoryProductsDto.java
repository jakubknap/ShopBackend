package pl.knap.shop.category.model;

import org.springframework.data.domain.Page;
import pl.knap.shop.product.controller.dto.ProductListDto;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}