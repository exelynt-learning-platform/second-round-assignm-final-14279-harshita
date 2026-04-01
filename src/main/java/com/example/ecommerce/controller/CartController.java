package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartItemRequest;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCart() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(cartService.getCartByUser(username));
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(@RequestBody CartItemRequest cartItemRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(cartService.addProductToCart(username, cartItemRequest.getProductId(), cartItemRequest.getQuantity()));
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> updateCartItem(@RequestBody CartItemRequest cartItemRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(cartService.updateCartItemQuantity(username, cartItemRequest.getProductId(), cartItemRequest.getQuantity()));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable Long productId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(cartService.removeProductFromCart(username, productId));
    }
}
