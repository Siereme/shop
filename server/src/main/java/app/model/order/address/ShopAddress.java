package app.model.order.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "shop_address")
@Getter
@Setter
@RequiredArgsConstructor
public class ShopAddress implements IAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Старана является обязательной")
    @Size(min = 3, max = 126, message = "От 3 до 126 символов")
    @Column(name = "country")
    private String country;

    @NotBlank(message = "Город является обязательным")
    @Size(min = 3, max = 126, message = "От 3 до 126 символов")
    @Column(name = "city")
    private String city;


    @NotBlank(message = "Улица является обязательной")
    @Size(min = 3, max = 126, message = "От 3 до 126 символов")
    @Column(name = "street")
    private String street;

    @NotBlank(message = "Обязательное поле")
    @Size(min = 1, max = 10, message = "От 1 до 10 символов")
    @Column(name = "building")
    private String building;

    @Column(name = "flat")
    private String flat;

}
