package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class PaymentService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Autowired
    private OrderService orderService;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    public PaymentIntent createPaymentIntent(Long orderId, String username) throws StripeException {
        Order order = orderService.getOrderById(orderId, username);
        
        long amount = (long) (order.getTotalPrice() * 100); // Stripe expects amount in cents

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency("usd")
                .putMetadata("orderId", orderId.toString())
                .build();

        return PaymentIntent.create(params);
    }

    public void handlePaymentSuccess(Long orderId) {
        orderService.updatePaymentStatus(orderId, "PAID");
    }

    public void handlePaymentFailure(Long orderId) {
        orderService.updatePaymentStatus(orderId, "FAILED");
    }
}
