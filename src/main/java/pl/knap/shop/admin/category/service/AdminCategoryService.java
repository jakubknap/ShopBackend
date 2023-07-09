package pl.knap.shop.admin.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.knap.shop.admin.category.model.AdminCategory;
import pl.knap.shop.admin.category.repository.AdminCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {
    private final AdminCategoryRepository categoryRepository;

    public List<AdminCategory> getCategories() {
        return categoryRepository.findAll();
    }

    public AdminCategory getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public AdminCategory createCategory(AdminCategory adminCategory) {
        return categoryRepository.save(adminCategory);
    }

    public AdminCategory updateCategory(AdminCategory adminCategory) {
        return categoryRepository.save(adminCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}