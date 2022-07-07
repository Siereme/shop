package app.model.dto.order;

import app.model.order.Payment;
import app.model.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderDTO {

    User user;
    Payment payment;

}
