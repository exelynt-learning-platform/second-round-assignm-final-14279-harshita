package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandlePaymentSuccess() {
        Order mockOrder = new Order();
        mockOrder.setPaymentStatus("PAID");
        when(orderService.updatePaymentStatus(1L, "PAID")).thenReturn(mockOrder);

        paymentService.handlePaymentSuccess(1L);

        verify(orderService, times(1)).updatePaymentStatus(1L, "PAID");
    }

    @Test
    void testHandlePaymentFailure() {
        Order mockOrder = new Order();
        mockOrder.setPaymentStatus("FAILED");
        when(orderService.updatePaymentStatus(1L, "FAILED")).thenReturn(mockOrder);

        paymentService.handlePaymentFailure(1L);

        verify(orderService, times(1)).updatePaymentStatus(1L, "FAILED");
    }
}
