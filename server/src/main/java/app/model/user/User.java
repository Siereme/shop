package app.model.user;

import app.model.order.Order;
import app.model.shoppingCart.ShoppingCart;
import app.model.user.role.Role;
import app.utils.constants.user.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@RequiredArgsConstructor
public class User implements IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Имя является обязательным")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Фамилия является обязательной")
    @Column(name = "surname")
    private String surname;

    @NotBlank(message = "Отчество является обязательным")
    @Column(name = "patronymic")
    private String patronymic;

    @NotBlank(message = "Email является обязательным")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Пароль является обязательным")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Телефон является обязательным")
    @Column(name = "phone", unique = true)
    private String phone;

    @JsonIgnoreProperties(value = {"users", "permissions", "authorities"}, allowSetters = true)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id")
    private ShoppingCart shoppingCart = new ShoppingCart();

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Order> orders = new HashSet<>();

    public void setOrder(Order order) {
        this.orders.add(order);
    }

}
