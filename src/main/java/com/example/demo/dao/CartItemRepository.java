package com.example.demo.dao;

import com.example.demo.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(collectionResourceRel = "cartItem", path = "cart-item")
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
