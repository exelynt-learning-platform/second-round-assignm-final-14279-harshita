package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartService cartService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductService productService;

    private User user;
    private Cart cart;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setUsername("testuser");

        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(50.0);
        product.setStockQuantity(10);

        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(2); // total 100

        cart = new Cart();
        cart.setUser(user);
        cart.getItems().add(item);
    }

    @Test
    void testPlaceOrder_Success() {
        when(cartService.getCartByUser("testuser")).thenReturn(cart);
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        
        Order savedOrder = new Order();
        savedOrder.setTotalPrice(100.0);
        savedOrder.setPaymentStatus("PENDING");
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        Order result = orderService.placeOrder("testuser", "123 Test St");

        assertNotNull(result);
        assertEquals(100.0, result.getTotalPrice());
        assertEquals("PENDING", result.getPaymentStatus());
        
        verify(productService, times(1)).updateProduct(eq(1L), any(Product.class));
        verify(cartService, times(1)).clearCart(cart);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testPlaceOrder_EmptyCart() {
        cart.getItems().clear();
        when(cartService.getCartByUser("testuser")).thenReturn(cart);

        RuntimeException exception = assertThrows(RuntimeException.class, 
                () -> orderService.placeOrder("testuser", "123 Test St"));
        
        assertEquals("Cart is empty", exception.getMessage());
    }
}
