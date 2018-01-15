package com.videorental.backend.repository;

import com.videorental.backend.model.FilmPriceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmPriceTypeRepository extends JpaRepository<FilmPriceType, Long> {

}
