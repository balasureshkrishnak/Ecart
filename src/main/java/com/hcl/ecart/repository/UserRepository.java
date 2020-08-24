package com.hcl.ecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.ecart.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
