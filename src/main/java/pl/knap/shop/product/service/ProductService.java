package pl.knap.shop.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.knap.shop.common.model.Product;
import pl.knap.shop.common.model.Review;
import pl.knap.shop.common.repository.ProductRepository;
import pl.knap.shop.common.repository.ReviewRepository;
import pl.knap.shop.product.service.dto.ProductDto;

import java.util.List;

import static pl.knap.shop.product.service.mapper.ProductDtoMapper.mapToProductDto;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public ProductDto getProduct(String slug) {
        Product product = productRepository.findBySlug(slug).orElseThrow();
        List<Review> reviews = reviewRepository.findAllByProductIdAndModerated(product.getId(), true);
        return mapToProductDto(product, reviews);
    }
}