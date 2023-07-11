package pl.knap.shop.category.dto;

import org.springframework.data.domain.Page;
import pl.knap.shop.common.dto.ProductListDto;
import pl.knap.shop.common.model.Category;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}