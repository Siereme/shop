package com.shop.orderserver.controller;

import com.shop.orderserver.model.payment.Payment;
import com.shop.orderserver.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/payment")
public class PaymentController {

    private final PaymentRepository paymentRepo;

    public PaymentController(PaymentRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getOrders() {
        try {
            List<Payment> payments = paymentRepo.findAll();
            return ResponseEntity.ok().body(payments);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
