package pl.knap.shop.homepage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.knap.shop.common.model.Product;
import pl.knap.shop.common.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomePageService {

    private final ProductRepository productRepository;

    public List<Product> getSaleProducts() {
        return productRepository.findTop10AllBySalePriceIsNotNull();
    }
}