package pl.knap.shop.admin.category.controller.mapper;

import pl.knap.shop.admin.category.controller.dto.AdminCategoryDto;
import pl.knap.shop.admin.category.model.AdminCategory;

import static pl.knap.shop.admin.common.utils.SlugifyUtils.slugifySlug;

public class AdminCategoryMapper {
    public static AdminCategory mapToAdminCategory(AdminCategoryDto adminCategoryDto, Long id) {
        return AdminCategory.builder()
                .id(id)
                .name(adminCategoryDto.getName())
                .description(adminCategoryDto.getDescription())
                .slug(slugifySlug(adminCategoryDto.getSlug()))
                .build();
    }
}