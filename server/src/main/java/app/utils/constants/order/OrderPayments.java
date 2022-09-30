package app.utils.constants.order;

public enum OrderPayments {
    CASH("Наличными"),
    CARD("Картой");

    public String value;

    OrderPayments(String value) {
        this.value = value;
    }
}
