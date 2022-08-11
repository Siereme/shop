package app.model.shoppingCart;

import app.model.product.IProductItem;
import app.model.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shopping_cart_items")
@Getter
@Setter
@RequiredArgsConstructor
public class ShoppingCartProductItem implements IProductItem<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(targetEntity = ShoppingCart.class)
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    private ShoppingCart shoppingCart;

    @JsonIgnoreProperties(value = "categories", allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "count")
    private int count;

}
