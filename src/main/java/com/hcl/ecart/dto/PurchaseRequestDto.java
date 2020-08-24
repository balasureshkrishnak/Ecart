package com.hcl.ecart.dto;

import java.time.LocalDate;

public class PurchaseRequestDto {

	private Integer cartId;


	private String billingAddress;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}


	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	
}
