package pl.knap.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.knap.shop.admin.order.model.AdminOrder;
import pl.knap.shop.admin.order.model.AdminOrderStatus;
import pl.knap.shop.common.mail.EmailClientService;

import static pl.knap.shop.admin.order.service.AdminOrderEmailMessage.*;

@Service
@RequiredArgsConstructor
class EmailNotificationForStatusChange {

    private final EmailClientService emailClientService;

    void sendEmailNotification(AdminOrderStatus newStatus, AdminOrder adminOrder) {
        if (newStatus == AdminOrderStatus.PROCESSING) {
            sendEmail(adminOrder.getEmail(), "Zamówienie numer: " + adminOrder.getId() + " zmieniło status na: " + newStatus.getValue(), createProcessingEmailMessage(adminOrder.getId(),
                                                                                                                                                     newStatus));
        }
        else if (newStatus == AdminOrderStatus.COMPLETED) {
            sendEmail(adminOrder.getEmail(), "Zamówienie numer:" + adminOrder.getId() + " zmieniło status na: " + newStatus.getValue(), createCompletedEmailMessage(adminOrder.getId(),
                                                                                                                                                     newStatus));
        }
        else if (newStatus == AdminOrderStatus.REFUND) {
            sendEmail(adminOrder.getEmail(), "Zamówienie numer:" + adminOrder.getId() + " zmieniło status na: " + newStatus.getValue(), createRefundEmailMessage(adminOrder.getId(),
                                                                                                                                                     newStatus));
        }
    }

    private void sendEmail(String email, String subject, String message) {
        emailClientService.getInstance()
                          .send(email, subject, message);
    }
}