package pl.knap.shop.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.knap.shop.review.model.Review;
import pl.knap.shop.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(Review review){
        return reviewRepository.save(review);
    }
}
