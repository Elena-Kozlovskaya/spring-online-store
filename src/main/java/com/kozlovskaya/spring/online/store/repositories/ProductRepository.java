package com.kozlovskaya.spring.online.store.repositories;

import com.kozlovskaya.spring.online.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    //@Query("select p from Product p where p.cost between ?1 and ?2")
    List<Product> findAllByCostBetween(Integer min, Integer max);

    @Query("select p from Product p where p.cost > ?1")
    List<Product> findAllByCostMoreThanMin(Integer min);

    @Query("select p from Product p where p.cost < ?1")
    List<Product> findAllByCostLessThanMax(Integer max);
}
