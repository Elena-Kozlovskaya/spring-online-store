package com.kozlovskaya.spring.online.store.repositories;

import com.kozlovskaya.spring.online.store.entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
