package pl.knap.shop.category.service.mapper;

import org.springframework.data.domain.Page;
import pl.knap.shop.common.dto.ProductListDto;
import pl.knap.shop.common.model.Product;

import java.util.List;

public class ProductListMapper {
    public static List<ProductListDto> getProductListDtos(Page<Product> page) {
        return page.getContent()
                .stream()
                .map(product -> ProductListDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .salePrice(product.getSalePrice())
                        .currency(product.getCurrency())
                        .image(product.getImage())
                        .slug(product.getSlug())
                        .build())
                .toList();
    }
}
