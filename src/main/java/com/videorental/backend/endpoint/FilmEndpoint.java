package com.videorental.backend.endpoint;

import com.videorental.backend.error.CustomErrorType;
import com.videorental.backend.error.ResourceNotFoundException;
import com.videorental.backend.model.Film;
import com.videorental.backend.repository.FilmPriceTypeRepository;
import com.videorental.backend.repository.FilmRepository;
import com.videorental.backend.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("films")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FilmEndpoint {

    private final DateUtil dateUtil;
    private final FilmPriceTypeRepository filmPriceTypeRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public FilmEndpoint(DateUtil dateUtil, FilmPriceTypeRepository filmPriceTypeRepository, FilmRepository filmRepository) {
        this.dateUtil = dateUtil;
        this.filmPriceTypeRepository = filmPriceTypeRepository;
        this.filmRepository = filmRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAll () {
        //System.out.println(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        List filmList = filmRepository.findAll();
        return new ResponseEntity<>(filmList, HttpStatus.OK);
    }

    @RequestMapping(path = "/getAllFilmPriceType", method = RequestMethod.GET)
    public ResponseEntity<?> getAllFilmPriceType () {
        List filmPriceTypeList = filmPriceTypeRepository.findAll();
        return new ResponseEntity<>(filmPriceTypeList, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> getFilmById ( @PathVariable("id") Long id ) {
        verifyIfFilmExists(id);
        Film film = filmRepository.findOne(id);
        return new ResponseEntity<Object>(film, HttpStatus.OK);
    }

    @GetMapping(path = "/findFilmByName/{name}" )
    public ResponseEntity<?> findFilmByName(@PathVariable String name){
        return new ResponseEntity<Object>(filmRepository.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<?> save ( @RequestBody Film film) {
        filmRepository.save(film);
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Long id) {
        verifyIfFilmExists(id);
        filmRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<?> update (@RequestBody Film film, @PathVariable("id") Long id) {
        verifyIfFilmExists(id);
        filmRepository.save(film);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfFilmExists(Long id){
        if(filmRepository.getOne(id) == null){
            throw new ResourceNotFoundException("Film not found for id :"+id);
        }
    }
}
