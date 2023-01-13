package com.shop.orderserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItem {

    private long sku;

    private String name;

    private String imageLink;

    private Double price;

    private int quantity;

}
