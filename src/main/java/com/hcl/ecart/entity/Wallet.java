package com.hcl.ecart.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer walletId;

	private Double walletAmount;

	private LocalDate validityDate;

	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}

	public Double getWalletAmount() {
		return walletAmount;
	}

	public void setWalletAmount(Double walletAmount) {
		this.walletAmount = walletAmount;
	}

	public LocalDate getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(LocalDate validityDate) {
		this.validityDate = validityDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
