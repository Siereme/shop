package app.model.product.option;

public interface IOption {
    Long getId();

    String getType();

    java.util.List<OptionValue> getValues();

    void setId(Long id);

    void setType(String type);

    void setValues(java.util.List<OptionValue> values);
}
