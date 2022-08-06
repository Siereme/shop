package app.model.order.delivery;

public interface IDelivery {
    Long getId();

    String getAddress();

    String getBuilding();

    java.sql.Date getDate();

    void setId(Long id);

    void setAddress(String address);

    void setBuilding(String building);

    void setDate(java.sql.Date date);
}
