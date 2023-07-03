package pl.knap.shop.admin.product.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import pl.knap.shop.admin.product.model.AdminProductCurrency;

import java.math.BigDecimal;

@Getter
public class AdminProductDto {
    @NotBlank
    @Length(min = 4)
    private String name;

    @NotBlank
    @Length(min = 4)
    private String category;

    @NotBlank
    @Length(min = 4)
    private String description;

    @NotNull
    @Min(0)
    private BigDecimal price;

    private AdminProductCurrency currency;
}