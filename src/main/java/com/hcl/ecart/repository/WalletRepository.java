package com.hcl.ecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ecart.entity.User;
import com.hcl.ecart.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

	public Wallet findByUser(User user);

}
