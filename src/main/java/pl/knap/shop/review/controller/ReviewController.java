package pl.knap.shop.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.knap.shop.common.model.Review;
import pl.knap.shop.review.controller.dto.ReviewDto;
import pl.knap.shop.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Review addReview(@RequestBody @Valid ReviewDto reviewDto) {
        return reviewService.addReview(mapToReview(reviewDto));
    }

    private Review mapToReview(ReviewDto reviewDto) {
        return Review.builder()
                     .authorName(cleanContent(reviewDto.authorName()))
                     .productId(reviewDto.productId())
                     .content(cleanContent(reviewDto.content()))
                     .build();
    }

    private String cleanContent(String text) {
        return Jsoup.clean(text, Safelist.none());
    }
}