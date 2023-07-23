package pl.knap.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.knap.shop.admin.order.model.AdminOrder;
import pl.knap.shop.admin.order.model.AdminOrderStatus;
import pl.knap.shop.admin.order.repository.AdminOrderRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final AdminOrderRepository orderRepository;

    public Page<AdminOrder> getOrders(Pageable pageable) {
        return orderRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        Sort.by("id")
                            .descending())
                                      );
    }

    public AdminOrder getOrder(Long id) {
        return orderRepository.findById(id)
                              .orElseThrow();
    }

    @Transactional
    public void patchOrder(Long id, Map<String, String> values) {
        AdminOrder adminOrder = orderRepository.findById(id).orElseThrow();
        pathValues(adminOrder, values);
    }

    private void pathValues(AdminOrder adminOrder, Map<String, String> values) {
        if(values.get("orderStatus") != null){
            adminOrder.setOrderStatus(AdminOrderStatus.valueOf(values.get("orderStatus")));
        }
    }
}