package pl.knap.shop.admin.order.service;

import pl.knap.shop.admin.order.model.AdminOrderStatus;

public class AdminOrderEmailMessage {
    public static String createProcessingEmailMessage(Long id, AdminOrderStatus status) {
        return "Twoje zamówienie numer: " + id + " jest przetwarzane." +
               "\nStatus twojego zamówienia został zmieniony na: " + status.getValue() +
               "\nTwoje zamówienie jest przetwarzane przez naszych pracowników." +
               "\nPo skompletowaniu niezwłocznie przekażemy je do wysyłki." +
               "\n\nPozdrawiamy" +
               "\nSklep Shop";
    }

    public static String createCompletedEmailMessage(Long id, AdminOrderStatus status) {
        return "Twoje zamówienie numer: " + id + " zostało zrealizowane." +
               "\nStatus twojego zamówienia został zmieniony na: " + status.getValue() +
               "\n\nDziękujemy za zakupy i zapraszamy ponownie." +
               "\n\nPozdrawiamy" +
               "\nSklep Shop";
    }

    public static String createRefundEmailMessage(Long id, AdminOrderStatus status) {
        return "Twoje zamówienie numer: " + id + " zostało zwrócone." +
               "\nStatus twojego zamówienia został zmieniony na: " + status.getValue() +
               "\n\nPozdrawiamy" +
               "\nSklep Shop";
    }
}