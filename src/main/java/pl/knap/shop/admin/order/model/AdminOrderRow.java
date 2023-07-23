package pl.knap.shop.admin.order.model;

import jakarta.persistence.*;
import lombok.Getter;
import pl.knap.shop.admin.product.model.AdminProduct;
import pl.knap.shop.order.model.Shipment;

import java.math.BigDecimal;

@Entity
@Table(name = "order_row")
@Getter
public class AdminOrderRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    @OneToOne
    @JoinColumn(name = "productId")
    private AdminProduct product;
    private int quantity;
    private BigDecimal price;
    @OneToOne
    @JoinColumn(name = "shipmentId")
    private Shipment shipment;
}