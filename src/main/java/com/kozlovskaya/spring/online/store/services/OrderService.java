package com.kozlovskaya.spring.online.store.services;

import com.kozlovskaya.spring.online.store.dto.OrderItemDto;
import com.kozlovskaya.spring.online.store.entities.Order;
import com.kozlovskaya.spring.online.store.entities.OrderItem;
import com.kozlovskaya.spring.online.store.entities.User;
import com.kozlovskaya.spring.online.store.exeptions.ResourceNotFoundException;
import com.kozlovskaya.spring.online.store.repositories.OrderItemRepository;
import com.kozlovskaya.spring.online.store.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private Order order;

    public void createNewOrder(String address, String phone, User user) {
        order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartService.getCurrentCart().getTotalPrice());
        order.setAddress(address);
        order.setPhone(phone);
        orderRepository.save(order);
    }

    public void saveOrderDetails() {
        List<OrderItemDto> orderItemDtos = cartService.getCurrentCart().getItems();
        List<OrderItem> orderItems = orderItemDtos
                .stream()
                .map(p -> new OrderItem(productService.findById(p.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Товар не найден, id: " + p.getProductId())),
                        order.getUser(),
                        order,
                        p.getQuantity(),
                        p.getPricePerProduct(),
                        p.getPrice()))
                .collect(Collectors.toList());
        orderItemRepository.saveAll(orderItems);
    }

}
