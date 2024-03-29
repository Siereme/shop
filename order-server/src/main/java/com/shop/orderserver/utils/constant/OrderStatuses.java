package com.shop.orderserver.utils.constant;

public enum OrderStatuses {
    AWAITING_PAYMENT("Ожидает оплаты"),
    ACCEPT_PROCESS("Принят в обработку"),
    SENT("Отправлен"),
    DELIVERED("Доставлен"),
    RECEIVED("Получен");

    public String value;

    OrderStatuses(String value) {
        this.value = value;
    }
}
