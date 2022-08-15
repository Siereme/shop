package app.model.dto.order;

import app.model.order.payment.Payment;
import app.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {

    User user;
    Payment payment;

}
