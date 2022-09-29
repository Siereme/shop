package app.controller.order;

import app.model.order.receipt.Receipt;
import app.repository.order.ReceiptRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/receipt")
public class ReceiptController {
    private final ReceiptRepository receiptRepo;

    public ReceiptController(ReceiptRepository paymentRepo) {
        this.receiptRepo = paymentRepo;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getReceipts() {
        try {
            List<Receipt> receipts = receiptRepo.findAll();
            return ResponseEntity.ok().body(receipts);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
