package com.shop.orderserver.service;

import com.shop.orderserver.dto.OrderDTO;
import com.shop.orderserver.dto.UserDTO;
import com.shop.orderserver.model.Order;
import com.shop.orderserver.repository.OrderRepository;
import com.shop.orderserver.service.builder.IOrderManager;
import com.shop.orderserver.service.builder.OrderFactory;
import com.shop.orderserver.utils.validation.CustomerValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements IOrderService<Order> {

    private final OrderRepository orderRepo;
    private final OrderFactory orderFactory;

    private final CustomerValidation customerValidation;

    public Order createOrder(OrderDTO orderDTO) {
        UserDTO user = orderDTO.getUser();
//        if (!customerValidation.isValidCustomer(user.getEmail())) {
//            throw new IllegalStateException("Customer is not found");
//        }

        IOrderManager<Order> constructor = orderFactory.getFactory(user.getRole());
        Order order = constructor.construct(orderDTO);

        return orderRepo.save(order);
    }

}
