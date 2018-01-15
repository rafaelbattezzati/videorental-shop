package com.videorental.backend.endpoint;

import com.videorental.backend.model.Order;
import com.videorental.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderEndpoint {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderEndpoint(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll () {
        List orderList = orderRepository.findAll();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path="/open")
    public ResponseEntity<?> listAllOpenOrders () {
        List orderList = orderRepository.findOpenOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path="/returned")
    public ResponseEntity<?> listAllReturnedOrders () {
        List orderList = orderRepository.findReturnedOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ordersByClient/{id}")
    public ResponseEntity<?> getOrdersByClient (@PathVariable("id") Long id ) {
        List orderList = orderRepository.findRentsByClient(id);
        return new ResponseEntity<Object>(orderList, HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update (@RequestBody Order order) {
        Order.calculateFinalPrice(order);
        orderRepository.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
