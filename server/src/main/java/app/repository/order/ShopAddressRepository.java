package app.repository.order;

import app.model.order.address.ShopAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopAddressRepository extends JpaRepository<ShopAddress, Long> {
}
