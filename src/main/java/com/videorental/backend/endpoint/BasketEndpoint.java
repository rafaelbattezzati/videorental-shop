package com.videorental.backend.endpoint;

import com.videorental.backend.error.CustomErrorType;
import com.videorental.backend.model.*;
import com.videorental.backend.repository.FilmRepository;
import com.videorental.backend.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("basket")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasketEndpoint {

    private final DateUtil dateUtil;
    private final FilmRepository filmRepository;

    @Autowired
    public BasketEndpoint ( DateUtil dateUtil, FilmRepository filmRepository ) {
        this.filmRepository = filmRepository;
        this.dateUtil = dateUtil;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> basket(HttpSession session){
        List<Item> itens = new ArrayList<>();
        /*
        if(session.getAttribute("basket") == null){
            Film f1 = filmRepository.findOne(new Long(11));
            Film f2 = filmRepository.findOne(new Long(15));
            Item i1 = new Item(new Long(1), f1, 1,20171221, 20171225, 0, 0, 0);
            Item i2 = new Item(new Long(2), f2, 1, 20171223, 20171225, 0, 0, 0);
            itens.add(i1);
            itens.add(i2);
        } else {
            itens = (List) session.getAttribute("basket");
        }*/
        return new ResponseEntity<>(itens, HttpStatus.OK);

    }

    /*
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll () {
        //System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(Basket.basketList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> getOrderById ( @PathVariable("id") int id ) {
        List<Basket> results = new ArrayList<Basket>();
        for(Basket b : Basket.basketList){
            if(b.getId() == id) {
                    results.add(b);
            }
        }
        return new ResponseEntity<Object>(results, HttpStatus.OK);
    } */



}
