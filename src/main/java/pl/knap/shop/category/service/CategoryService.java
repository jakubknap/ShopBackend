package pl.knap.shop.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.knap.shop.category.model.Category;
import pl.knap.shop.category.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}