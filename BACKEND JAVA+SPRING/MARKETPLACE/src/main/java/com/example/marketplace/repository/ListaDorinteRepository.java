package com.example.marketplace.repository;

import com.example.marketplace.model.ListaDorinte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ListaDorinteRepository extends JpaRepository<ListaDorinte, Long> {
    @Query(value = "SELECT * FROM lista_dorinte  WHERE id_utilizator=?1", nativeQuery = true)
    ListaDorinte findWishlistForCertainUser(long idUtilizator);

}
