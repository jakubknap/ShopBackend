package pl.knap.shop.order.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.knap.shop.common.mail.EmailClientService;
import pl.knap.shop.common.mail.FakeEmailService;
import pl.knap.shop.common.model.Cart;
import pl.knap.shop.common.model.CartItem;
import pl.knap.shop.common.model.Product;
import pl.knap.shop.common.repository.CartItemRepository;
import pl.knap.shop.common.repository.CartRepository;
import pl.knap.shop.order.model.Order;
import pl.knap.shop.order.model.Payment;
import pl.knap.shop.order.model.Shipment;
import pl.knap.shop.order.model.dto.OrderDto;
import pl.knap.shop.order.model.dto.OrderSummary;
import pl.knap.shop.order.repository.OrderRepository;
import pl.knap.shop.order.repository.OrderRowRepository;
import pl.knap.shop.order.repository.PaymentRepository;
import pl.knap.shop.order.repository.ShipmentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.knap.shop.order.model.OrderStatus.NEW;
import static pl.knap.shop.order.model.PaymentType.BANK_TRANSFER;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private CartRepository cartRepository;
    @Mock
    private ShipmentRepository shipmentRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderRowRepository orderRowRepository;
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private EmailClientService emailClientService;
    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldPlaceOrder() {
        //given
        OrderDto orderDto = createOrderDto();
        when(cartRepository.findById(any())).thenReturn(createCart());
        when(shipmentRepository.findById(any())).thenReturn(createShipment());
        when(paymentRepository.findById(any())).thenReturn(createPayment());
        when(orderRepository.save(any())).thenAnswer(invocation -> {
            Order order = (Order) invocation.getArguments()[0];
            order.setId(1L);
            return order;
        });
        when(emailClientService.getInstance()).thenReturn(new FakeEmailService());
        //when
        OrderSummary orderSummary = orderService.placeOrder(orderDto);
        //then
        assertThat(orderSummary).isNotNull();
        assertThat(orderSummary.getStatus()).isEqualTo(NEW);
        assertThat(orderSummary.getGrossValue()).isEqualTo(new BigDecimal("36.22"));
        assertThat(orderSummary.getPayment()
                               .getType()).isEqualTo(BANK_TRANSFER);
        assertThat(orderSummary.getPayment()
                               .getName()).isEqualTo("test payment");
        assertThat(orderSummary.getPayment()
                               .getId()).isEqualTo(1L);
    }

    private Optional<Payment> createPayment() {
        return Optional.of(Payment.builder()
                                  .id(1L)
                                  .name("test payment")
                                  .type(BANK_TRANSFER)
                                  .defaultPayment(true)
                                  .build());
    }

    private Optional<Shipment> createShipment() {
        return Optional.of(Shipment.builder()
                                   .id(2L)
                                   .price(new BigDecimal("14.00"))
                                   .build());
    }

    private Optional<Cart> createCart() {
        return Optional.of(Cart.builder()
                               .id(1L)
                               .created(LocalDateTime.now())
                               .items(createItems())
                               .build());
    }

    private List<CartItem> createItems() {
        return List.of(CartItem.builder()
                               .id(1L)
                               .cartId(1L)
                               .quantity(1)
                               .product(Product.builder()
                                               .id(1L)
                                               .price(new BigDecimal("11.11"))
                                               .build())
                               .build(),
                       CartItem.builder()
                               .id(2L)
                               .cartId(1L)
                               .quantity(1)
                               .product(Product.builder()
                                               .id(2L)
                                               .price(new BigDecimal("11.11"))
                                               .build())
                               .build());
    }

    private OrderDto createOrderDto() {
        return OrderDto.builder()
                       .firstname("firstname")
                       .lastname("lastname")
                       .street("street")
                       .zipcode("zipcode")
                       .city("city")
                       .email("email")
                       .phone("phone")
                       .cartId(1L)
                       .shipmentId(1L)
                       .paymentId(1L)
                       .build();
    }
}