package pl.knap.shop.homepage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.knap.shop.homepage.controller.dto.HomePageDto;
import pl.knap.shop.homepage.service.HomePageService;

@RestController
@RequiredArgsConstructor
public class HomePageController {

    private final HomePageService homePageService;

    @GetMapping("/homePage")
    public HomePageDto getHomePage() {
        return new HomePageDto(homePageService.getSaleProducts());
    }
}