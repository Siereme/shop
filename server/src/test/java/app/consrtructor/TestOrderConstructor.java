package app.consrtructor;

import app.model.order.Order;
import app.model.order.OrderProductItem;
import app.model.order.payment.Payment;
import app.model.order.userDetails.OrderUserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class TestOrderConstructor {

    private final TestProductConstructor productConstructor = new TestProductConstructor();
    private final List<Order> orderList = new ArrayList<>();

    public TestOrderConstructor() {
        construct();
    }

    private void construct() {
        Order order1 = new Order();
        order1.setId(1L);

        OrderUserDetails userDetails1 = new OrderUserDetails();
        userDetails1.setId(1L);
        userDetails1.setName("User1");
        userDetails1.setPatronymic("User1");
        userDetails1.setSurname("User1");
        userDetails1.setEmail("userdetails1@mail.com");
        userDetails1.setPhone("+79999999999");
        order1.setUserDetails(userDetails1);

        OrderProductItem productItem11 = new OrderProductItem();
        productItem11.setId(1L);
        productItem11.setOrder(order1);
        productItem11.setProduct(productConstructor.getById(1L));
        productItem11.setCount(2);

        OrderProductItem productItem12 = new OrderProductItem();
        productItem12.setId(2L);
        productItem12.setOrder(order1);
        productItem12.setProduct(productConstructor.getById(3L));
        productItem12.setCount(8);

        order1.setOrderItems(Set.of(productItem11, productItem12));

        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setType("При получении");
        order1.setPayment(payment1);

        order1.setTotal(194500d);

        Order order2 = new Order();
        order2.setId(2L);

        OrderUserDetails userDetails2 = new OrderUserDetails();
        userDetails2.setId(2L);
        userDetails2.setName("User2");
        userDetails2.setPatronymic("User2");
        userDetails2.setSurname("User2");
        userDetails2.setEmail("userdetails2@mail.com");
        userDetails2.setPhone("+78888888888");
        order2.setUserDetails(userDetails2);

        OrderProductItem productItem21 = new OrderProductItem();
        productItem21.setId(3L);
        productItem21.setOrder(order2);
        productItem21.setProduct(productConstructor.getById(4L));
        productItem21.setCount(10);

        OrderProductItem productItem22 = new OrderProductItem();
        productItem22.setId(4L);
        productItem22.setOrder(order2);
        productItem22.setProduct(productConstructor.getById(5L));
        productItem22.setCount(3);

        order2.setOrderItems(Set.of(productItem21, productItem22));

        Payment payment2 = new Payment();
        payment2.setId(1L);
        payment2.setType("При получении");
        order2.setPayment(payment2);

        order2.setTotal(214500d);

        orderList.addAll(List.of(order1, order2));
    }

    public List<Order> getAll() {
        return orderList;
    }

    public Set<Order> getAllByIds(List<Long> ids) {
        return orderList.stream()
                .filter(order -> ids.stream()
                        .anyMatch(id -> Objects.equals(order.getId(), id)))
                .collect(Collectors.toSet());
    }
}
