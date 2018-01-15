package com.videorental.backend.repository;

import com.videorental.backend.model.Client;
import com.videorental.backend.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNameIgnoreCaseContaining(String name);

    List<Client> findByLastNameIgnoreCaseContaining(String lastName);

}
