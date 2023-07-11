package pl.knap.shop.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.knap.shop.common.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}