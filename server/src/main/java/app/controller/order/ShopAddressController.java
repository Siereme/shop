package app.controller.order;

import app.model.order.address.ShopAddress;
import app.model.order.receipt.Receipt;
import app.repository.order.ReceiptRepository;
import app.repository.order.ShopAddressRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shop-address")
public class ShopAddressController {
    private final ShopAddressRepository shopAddressRepo;

    public ShopAddressController(ShopAddressRepository shopAddressRepo) {
        this.shopAddressRepo = shopAddressRepo;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getShopAddress() {
        try {
            List<ShopAddress> shopAddresses = shopAddressRepo.findAll();
            return ResponseEntity.ok().body(shopAddresses);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
