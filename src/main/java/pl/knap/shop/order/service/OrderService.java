package pl.knap.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.knap.shop.common.mail.EmailClientService;
import pl.knap.shop.common.model.Cart;
import pl.knap.shop.common.repository.CartItemRepository;
import pl.knap.shop.common.repository.CartRepository;
import pl.knap.shop.order.model.Order;
import pl.knap.shop.order.model.Payment;
import pl.knap.shop.order.model.Shipment;
import pl.knap.shop.order.model.dto.OrderDto;
import pl.knap.shop.order.model.dto.OrderListDto;
import pl.knap.shop.order.model.dto.OrderSummary;
import pl.knap.shop.order.repository.OrderRepository;
import pl.knap.shop.order.repository.OrderRowRepository;
import pl.knap.shop.order.repository.PaymentRepository;
import pl.knap.shop.order.repository.ShipmentRepository;

import java.util.List;

import static pl.knap.shop.order.service.mapper.OrderDtoMapper.mapToOrderListDto;
import static pl.knap.shop.order.service.mapper.OrderEmailMessageMapper.createEmailMessage;
import static pl.knap.shop.order.service.mapper.OrderMapper.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderRowRepository orderRowRepository;
    private final CartItemRepository cartItemRepository;
    private final ShipmentRepository shipmentRepository;
    private final PaymentRepository paymentRepository;
    private final EmailClientService emailClientService;

    @Transactional
    public OrderSummary placeOrder(OrderDto orderDto, Long userId) {
        Cart cart = cartRepository.findById(orderDto.getCartId())
                                  .orElseThrow();
        Shipment shipment = shipmentRepository.findById(orderDto.getShipmentId())
                                              .orElseThrow();
        Payment payment = paymentRepository.findById(orderDto.getPaymentId())
                                           .orElseThrow();
        Order newOrder = orderRepository.save(createNewOrder(orderDto, cart, shipment, payment, userId));
        saveOrderRows(cart, newOrder.getId(), shipment);
        clearOrderCart(orderDto);
        sendConfirmEmail(newOrder);
        return createOrderSummary(payment, newOrder);
    }

    private void sendConfirmEmail(Order newOrder) {
        emailClientService.getInstance()
                          .send(newOrder.getEmail(), "Twoje zamówienie zostało przyjęte", createEmailMessage(newOrder));
    }

    private void clearOrderCart(OrderDto orderDto) {
        cartItemRepository.deleteByCartId(orderDto.getCartId());
        cartRepository.deleteCartById(orderDto.getCartId());
    }

    private void saveOrderRows(Cart cart, Long orderId, Shipment shipment) {
        saveProductRows(cart, orderId);
        saveShipmentRow(orderId, shipment);
    }

    private void saveShipmentRow(Long orderId, Shipment shipment) {
        orderRowRepository.save(mapToOrderRowWithQuantity(orderId, shipment));
    }

    private void saveProductRows(Cart cart, Long orderId) {
        cart.getItems()
            .stream()
            .map(cartItem -> mapToOrderRow(orderId, cartItem))
            .peek(orderRowRepository::save)
            .toList();
    }

    public List<OrderListDto> getOrdersForCustomer(Long userId) {
        if(userId == null){
            throw new IllegalArgumentException("Brak użytkownika");
        }
        return mapToOrderListDto(orderRepository.findAllByUserId(userId));
    }
}