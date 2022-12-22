package com.shop.orderserver.model.receipt.receiptDetail;

import com.shop.orderserver.model.address.Address;
import com.shop.orderserver.model.receipt.Receipt;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
public class ReceiptDetail implements IReceiptDetail {

    @Valid
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    private Receipt receipt;

    @Valid
    @Embedded
    private Address address;

    @Future
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Getter(onMethod_ = @NotNull(message = "Выберите дату"))
    @Column(name = "receipt_date")
    private Date date;

}
