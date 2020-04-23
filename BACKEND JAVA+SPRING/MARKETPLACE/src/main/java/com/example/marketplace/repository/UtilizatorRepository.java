package com.example.marketplace.repository;

import com.example.marketplace.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilizatorRepository extends JpaRepository<Utilizator, Long> {

    @Query(value = "SELECT * FROM utilizator WHERE username_utilizator=?1", nativeQuery = true)
    Utilizator findUtilizatorByUsername(String username);

}
