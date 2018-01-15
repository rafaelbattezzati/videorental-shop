package com.videorental.backend.endpoint;

import com.videorental.backend.model.*;
import com.videorental.backend.repository.*;
import com.videorental.backend.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("rent")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RentEndpoint {

    private final FilmRepository filmRepository;
    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;
    private final ClientRepository clientRepository;
    private final PaymentRepository paymentRepository;
    private final DeliveryRepository deliveryRepository;

    @Autowired
    public RentEndpoint (FilmRepository filmRepository,
                         OrderRepository orderRepository,
                         BasketRepository basketRepository,
                         ClientRepository clientRepository,
                         PaymentRepository paymentRepository,
                         DeliveryRepository deliveryRepository){
        this.filmRepository = filmRepository;
        this.orderRepository = orderRepository;
        this.basketRepository = basketRepository;
        this.clientRepository = clientRepository;
        this.paymentRepository = paymentRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll () {
        List<Film> filmList = filmRepository.findAll();
        List<Item> itemFilms = new ArrayList<>();
        for (Film f : filmList){
            Item item = new Item(f);
            itemFilms.add(item);
        }
        return new ResponseEntity<>(itemFilms, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> listRentsByFilm (@PathVariable("id") Long id ) {
        List<Order> orders = orderRepository.findRentsByFilm(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value="/rentAll", method = RequestMethod.POST)
    public ResponseEntity<?> rentAll(@RequestBody Item[] itemArray){

        Payment payment = paymentRepository.findOne(new Long(1));
        Delivery delivery = deliveryRepository.findOne(new Long(1));
        Client client = clientRepository.findOne(new Long(1));

        Basket basket = new Basket();
        basket.setClient(client);
        basket.setDelivery(delivery);
        basket.setPayment(payment);

        //Set orders = new HashSet();
        //orders.add(order);
        //basket.setOrders(orders);
        basket = basketRepository.save(basket);

        for (Item item : itemArray) {
            Order order = new Order(
                    item.getFilm().getId(),
                    1,
                    DateUtil.getDate(item.getStartRentDate()),
                    DateUtil.getDate(item.getExpectedEndRentDate()),
                    DateUtil.getDate(item.getEndRentDate()),
                    item.getFilm(),
                    item.getExpectedPrice(),
                    item.getFinalPrice());
            order.setBasket(basket);
            order.setOrderId(basket.getId());
            orderRepository.save(order);
        }
        return new ResponseEntity<>(itemArray, HttpStatus.OK);
    }
}
