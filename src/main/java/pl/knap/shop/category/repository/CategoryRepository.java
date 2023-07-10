package pl.knap.shop.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import pl.knap.shop.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findBySlug(@PathVariable("slug") String slug);
}