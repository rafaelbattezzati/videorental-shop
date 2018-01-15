package com.videorental.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")
@IdClass(OrderId.class)
public class Order implements Serializable {

    @Id
    @Column(name = "film_id", insertable = false, updatable = false)
    private Long filmId;

    @Id
    @Column(name = "order_id", insertable = false, updatable = false)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false, insertable = false, updatable = false)
    //@JsonIgnore
    public Film film;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, insertable = false, updatable = false)
    //@JsonIgnore
    public Basket basket;

    @Column(name = "quantity")
    public int quantity;
    @Column(name = "start_rent_date")
    public Date startRentDate;
    @Column(name = "expected_end_rent_date")
    public Date expectedEndRentDate;
    @Column(name = "end_rent_date")
    public Date endRentDate;
    @Column(name = "expected_price")
    public Double expectedPrice;
    @Column(name = "final_price")
    public Double finalPrice;
    @Column(name = "daysBetween")
    public float daysBetween;

    public Order () {
    }

    public Order (Long filmId,
                  int quantity,
                  Date startRentDate,
                  Date expectedEndRentDate,
                  Date endRentDate,
                  Film film,
                  Double expectedPrice,
                  Double finalPrice) {
        this.filmId = filmId;
        this.quantity = quantity;
        this.startRentDate = startRentDate;
        this.expectedEndRentDate = expectedEndRentDate;
        this.endRentDate = endRentDate;
        this.film = film;
        this.expectedPrice = expectedPrice;
        this.finalPrice = finalPrice;
        calculateExpectedPrice(this);
        //calculateFinalPrice(this);
    }

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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getStartRentDate() {
        return startRentDate;
    }

    public void setStartRentDate(Date startRentDate) {
        this.startRentDate = startRentDate;
    }

    public Date getExpectedEndRentDate() {
        return expectedEndRentDate;
    }

    public void setExpectedEndRentDate(Date expectedEndRentDate) {
        this.expectedEndRentDate = expectedEndRentDate;
    }

    public Date getEndRentDate() {
        return endRentDate;
    }

    public void setEndRentDate(Date endRentDate) {
        this.endRentDate = endRentDate;
    }

    public Double getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(Double expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public float getDaysBetween() {
        return daysBetween;
    }

    public void setDaysBetween(float daysBetween) {
        this.daysBetween = daysBetween;
    }

    public static void calculateExpectedPrice(Order order){
        long difference = order.getExpectedEndRentDate().getTime() - order.getStartRentDate().getTime();
        float daysBetween = (difference / (1000*60*60*24));
        Double price = order.getFilm().getFilmPriceType().getPrice();
        Integer daysDiscount = order.getFilm().getFilmPriceType().getDaysDiscount();

        order.setExpectedPrice(order.getFilm().getFilmPriceType().getPrice());
        if(daysBetween > 0 && ((daysBetween - daysDiscount) > 0)) {
            order.setExpectedPrice(price * (daysBetween - daysDiscount));
        }
        order.setDaysBetween(daysBetween);
    }

    public static void calculateFinalPrice(Order order){
        if(order.getEndRentDate() != null && order.getEndRentDate().getTime() > 0) {
            Long difference = order.getEndRentDate().getTime() - order.getExpectedEndRentDate().getTime();
            float daysBetween = (difference / (1000*60*60*24));
            Integer daysDiscount = order.getFilm().getFilmPriceType().getDaysDiscount();
            Double price = order.getFilm().getFilmPriceType().getPrice();
            float oldDaysBetween = order.getDaysBetween();
            order.setDaysBetween(oldDaysBetween + daysBetween);

            if(order.getDaysBetween() > 0) {
                if(daysDiscount>order.getDaysBetween()){
                    order.setFinalPrice(price * (daysDiscount - order.getDaysBetween()));
                } else {
                    order.setFinalPrice(price * (order.getDaysBetween() - daysDiscount));
                }
            }


        } else {
            order.setFinalPrice(order.getExpectedPrice());
            order.setEndRentDate(order.getExpectedEndRentDate());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (film != null ? !film.equals(order.film) : order.film != null) return false;
        return basket != null ? basket.equals(order.basket) : order.basket == null;
    }

    @Override
    public int hashCode() {
        int result = film != null ? film.hashCode() : 0;
        result = 31 * result + (basket != null ? basket.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "filmId=" + filmId +
                ", orderId=" + orderId +
                ", film=" + film +
                ", basket=" + basket +
                ", quantity=" + quantity +
                ", startRentDate=" + startRentDate +
                ", expectedEndRentDate=" + expectedEndRentDate +
                ", endRentDate=" + endRentDate +
                ", expectedPrice=" + expectedPrice +
                ", finalPrice=" + finalPrice +
                ", daysBetween=" + daysBetween +
                '}';
    }
}
