package com.videorental.backend.endpoint;

import com.videorental.backend.model.Client;
import com.videorental.backend.model.Film;
import com.videorental.backend.repository.*;
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
@RequestMapping("client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientEndPoint {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientEndPoint (ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> getClientById ( @PathVariable("id") Long id ) {
        Client client = clientRepository.findOne(id);
        return new ResponseEntity<Object>(client, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll () {
        List clientList = clientRepository.findAll();
        return new ResponseEntity<>(clientList, HttpStatus.OK);
    }

    @GetMapping(path = "/findClientByName/{name}" )
    public ResponseEntity<?> findClientByName(@PathVariable String name){
        return new ResponseEntity<Object>(clientRepository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @GetMapping(path = "/findClientByLastName/{lastName}" )
    public ResponseEntity<?> findClientByLastName(@PathVariable String lastName){
        return new ResponseEntity<Object>(clientRepository.findByLastNameIgnoreCaseContaining(lastName), HttpStatus.OK);
    }
}
