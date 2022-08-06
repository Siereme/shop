package app.model.shoppingCart;

import app.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "shoppingCart", orphanRemoval=true)
    private Set<ShoppingCartProductItem> cartItems = new HashSet<>();

    @Column(name = "total")
    private Double total;

    @Column(name = "count_items")
    private int count;

    public void setCartItem(ShoppingCartProductItem cartItem) {
        cartItems.add(cartItem);
    }

    public void clear(){
        this.cartItems.clear();
        this.count = 0;
        this.total = 0d;
    }
}
