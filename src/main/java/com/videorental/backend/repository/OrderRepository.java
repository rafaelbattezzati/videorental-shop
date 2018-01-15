package com.videorental.backend.repository;

import com.videorental.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    public static String FIND_BY_FILM_QUERY =
            "SELECT o FROM Order o LEFT JOIN o.film "+
            "WHERE o.filmId = :filmId "+
            "AND o.endRentDate IS NOT null "+
            "AND o.finalPrice != 0 "+
            "ORDER BY o.orderId DESC";

    public static String FIND_ORDERS_BY_CLIENT_QUERY =
            "SELECT o from Order o, Basket b, Client c " +
            "WHERE o.orderId = b.id "+
            "AND b.client = c.id "+
            "AND o.endRentDate IS null "+
            "AND o.finalPrice = 0 "+
            "AND c.id = :clientId "+
            "ORDER BY o.orderId DESC";

    public static String FIND_ORDERS_OPEN =
            "SELECT o FROM Order o LEFT JOIN o.film "+
                    "WHERE o.endRentDate IS null "+
                    "AND o.finalPrice = 0 "+
                    "ORDER BY o.orderId DESC";

    public static String FIND_ORDERS_RETURNED =
            "SELECT o FROM Order o LEFT JOIN o.film "+
                    "WHERE o.endRentDate IS NOT null "+
                    "AND o.finalPrice != 0 "+
                    "ORDER BY o.orderId DESC";

    /**
     * Find persons by address.
     */
    @Query(FIND_BY_FILM_QUERY)
    List<Order> findRentsByFilm(@Param("filmId") Long filmId);

    @Query(FIND_ORDERS_BY_CLIENT_QUERY)
    List<Order> findRentsByClient(@Param("clientId") Long clientId);

    @Query(FIND_ORDERS_OPEN)
    List<Order> findOpenOrders();

    @Query(FIND_ORDERS_RETURNED)
    List<Order> findReturnedOrders();


}
