package com.videorental.backend.repository;

import com.videorental.backend.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByNameIgnoreCaseContaining(String name);

}