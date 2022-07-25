package app.controller.payment;

import app.model.order.Payment;
import app.repository.payment.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/payment")
public class PaymentController {

    private final PaymentRepository paymentRepo;

    public PaymentController(PaymentRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Payment>> getOrders(){
        List<Payment> payments = paymentRepo.findAll();

        if(payments.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(payments);
    }
}
