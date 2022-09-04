package app.model.dto.order;

import app.model.order.Order;
import app.model.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderResponse {

    String token;
    User user;
    Order order;

    public OrderResponse(Order order) {
        this.order = order;
    }

    public OrderResponse(String token, User user, Order order) {
        this.token = token;
        this.user = user;
        this.order = order;
    }

}
