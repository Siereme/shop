package com.shop.orderserver.model.receipt;

public interface IReceipt {
    Long getId();

    String getType();

    void setId(Long id);

    void setType(String type);
}
