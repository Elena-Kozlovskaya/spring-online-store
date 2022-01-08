package com.kozlovskaya.spring.online.store.repositories;

import com.kozlovskaya.spring.online.store.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
