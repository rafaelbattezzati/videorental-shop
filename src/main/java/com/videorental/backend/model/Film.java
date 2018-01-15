package com.videorental.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "films")
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private Long id;
    @Column(name = "film_name")
    private String name;
    @Column(name = "film_category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "filmType_id", nullable = false)
    private FilmPriceType filmPriceType;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "basket")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    @Transient
    private String type;

    static {

    }

    public Film () {
    }

    public Film(String name, String category, FilmPriceType filmPriceType, Set<Order> orders, String type) {
        this.name = name;
        this.category = category;
        this.filmPriceType = filmPriceType;
        this.orders = orders;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public FilmPriceType getFilmPriceType() {
        return filmPriceType;
    }

    public void setFilmPriceType(FilmPriceType filmPriceType) {
        this.filmPriceType = filmPriceType;
    }

    @JsonIgnore
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", filmPriceType=" + filmPriceType +
                ", orders=" + orders +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        return id != null ? id.equals(film.id) : film.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
