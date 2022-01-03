package com.kozlovskaya.spring.online.store.services;


import com.kozlovskaya.spring.online.store.dto.ProductDto;
import com.kozlovskaya.spring.online.store.entities.Product;
import com.kozlovskaya.spring.online.store.exeptions.ResourceNotFoundException;
import com.kozlovskaya.spring.online.store.repositories.ProductRepository;
import com.kozlovskaya.spring.online.store.repositories.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAll(Integer minCost, Integer maxCost, String titlePart, Integer page){
        Specification<Product> spec = Specification.where(null);

        // select p from Product p where true - первоначальный запрос
        if(minCost != null){
            spec = spec.and(ProductSpecifications.costGreaterOrEqualsThan(minCost));
            // select p from Product p where true AND p.cost > minCost
        }
        if(maxCost != null) {
            spec = spec.and(ProductSpecifications.costLessThanOrEqualsThan(maxCost));
            // select p from Product p where true AND p.cost > minCost AND p.cost < maxCost
        }
        if(titlePart != null){
            spec = spec.and(ProductSpecifications.titleLike(titlePart));
            // если указаны minCost и titlePart
            // select p from Product p where true AND p.cost > minCost AND p.title LIKE %titlePart%
        }
        // если titlePart не указали остается: // select p from Product p where true AND p.cost > minCost AND p.cost < maxCost
        return productRepository.findAll(spec, PageRequest.of(page - 1, 50));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(ProductDto productDto){
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Обновление продукта невозможно, id: " + productDto.getId() + " в базе не найден."));
        product.setTitle(productDto.getTitle());
        product.setCost(productDto.getCost());
        return product;
    }

    /*@Transactional
    public void changeCost(Long productId, Integer delta) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to change product's cost. Product is not found, id: " + productId));
        product.setCost(product.getCost() + delta);
    }

    public List<Product> findAllByCostBetween(Integer min, Integer max) {
        if(min == 0){
            return productRepository.findAllByCostLessThanMax(max);
        } else if(max == null){
            return productRepository.findAllByCostMoreThanMin(min);
        } else {
            return productRepository.findAllByCostBetween(min, max);
        }
    }*/
}
