package pl.knap.shop.admin.product.controller.mapper;

import pl.knap.shop.admin.product.controller.dto.AdminProductDto;
import pl.knap.shop.admin.product.model.AdminProduct;

import static pl.knap.shop.admin.common.utils.SlugifyUtils.slugifySlug;

public class AdminProductMapper {
    public static AdminProduct mapAdminProduct(AdminProductDto adminProductDto, Long id) {
        return AdminProduct.builder()
                .id(id)
                .name(adminProductDto.getName())
                .description(adminProductDto.getDescription())
                .fullDescription(adminProductDto.getFullDescription())
                .categoryId(adminProductDto.getCategoryId())
                .price(adminProductDto.getPrice())
                .salePrice(adminProductDto.getSalePrice())
                .currency(adminProductDto.getCurrency())
                .image(adminProductDto.getImage())
                .slug(slugifySlug(adminProductDto.getSlug()))
                .build();
    }
}