package pl.knap.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import pl.knap.shop.admin.order.model.AdminOrder;
import pl.knap.shop.admin.order.repository.AdminOrderRepository;
import pl.knap.shop.common.model.OrderStatus;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminExportService {

    private final AdminOrderRepository orderRepository;

    private static final CSVFormat FORMAT = CSVFormat.Builder
            .create(CSVFormat.DEFAULT)
            .setHeader("Id", "PlaceDate", "OrderStatus", "GrossValue", "Firstname",
                    "Lastname", "Street", "Zipcode", "City",
                    "Email", "Phone", "Payment")
            .build();

    public List<AdminOrder> exportOrders(LocalDateTime from, LocalDateTime to, OrderStatus orderStatus) {
        return orderRepository.findAllByPlaceDateIsBetweenAndOrderStatus(from, to, orderStatus);
    }

    public static ByteArrayInputStream transformToCsv(List<AdminOrder> adminOrders) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream();
             CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {
            for (AdminOrder order : adminOrders) {
                printer.printRecord(Arrays.asList(
                        order.getId(),
                        order.getPlaceDate(),
                        order.getOrderStatus()
                                .getValue(),
                        order.getGrossValue(),
                        order.getFirstname(),
                        order.getLastname(),
                        order.getStreet(),
                        order.getZipcode(),
                        order.getCity(),
                        order.getEmail(),
                        order.getPhone(),
                        order.getPayment()
                                .getName()
                ));
            }
            printer.flush();
            return new ByteArrayInputStream(stream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Błąd przetwarzania CSV: " + e.getMessage());
        }
    }
}