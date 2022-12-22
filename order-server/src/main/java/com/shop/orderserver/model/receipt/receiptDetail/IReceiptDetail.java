package com.shop.orderserver.model.receipt.receiptDetail;

import com.shop.orderserver.model.address.Address;
import com.shop.orderserver.model.receipt.Receipt;

import java.sql.Date;

public interface IReceiptDetail {

    Receipt getReceipt();

    Address getAddress();

    Date getDate();

    void setReceipt(Receipt receipt);

    void setAddress(Address address);

    void setDate(Date date);
}
