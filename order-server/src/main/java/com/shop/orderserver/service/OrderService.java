package com.shop.orderserver.service;

import com.shop.orderserver.dto.OrderDTO;
import com.shop.orderserver.dto.UserDTO;
import com.shop.orderserver.model.Order;
import com.shop.orderserver.repository.OrderRepository;
import com.shop.orderserver.service.builder.IOrderManager;
import com.shop.orderserver.service.builder.OrderFactory;
import com.shop.orderserver.utils.constant.ServiceUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService<Order> {

    private final WebClient.Builder webClientBuilder;
    private final OrderRepository orderRepo;
    private final OrderFactory orderFactory;


    @Transactional
    public Order createOrder(OrderDTO orderDTO) {
        UserDTO user = orderDTO.getUser();

        Boolean isExist = webClientBuilder.build()
                .get().uri(ServiceUrl.CUSTOMER_EXIST + user.getId())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (Boolean.FALSE.equals(isExist)) {
            throw new IllegalStateException("Customer is not found");
        }

        IOrderManager<Order> constructor = orderFactory.getFactory(user.getRole());
        Order order = constructor.construct(orderDTO);

        return orderRepo.save(order);
    }

}