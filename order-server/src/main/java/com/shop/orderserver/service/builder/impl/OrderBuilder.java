package com.shop.orderserver.service.builder.impl;

import com.shop.orderserver.dto.UserDTO;
import com.shop.orderserver.model.Order;
import com.shop.orderserver.model.OrderLineItem;
import com.shop.orderserver.model.OrderLineItems;
import com.shop.orderserver.model.payment.Payment;
import com.shop.orderserver.model.receipt.Receipt;
import com.shop.orderserver.model.receipt.receiptDetail.ReceiptDetail;
import com.shop.orderserver.model.status.OrderStatus;
import com.shop.orderserver.model.userDetails.UserDetails;
import com.shop.orderserver.repository.OrderStatusRepository;
import com.shop.orderserver.repository.PaymentRepository;
import com.shop.orderserver.repository.ReceiptRepository;
import com.shop.orderserver.service.builder.IOrderBuilder;
import com.shop.orderserver.utils.constant.OrderPayments;
import com.shop.orderserver.utils.constant.OrderStatuses;
import com.shop.orderserver.utils.constant.ServiceUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderBuilder implements IOrderBuilder<Order, UserDTO> {

    private final RestTemplate restTemplate;
    private final ReceiptRepository receiptRepo;
    private final PaymentRepository paymentRepo;
    private final OrderStatusRepository statusRepo;

    private Order order;

    @Override
    public void create() {
        this.order = new Order();
    }

    public void setCustomerId(long customerId) {
        this.order.setCustomerId(customerId);
    }

    @Override
    public void setUserDetails(UserDTO user) {
        UserDetails userDetails = new UserDetails();
        userDetails.setName(user.getName());
        userDetails.setPatronymic(user.getPatronymic());
        userDetails.setSurname(user.getSurname());
        userDetails.setEmail(user.getEmail());
        userDetails.setPhone(user.getPhone());
        this.order.setUserDetails(userDetails);
    }


    @Override
    public void setReceiptDetail(ReceiptDetail receiptDetailDTO) {
        Receipt receipt = receiptRepo.findById(receiptDetailDTO.getReceipt().getId())
                .orElseThrow(() -> new EntityNotFoundException("Order constructor - Receipt doesn't exist"));

        ReceiptDetail receiptDetail = new ReceiptDetail();
        receiptDetail.setReceipt(receipt);
        receiptDetail.setAddress(receiptDetailDTO.getAddress());
        receiptDetail.setDate(receiptDetailDTO.getDate());
        this.order.setReceiptDetail(receiptDetail);
    }

    @Override
    public void setLineItems(List<OrderLineItem> lineItems) {
        OrderLineItems orderLineItems = new OrderLineItems(lineItems);
        this.order.setOrderLineItems(orderLineItems);
    }

    @Override
    public void setPayment(long paymentId) {
        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Order constructor - Payment is not found"));
        order.setPayment(payment);
    }

    @Override
    public void setStatus() {
        String statusType = Objects.equals(this.order.getPayment().getType(), OrderPayments.CASH.value)
                ? OrderStatuses.ACCEPT_PROCESS.value
                : OrderStatuses.AWAITING_PAYMENT.value;
        setStatus(statusType);
    }

    @Override
    public void setStatus(String statusType) {
        OrderStatus status = statusRepo.findByType(statusType)
                .orElseThrow(() -> new EntityNotFoundException("Order constructor - OrderStatus is not found"));
        this.order.setStatus(status);
    }

    @Override
    public void setTotal() {
        double total = this.order.getOrderLineItems().getLineItems()
                .stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        this.order.setTotal(total);
    }

    @Override
    public void clearShoppingCart(long customerId) {
        restTemplate.delete(ServiceUrl.CART_CLEAR + customerId);
    }

    @Override
    public Order getOrder() {
        return this.order;
    }

}
