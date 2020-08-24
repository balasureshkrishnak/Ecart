package com.hcl.ecart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecart.dto.PurchaseRequestDto;
import com.hcl.ecart.dto.ResponseDto;
import com.hcl.ecart.exception.CartNotFoundException;
import com.hcl.ecart.exception.InsufficientBalanceException;
import com.hcl.ecart.service.PurchaseService;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@PostMapping("/")
	public ResponseEntity<ResponseDto> makePurchases(@RequestBody PurchaseRequestDto purchaseRequestDto) throws InsufficientBalanceException, CartNotFoundException {
		ResponseDto responseDto;
		responseDto = purchaseService.makePurchase(purchaseRequestDto);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
		
	}

}
