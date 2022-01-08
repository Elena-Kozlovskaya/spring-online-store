package com.kozlovskaya.spring.online.store.converters;

import com.kozlovskaya.spring.online.store.dto.OrderItemDto;
import com.kozlovskaya.spring.online.store.entities.OrderItem;
import com.kozlovskaya.spring.online.store.entities.Product;
import com.kozlovskaya.spring.online.store.exeptions.ResourceNotFoundException;
import com.kozlovskaya.spring.online.store.services.ProductService;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {
    private ProductService productService;

    public OrderItem dtoToEntity(OrderItemDto orderItemDto){
        OrderItem orderItem = new OrderItem();
        Long id = orderItemDto.getProductId();
        System.out.println(id);
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPricePerProduct(orderItemDto.getPricePerProduct());
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }

}
