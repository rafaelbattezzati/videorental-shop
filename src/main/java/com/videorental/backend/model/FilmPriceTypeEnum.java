package com.videorental.backend.model;

public enum FilmPriceTypeEnum {
    NEW(1), BASIC(2), OLD(3);

    FilmPriceTypeEnum(int type) {
        this.type = type;
    }

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
