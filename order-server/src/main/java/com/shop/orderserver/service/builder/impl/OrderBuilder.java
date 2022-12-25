package com.shop.orderserver.service.builder.impl;

import com.shop.orderserver.dto.LineItemDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderBuilder implements IOrderBuilder<Order, UserDTO> {

    private final RestTemplate restTemplate;
    private final ReceiptRepository receiptRepo;
    private final PaymentRepository paymentRepo;
    private final OrderStatusRepository statusRepo;


    @Override
    public UserDetails getUserDetails(UserDTO user) {
        UserDetails userDetails = new UserDetails();
        userDetails.setName(user.getName());
        userDetails.setPatronymic(user.getPatronymic());
        userDetails.setSurname(user.getSurname());
        userDetails.setEmail(user.getEmail());
        userDetails.setPhone(user.getPhone());
        return userDetails;
    }


    @Override
    public ReceiptDetail getReceiptDetail(ReceiptDetail receiptDetailDTO) {
        Receipt receipt = receiptRepo.findById(receiptDetailDTO.getReceipt().getId())
                .orElseThrow(() -> new EntityNotFoundException("Order constructor - Receipt doesn't exist"));

        ReceiptDetail receiptDetail = new ReceiptDetail();
        receiptDetail.setReceipt(receipt);
        receiptDetail.setAddress(receiptDetailDTO.getAddress());
        receiptDetail.setDate(receiptDetailDTO.getDate());
        return receiptDetail;
    }

    @Override
    public OrderLineItems getLineItems(List<LineItemDTO> lineItemsDTO) {
        List<Long> lineItemsIds = lineItemsDTO.stream().map(LineItemDTO::getSku).collect(Collectors.toList());
        OrderLineItem[] lineItemsArray = restTemplate.postForObject(ServiceUrl.CATALOG_PRODUCT_SKUS,
                lineItemsIds, OrderLineItem[].class);

        List<OrderLineItem> lineItems = Arrays.stream(Objects.requireNonNull(lineItemsArray))
                .peek(item -> {
                    int quantity = lineItemsDTO.stream()
                            .filter(itemDTO -> itemDTO.getSku() == item.getSku())
                            .findFirst().orElseGet(LineItemDTO::new).getQuantity();
                    item.setQuantity(quantity);
                }).collect(Collectors.toList());

        return new OrderLineItems(lineItems);
    }

    @Override
    public Payment getPayment(Payment payment) {
        return paymentRepo.findById(payment.getId())
                .orElseThrow(() -> new EntityNotFoundException("Order constructor - Payment is not found"));
    }

    @Override
    public OrderStatus getStatus(Payment payment) {
        OrderStatuses statusType = Objects.equals(payment.getType(), OrderPayments.CASH.value)
                ? OrderStatuses.ACCEPT_PROCESS
                : OrderStatuses.AWAITING_PAYMENT;
        return getStatus(statusType);
    }

    @Override
    public OrderStatus getStatus(OrderStatuses statusType) {
        return statusRepo.findByType(statusType.value)
                .orElseThrow(() -> new EntityNotFoundException("Order constructor - OrderStatus is not found"));
    }

    @Override
    public double getTotal(List<OrderLineItem> lineItems) {
        return lineItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    @Override
    public void clearShoppingCart(long customerId) {
        restTemplate.delete(ServiceUrl.CART_CLEAR + customerId);
    }

}
