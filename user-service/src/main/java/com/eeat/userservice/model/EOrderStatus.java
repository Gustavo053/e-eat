package com.eeat.userservice.model;

public enum EOrderStatus {
    CREATED("CREATED", "Order created"),
    CONFIRM_ORDER("CONFIRM_ORDER", "Order confirmed for restaurant"),
    SENDED_ORDER("SENDED_ORDER", "Order sended for restaurant");

    private String label;
    private String value;

    EOrderStatus(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
