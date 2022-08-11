package app.model.shoppingCart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
@Getter
@Setter
@RequiredArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shoppingCart", orphanRemoval = true)
    private Set<ShoppingCartProductItem> cartItems = new HashSet<>();

    @Column(name = "count_items")
    private int count;

    @Column(name = "total")
    private Double total;

    public void setCartItem(ShoppingCartProductItem cartItem) {
        cartItems.add(cartItem);
    }

    public void clear() {
        this.cartItems.clear();
        this.count = 0;
        this.total = 0d;
    }
}
