package com.shop.orderserver.dto;

import com.shop.orderserver.model.OrderLineItem;
import com.shop.orderserver.model.payment.Payment;
import com.shop.orderserver.model.receipt.receiptDetail.ReceiptDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {

    @Valid
    UserDTO user;
    @Valid
    Payment payment;
    @Valid
    ReceiptDetail receiptDetail;

    @Valid
    List<OrderLineItem> lineItems;

}
