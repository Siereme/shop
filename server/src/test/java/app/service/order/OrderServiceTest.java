package app.service.order;

import app.consrtructor.TestOrderConstructor;
import app.constructor.order.OrderFactory;
import app.constructor.order.impl.DefaultOrderManager;
import app.model.dto.order.OrderDTO;
import app.model.order.Order;
import app.model.order.payment.Payment;
import app.model.user.User;
import app.repository.order.OrderRepository;
import app.repository.order.PaymentRepository;
import app.repository.user.UserRepository;
import app.utils.constants.user.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepo;
    @Mock
    private OrderFactory orderFactory;
    @Mock
    private DefaultOrderManager orderManager;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PaymentRepository paymentRepo;

    private final TestOrderConstructor orderConstructor = new TestOrderConstructor();


    @Test
    void createOrder() {
        //Prepare objects
        Order orderMock = orderConstructor.getById(1L);
        User user = userRepo.findById(1L).orElseGet(User::new);
        Payment payment = paymentRepo.findById(1L).orElseGet(Payment::new);
        OrderDTO orderDTO = new OrderDTO(user, payment);
        UserRole role = UserRole.valueOf(orderDTO.getUser().getRole().getName());

        //When stubs are called
        Mockito.when(orderFactory.getFactory(role)).thenReturn(orderManager);
        Mockito.when(orderManager.construct(orderDTO)).thenReturn(orderMock);
        Mockito.when(orderRepo.save(orderMock)).thenReturn(orderMock);

        //Call a real service method
        Order order = orderService.createOrder(orderDTO);

        //Verify stub calls
        Mockito.verify(orderFactory, Mockito.times(1)).getFactory(role);
        Mockito.verify(orderManager, Mockito.times(1)).construct(orderDTO);
        Mockito.verify(orderRepo, Mockito.times(1)).save(orderMock);

        //Check the resulting object
        Assertions.assertEquals(orderMock.getId(), order.getId());
        Assertions.assertEquals(orderMock.getUserDetails().getEmail(), order.getUserDetails().getEmail());
        Assertions.assertEquals(orderMock.getOrderItems().size(), order.getOrderItems().size());
    }
}