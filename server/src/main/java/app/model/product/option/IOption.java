package app.model.product.option;

public interface IOption {
    Long getId();

    String getName();

    String getValue();

    void setId(Long id);

    void setName(String name);

    void setValue(String value);
}
