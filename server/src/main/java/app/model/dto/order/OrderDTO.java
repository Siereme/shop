package app.model.dto.order;

import app.model.order.payment.Payment;
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
    Payment payment;

}
