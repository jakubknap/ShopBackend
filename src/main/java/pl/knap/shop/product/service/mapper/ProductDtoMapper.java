package pl.knap.shop.product.service.mapper;

import org.springframework.transaction.annotation.Transactional;
import pl.knap.shop.common.model.Product;
import pl.knap.shop.common.model.Review;
import pl.knap.shop.product.service.dto.ProductDto;
import pl.knap.shop.product.service.dto.ReviewDto;

import java.util.List;

public class ProductDtoMapper {
    @Transactional(readOnly = true)
    public static ProductDto mapToProductDto(Product product, List<Review> reviews) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .description(product.getDescription())
                .fullDescription(product.getFullDescription())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(product.getSlug())
                .reviews(maptoReviewDtoList(reviews))
                .build();
    }

    private static List<ReviewDto> maptoReviewDtoList(List<Review> reviews) {
        return reviews.stream()
                .map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .productId(review.getProductId())
                        .authorName(review.getAuthorName())
                        .content(review.getContent())
                        .moderate(review.isModerated())
                        .build())
                .toList();
    }
}
