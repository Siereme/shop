package app.model.order.userDetails;

public interface IOrderUserDetails {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getSurname();

    void setSurname(String surname);

    String getPatronymic();

    void setPatronymic(String patronymic);

    String getEmail();

    void setEmail(String email);

    String getPhone();

    void setPhone(String phone);

}
