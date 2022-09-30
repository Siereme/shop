package app.model.order;

import app.model.order.receipt.receiptDetail.ReceiptDetail;
import app.model.order.payment.Payment;
import app.model.order.receipt.Receipt;
import app.model.order.status.OrderStatus;
import app.model.order.userDetails.OrderUserDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@RequiredArgsConstructor
public class Order implements IOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private OrderUserDetails userDetails;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private Set<OrderProductItem> orderItems = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    private ReceiptDetail receiptDetail;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private OrderStatus status;

    @Column(name = "total")
    private Double total;

    @Override
    public void setOrderProduct(OrderProductItem orderProductItem) {
        this.orderItems.add(orderProductItem);
    }
}
