package app.model.order.address;

public interface IAddress {

    String getCountry();

    String getCity();

    String getStreet();

    String getBuilding();

    String getFlat();

    void setCountry(String country);

    void setCity(String city);

    void setStreet(String street);

    void setBuilding(String building);

    void setFlat(String flat);
}
