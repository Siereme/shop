package app.model.product.option;

public interface IOption {
    Long getId();

    OptionType getOptionType();

    String getValue();

    void setId(Long id);

    void setOptionType(OptionType optionType);

    void setValue(String value);
}
