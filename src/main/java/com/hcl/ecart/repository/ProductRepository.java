package com.hcl.ecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecart.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
