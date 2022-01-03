package com.kozlovskaya.spring.online.store.repositories;

import com.kozlovskaya.spring.online.store.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Role, Long> {
}
