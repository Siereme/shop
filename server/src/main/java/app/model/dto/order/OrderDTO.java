package app.model.dto.order;

import app.model.order.receipt.receiptDetail.ReceiptDetail;
import app.model.order.payment.Payment;
import app.model.order.receipt.Receipt;
import app.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {

    @Valid
    User user;
    @Valid
    Payment payment;
    @Valid
    ReceiptDetail receiptDetail;
    
}
