package pl.knap.shop.admin.category.controller;

import com.github.slugify.Slugify;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.knap.shop.admin.category.controller.dto.AdminCategoryDto;
import pl.knap.shop.admin.category.model.AdminCategory;
import pl.knap.shop.admin.category.service.AdminCategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    public static final Long EMPTY_ID = null;
    private final AdminCategoryService categoryService;

    @GetMapping
    public List<AdminCategory> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public AdminCategory getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    public AdminCategory createCategory(@RequestBody @Valid AdminCategoryDto adminCategoryDto) {
        return categoryService.createCategory(mapToAdminCategory(adminCategoryDto, EMPTY_ID));
    }

    @PutMapping("/{id}")
    public AdminCategory updateCategory(@PathVariable Long id, @RequestBody @Valid AdminCategoryDto adminCategoryDto) {
        return categoryService.updateCategory(mapToAdminCategory(adminCategoryDto, id));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    private AdminCategory mapToAdminCategory(AdminCategoryDto adminCategoryDto, Long id) {
        return AdminCategory.builder()
                .id(id)
                .name(adminCategoryDto.getName())
                .description(adminCategoryDto.getDescription())
                .slug(slugifyCategoryName(adminCategoryDto.getSlug()))
                .build();
    }

    private String slugifyCategoryName(String slug) {
        Slugify slugify = new Slugify();
        return slugify.withCustomReplacement("_", "-").slugify(slug);
    }
}