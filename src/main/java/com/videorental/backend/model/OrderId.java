package com.videorental.backend.model;

import java.io.Serializable;

public class OrderId implements Serializable {

    private Long filmId;

    private Long orderId;

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderId orderId1 = (OrderId) o;

        if (filmId != null ? !filmId.equals(orderId1.filmId) : orderId1.filmId != null) return false;
        return orderId != null ? orderId.equals(orderId1.orderId) : orderId1.orderId == null;
    }

    @Override
    public int hashCode() {
        int result = filmId != null ? filmId.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderId{" +
                "filmId=" + filmId +
                ", orderId=" + orderId +
                '}';
    }
}
