package app.model.order.payment;

public interface IPayment {
    Long getId();

    String getType();

    void setId(Long id);

    void setType(String type);
}
