package com.shop.shoppingcartserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartLineItems {

    @ElementCollection
    @CollectionTable(name = "shopping_cart_line_items")
    private List<ShoppingCartLineItem> lineItems = new ArrayList<>();

}
