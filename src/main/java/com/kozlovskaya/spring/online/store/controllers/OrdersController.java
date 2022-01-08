package com.kozlovskaya.spring.online.store.controllers;


import com.kozlovskaya.spring.online.store.entities.User;
import com.kozlovskaya.spring.online.store.services.OrderService;
import com.kozlovskaya.spring.online.store.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    private final UserService userService;


    @GetMapping
    public void saveNewOrder(Principal principal) {
        String address = "address";
        String phone = "phone";
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", principal.getName())));
        orderService.createNewOrder(address, phone, user);
        orderService.saveOrderDetails();
    }


}
