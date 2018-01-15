package com.videorental.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private Client client;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "film")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    @JsonIgnore
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    @JsonIgnore
    private Payment payment;

    //@Transient
    //private List<Order> orderList;

    static {
        createBasketList();
    }

    public Basket ( Set<Order> orders, Client client, Delivery delivery, Payment payment) {
        this.client = client;
        this.orders = orders;
        this.delivery = delivery;
        this.payment = payment;
    }

    public Basket () {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @JsonIgnore
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals ( Object o ) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Basket basket = (Basket) o;

        return id == basket.id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", client=" + client +
                ", orders=" + orders +
                ", delivery=" + delivery +
                ", payment=" + payment +
                '}';
    }

    public static void createBasketList(){

        /*
        basketList = new ArrayList<>();
        Basket b1 = new Basket();
        b1.setId(1);
        b1.setClient(new Client(1, "Rafael"));

        Basket b2 = new Basket();
        b2.setId(2);
        b2.setClient(new Client(2, "Silma"));

        Basket b3 = new Basket();
        b3.setId(3);
        b3.setClient(new Client(3, "Zaneta"));

        Film f2 = new Film(new Long(2), "SpiderMan", FilmPriceTypeEnum.REGULAR_FILM_PRICE, FilmPriceTypeEnum.REGULAR_FILM_PRICE.getDescription());

        Film f3 = new Film(new Long(3), "SpiderMan 2", FilmPriceTypeEnum.REGULAR_FILM_PRICE, FilmPriceType.REGULAR_FILM_PRICE.getDescription());
        Film f4 = new Film(new Long(4), "Old Africa" , FilmPriceTypeEnum.OLD_FILM_PRICE, FilmPriceType.OLD_FILM_PRICE.getDescription());
        Film f1 = new Film(new Long(1), "Matrix 11"  , FilmPriceTypeEnum.NEW_RELEASES_PRICE,FilmPriceType.NEW_RELEASES_PRICE.getDescription());

        List<Order> orderList = new ArrayList<>(asList(
                new Order(20171201, 20171208, 0, f2, 0.0, 0.0),
                new Order(20171201, 20171215, 0, f3, 0.0, 0.0)
        ));

        List<Order> orderList2 = new ArrayList<>(asList(
                new Order(20171210, 20171222, 0, f4, 0.0, 0.0)
        ));

        List<Order> orderList3 = new ArrayList<>(asList(
                new Order(20171215, 20171230, 0, f1, 0.0, 0.0)
        ));

        b1.setOrderList(orderList);
        b2.setOrderList(orderList2);
        b3.setOrderList(orderList3);
        basketList.add(b1);
        basketList.add(b2);
        basketList.add(b3);
        */
    }
}
