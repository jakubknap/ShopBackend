package pl.knap.shop.order.service.mapper;

import pl.knap.shop.order.model.Order;

import java.time.format.DateTimeFormatter;

public class OrderEmailMessageMapper {
    public static String createEmailMessage(Order newOrder) {
        return "Twoje zamówienie o id: " + newOrder.getId() +
               "\nData złożenia: " + newOrder.getPlaceDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
               "\nWartość: " + newOrder.getGrossValue() + " PLN " +
               "\n\n" +
               "\nPłatność: " + newOrder.getPayment().getName() +
               (newOrder.getPayment().getNote() != null ? "\n" + newOrder.getPayment().getNote() : "") +
               "\n\nDziękujemy za zakupy.";
    }
}