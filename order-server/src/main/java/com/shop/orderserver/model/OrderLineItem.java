package com.shop.orderserver.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItem {

    private long article;

    private String name;

    private String imageLink;

    private Double price;

    private int quantity;

}
