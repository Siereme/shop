package app.service.order;

import app.consrtructor.TestOrderConstructor;
import app.model.dto.order.OrderDTO;
import app.model.order.Order;
import app.model.order.payment.Payment;
import app.model.user.User;
import app.repository.order.PaymentRepository;
import app.repository.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest {

    @Mock
    private OrderService orderService;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PaymentRepository paymentRepo;
    private final TestOrderConstructor orderConstructor = new TestOrderConstructor();


    @Test
    void createOrder() {
        Order orderMock = orderConstructor.getById(1L);

        User user = userRepo.findById(1L).orElseGet(User::new);
        Payment payment = paymentRepo.findById(1L).orElseGet(Payment::new);
        OrderDTO orderDTO = new OrderDTO(user, payment);


        Mockito.when(orderService.createOrder(orderDTO)).thenReturn(orderMock);

        Order order = orderService.createOrder(orderDTO);

        Assertions.assertEquals(orderMock.getId(), order.getId());
        Assertions.assertEquals(orderMock.getUserDetails().getEmail(), order.getUserDetails().getEmail());
        Assertions.assertEquals(orderMock.getOrderItems().size(), order.getOrderItems().size());
    }
}