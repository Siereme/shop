package app.model.order.receipt.receiptDetail;

import app.model.order.address.Address;
import app.model.order.receipt.Receipt;

import java.sql.Date;

public interface IReceiptDetail {
    Long getId();

    Receipt getReceipt();

    Address getAddress();

    Date getDate();

    void setId(Long id);

    void setReceipt(Receipt receipt);

    void setAddress(Address address);

    void setDate(Date date);
}
