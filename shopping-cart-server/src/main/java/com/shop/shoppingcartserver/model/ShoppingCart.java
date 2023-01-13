package com.shop.shoppingcartserver.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart")
@Getter
@Setter
@RequiredArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Embedded
    private ShoppingCartLineItems cartLineItems;

    @Column(name = "count_items")
    private int count = 0;

    @Column(name = "total")
    private Double total = 0d;

    public void clear() {
        this.count = 0;
        this.total = 0d;
        cartLineItems.getLineItems().clear();
    }
}
