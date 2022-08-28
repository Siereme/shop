package app.repository.order;

import app.consrtructor.TestOrderConstructor;
import app.model.order.Order;
import app.model.order.OrderProductItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepo;
    private final List<Order> orderList = new ArrayList<>();

    @BeforeAll
    void setUp() {
        orderList.addAll(new TestOrderConstructor().getAll());
    }

    @Test
    void findAll() {
        //Find all orders from repository
        List<Order> orders = orderRepo.findAll();

        //Get all orders from constructor
        List<Order> checkOrderList = orderList;

        //Check collections
        Assertions.assertEquals(checkOrderList.size(), orders.size());

        //Comparison of the first elements of collections
        Assertions.assertEquals(checkOrderList.get(0).getId(), orders.get(0).getId());
        Assertions.assertEquals(checkOrderList.get(0).getPayment().getType(), orders.get(0).getPayment().getType());
        Assertions.assertEquals(checkOrderList.get(0).getTotal(), orders.get(0).getTotal());
        //// Check user details
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getId(), orders.get(0).getUserDetails().getId());
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getName(), orders.get(0).getUserDetails().getName());
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getPatronymic(), orders.get(0).getUserDetails().getPatronymic());
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getSurname(), orders.get(0).getUserDetails().getSurname());
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getEmail(), orders.get(0).getUserDetails().getEmail());
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getPhone(), orders.get(0).getUserDetails().getPhone());
        //// Check order items
        Assertions.assertEquals(checkOrderList.get(0).getOrderItems().size(), orders.get(0).getOrderItems().size());
        Assertions.assertTrue(
                orders.get(0).getOrderItems()
                        .stream()
                        .map(OrderProductItem::getId)
                        .allMatch(orderId -> checkOrderList.get(0).getOrderItems()
                                .stream()
                                .map(OrderProductItem::getId)
                                .anyMatch(orderId::equals))
        );

        //Comparison of the second elements of collections
        Assertions.assertEquals(checkOrderList.get(1).getId(), orders.get(1).getId());
        Assertions.assertEquals(checkOrderList.get(1).getPayment().getType(), orders.get(1).getPayment().getType());
        Assertions.assertEquals(checkOrderList.get(1).getTotal(), orders.get(1).getTotal());
        //// Check user details
        Assertions.assertEquals(checkOrderList.get(1).getUserDetails().getId(), orders.get(1).getUserDetails().getId());
        Assertions.assertEquals(checkOrderList.get(1).getUserDetails().getName(), orders.get(1).getUserDetails().getName());
        Assertions.assertEquals(checkOrderList.get(1).getUserDetails().getPatronymic(), orders.get(1).getUserDetails().getPatronymic());
        Assertions.assertEquals(checkOrderList.get(1).getUserDetails().getSurname(), orders.get(1).getUserDetails().getSurname());
        Assertions.assertEquals(checkOrderList.get(1).getUserDetails().getEmail(), orders.get(1).getUserDetails().getEmail());
        Assertions.assertEquals(checkOrderList.get(1).getUserDetails().getPhone(), orders.get(1).getUserDetails().getPhone());
        //// Check order items
        Assertions.assertEquals(checkOrderList.get(1).getOrderItems().size(), orders.get(1).getOrderItems().size());
        Assertions.assertTrue(
                orders.get(1).getOrderItems()
                        .stream()
                        .map(OrderProductItem::getId)
                        .allMatch(orderId -> checkOrderList.get(1).getOrderItems()
                                .stream()
                                .map(OrderProductItem::getId)
                                .anyMatch(orderId::equals))
        );
    }

    @Test
    void findAllByUserId() {
        //Find orders from repository where userId = 1
        List<Order> orders = orderRepo.findAllByUserId(1L).orElseGet(Collections::emptyList);

        //Get orders from constructor where userId = 1
        List<Order> checkOrderList = Collections.singletonList(orderList.get(0));

        //Check collections
        Assertions.assertEquals(checkOrderList.size(), orders.size());

        //Comparison of the first elements of collections
        Assertions.assertEquals(checkOrderList.get(0).getId(), orders.get(0).getId());
        Assertions.assertEquals(checkOrderList.get(0).getPayment().getType(), orders.get(0).getPayment().getType());
        Assertions.assertEquals(checkOrderList.get(0).getTotal(), orders.get(0).getTotal());
        //// Check user details
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getId(), orders.get(0).getUserDetails().getId());
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getName(), orders.get(0).getUserDetails().getName());
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getPatronymic(), orders.get(0).getUserDetails().getPatronymic());
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getSurname(), orders.get(0).getUserDetails().getSurname());
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getEmail(), orders.get(0).getUserDetails().getEmail());
        Assertions.assertEquals(checkOrderList.get(0).getUserDetails().getPhone(), orders.get(0).getUserDetails().getPhone());
        //// Check order items
        Assertions.assertEquals(checkOrderList.get(0).getOrderItems().size(), orders.get(0).getOrderItems().size());
        Assertions.assertTrue(
                orders.get(0).getOrderItems()
                        .stream()
                        .map(OrderProductItem::getId)
                        .allMatch(itemId -> checkOrderList.get(0).getOrderItems()
                                .stream()
                                .map(OrderProductItem::getId)
                                .anyMatch(itemId::equals))
        );
    }

}