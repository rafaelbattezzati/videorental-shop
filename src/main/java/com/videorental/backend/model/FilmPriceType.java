package com.videorental.backend.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Entity
@Table(name = "filmType")
public class FilmPriceType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "filmType_id")
    private Long type;
    @Column(name = "price")
    private Double price;
    @Column(name = "description")
    private String description;
    @Column(name = "days_discount")
    private Integer daysDiscount;
    @Column(name = "bonus_points")
    private Integer bonusPoints;
    @Column(name = "discountDescription")
    private String discountDescription;

    @OneToMany(cascade = CascadeType.ALL,
               mappedBy = "filmPriceType")
    @JsonIgnore
    private Set<Film> films = new HashSet<>();

    public FilmPriceType() {
    }

    public FilmPriceType(Double price, String description, Integer daysDiscount, Integer bonusPoints, String discountDescription, Set<Film> films) {
        this.price = price;
        this.description = description;
        this.daysDiscount = daysDiscount;
        this.bonusPoints = bonusPoints;
        this.discountDescription = discountDescription;
        this.films = films;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDaysDiscount() {
        return daysDiscount;
    }

    public void setDaysDiscount(Integer daysDiscount) {
        this.daysDiscount = daysDiscount;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    @JsonIgnore
    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmPriceType that = (FilmPriceType) o;

        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FilmPriceType{" +
                "type=" + type +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", daysDiscount=" + daysDiscount +
                ", bonusPoints=" + bonusPoints +
                ", discountDescription='" + discountDescription + '\'' +
                ", films=" + films +
                '}';
    }
}
