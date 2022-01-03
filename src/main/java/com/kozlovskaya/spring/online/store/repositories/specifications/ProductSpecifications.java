package com.kozlovskaya.spring.online.store.repositories.specifications;

import com.kozlovskaya.spring.online.store.entities.Product;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecifications {
    public static Specification<Product> costGreaterOrEqualsThan(Integer cost){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost);
    }

    public static Specification<Product> costLessThanOrEqualsThan(Integer cost){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost);
    }

    //select s from Product p where p.title like %To%
    public static Specification<Product> titleLike(String titlePart){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
