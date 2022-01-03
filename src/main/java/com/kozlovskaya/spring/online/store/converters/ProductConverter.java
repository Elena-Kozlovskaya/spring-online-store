package com.kozlovskaya.spring.online.store.converters;

import com.kozlovskaya.spring.online.store.dto.ProductDto;
import com.kozlovskaya.spring.online.store.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }
}
