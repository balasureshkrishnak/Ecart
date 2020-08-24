package com.hcl.ecart.service;

import com.hcl.ecart.dto.PurchaseRequestDto;
import com.hcl.ecart.dto.ResponseDto;
import com.hcl.ecart.exception.CartNotFoundException;
import com.hcl.ecart.exception.InsufficientBalanceException;

public interface PurchaseService {
	
	public ResponseDto makePurchase(PurchaseRequestDto purchaseRequestDto)  throws InsufficientBalanceException,CartNotFoundException;

}
