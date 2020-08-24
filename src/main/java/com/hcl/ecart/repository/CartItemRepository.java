package com.hcl.ecart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.ecart.entity.Cart;
import com.hcl.ecart.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	public List<CartItem> findByCart(Cart cart);
	
	

}
