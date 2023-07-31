package pl.knap.shop.homepage.controller.dto;

import pl.knap.shop.common.model.Product;

import java.util.List;

public record HomePageDto (List<Product> saleProducts) {
}