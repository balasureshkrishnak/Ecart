package com.hcl.ecart.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.ecart.config.ApplicationConstants;
import com.hcl.ecart.dto.PurchaseRequestDto;
import com.hcl.ecart.dto.ResponseDto;
import com.hcl.ecart.entity.Cart;
import com.hcl.ecart.entity.CartItem;
import com.hcl.ecart.entity.Product;
import com.hcl.ecart.entity.Purchase;
import com.hcl.ecart.entity.User;
import com.hcl.ecart.entity.Wallet;
import com.hcl.ecart.exception.CartNotFoundException;
import com.hcl.ecart.exception.InsufficientBalanceException;
import com.hcl.ecart.repository.CartItemRepository;
import com.hcl.ecart.repository.CartRepository;
import com.hcl.ecart.repository.ProductRepository;
import com.hcl.ecart.repository.PurchaseRepository;
import com.hcl.ecart.repository.UserRepository;
import com.hcl.ecart.repository.WalletRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	WalletRepository walletRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Transactional
	public ResponseDto makePurchase(PurchaseRequestDto purchaseRequestDto) throws InsufficientBalanceException, CartNotFoundException{
		
		ResponseDto responseDto = new ResponseDto();
		Integer cartId = purchaseRequestDto.getCartId();
		LocalDate purchaseDate = LocalDate.now();
		String billingAddress = purchaseRequestDto.getBillingAddress();
		Double totalAmount = 0.0;
		Optional<Cart> carts = cartRepository.findByCartId(cartId);
		if(carts.isPresent()) {
			Cart cart = carts.get();
			List<CartItem> cartItemList = cartItemRepository.findByCart(cart);
			User user = cart.getUser();
			Wallet wallet = walletRepository.findByUser(user);
			
			for(CartItem cartItem: cartItemList) {
				Product product = cartItem.getProduct();
				totalAmount = totalAmount + product.getProductPrice()*cartItem.getQuantity();
			}
			
			if(wallet.getWalletAmount() > totalAmount) {
				
				Purchase purchase = new Purchase();
				purchase.setCart(cart);
				purchase.setTotalAmount(totalAmount);
				purchase.setPurchaseDate(purchaseDate);
				purchase.setBillingAddress(billingAddress);
				
				for(CartItem cartItem: cartItemList) {
					Product product = cartItem.getProduct();
					product.setProductQuantity(product.getProductQuantity() - cartItem.getQuantity());
					productRepository.save(product);
				}
				
				purchaseRepository.save(purchase);
				responseDto.setStatusMessage(ApplicationConstants.PURCHASE_SUCCESSFUL);
				responseDto.setStatusCode(HttpStatus.OK.value());
				return responseDto;
				
			} else {
				throw new InsufficientBalanceException(ApplicationConstants.INSUFFICIENT_BALANCE);
			}
		}
		
		throw new CartNotFoundException(ApplicationConstants.CART_NOT_FOUND);
	}

}
