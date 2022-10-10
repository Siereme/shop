package app.model.product.option;

public interface IOptionValue {
    Long getId();

    Option getOption();

    String getValue();

    void setId(Long id);

    void setOption(Option option);

    void setValue(String value);
}
