package com.videorental.backend.endpoint;

import java.util.ArrayList;
import java.util.List;

import com.videorental.backend.model.Film;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestWebController {

    List<Film> filmList = new ArrayList<Film>();

    @RequestMapping(value = "/getallfilm", method = RequestMethod.GET)
    public List<Film> getResource(){
        return filmList;
    }

    @RequestMapping(value="/postfilm", method=RequestMethod.POST)
    public String postFilm(@RequestBody Film film){
        filmList.add(film);
        return "Sucessful!";
    }
}