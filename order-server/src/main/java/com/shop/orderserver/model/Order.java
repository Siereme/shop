package com.shop.orderserver.model;

import com.shop.orderserver.model.payment.Payment;
import com.shop.orderserver.model.receipt.receiptDetail.ReceiptDetail;
import com.shop.orderserver.model.status.OrderStatus;
import com.shop.orderserver.model.userDetails.UserDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@RequiredArgsConstructor
public class Order implements IOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Embedded
    private UserDetails userDetails;

    @Embedded
    private ReceiptDetail receiptDetail;

    @Embedded
    private OrderLineItems orderLineItems;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private OrderStatus status;

    @Column(name = "total")
    private Double total;

    @Override
    public void setOrderProduct(OrderLineItem orderLineItem) {
        this.orderLineItems.getLineItems().add(orderLineItem);
    }
}
