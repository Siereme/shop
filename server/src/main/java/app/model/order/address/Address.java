package app.model.order.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@RequiredArgsConstructor
public class Address implements IAddress {

    @NotBlank(message = "Старана является обязательной")
    @Size(min = 3, max = 126, message = "От 3 до 126 символов")
    private String country;

    @NotBlank(message = "Город является обязательным")
    @Size(min = 3, max = 126, message = "От 3 до 126 символов")
    private String city;


    @NotBlank(message = "Улица является обязательной")
    @Size(min = 3, max = 126, message = "От 3 до 126 символов")
    private String street;

    @NotBlank(message = "Обязательное поле")
    @Size(min = 1, max = 10, message = "От 1 до 10 символов")
    private String building;
    
    private String flat;

}
