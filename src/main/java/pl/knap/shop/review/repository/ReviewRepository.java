package pl.knap.shop.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.review.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
