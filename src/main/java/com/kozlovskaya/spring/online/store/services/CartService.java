package com.kozlovskaya.spring.online.store.services;

import com.kozlovskaya.spring.online.store.dto.Cart;
import com.kozlovskaya.spring.online.store.entities.Product;
import com.kozlovskaya.spring.online.store.exeptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private  final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init(){
        cart = new Cart();
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public void addProductByIdToCart(Long productId){
        //Первый продукт достается из бд, остальные просто увеличат счетчик.
       if (!getCurrentCart().addProduct(productId)){
           Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину. Продукт не найден, id: " + productId));
           getCurrentCart().addProduct(product);
       }
    }


    public void clear(){
        getCurrentCart().clear();
    }

}
