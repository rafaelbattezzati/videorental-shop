package com.videorental.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Item implements Serializable {

    public Long itemId;
    public Film film;
    public int quantity;
    public String startRentDate;
    public String expectedEndRentDate;
    public String endRentDate;
    public double expectedPrice;
    public double finalPrice;


    public Item() {
    }

    public Item(Film film) {
        this.film = film;
    }

    public Item(Long itemId,
                Film film,
                int quantity,
                String startRentDate,
                String expectedEndRentDate,
                String endRentDate,
                double expectedPrice,
                double finalPrice) {
        this.itemId = itemId;
        this.film = film;
        this.quantity = quantity;
        this.startRentDate = startRentDate;
        this.expectedEndRentDate = expectedEndRentDate;
        this.endRentDate = endRentDate;
        this.expectedPrice = expectedPrice;
        this.finalPrice = finalPrice;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStartRentDate() {
        return startRentDate;
    }

    public void setStartRentDate(String startRentDate) {
        this.startRentDate = startRentDate;
    }

    public String getExpectedEndRentDate() {
        return expectedEndRentDate;
    }

    public void setExpectedEndRentDate(String expectedEndRentDate) {
        this.expectedEndRentDate = expectedEndRentDate;
    }

    public String getEndRentDate() {
        return endRentDate;
    }

    public void setEndRentDate(String endRentDate) {
        this.endRentDate = endRentDate;
    }

    public double getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(double expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return itemId != null ? itemId.equals(item.itemId) : item.itemId == null;
    }

    @Override
    public int hashCode() {
        return itemId != null ? itemId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", film=" + film +
                ", quantity=" + quantity +
                ", startRentDate=" + startRentDate +
                ", expectedEndRentDate=" + expectedEndRentDate +
                ", endRentDate=" + endRentDate +
                ", expectedPrice=" + expectedPrice +
                ", finalPrice=" + finalPrice +
                '}';
    }
}
