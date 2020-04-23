package com.example.marketplace.repository;

import com.example.marketplace.model.Categorie;
import com.example.marketplace.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    @Query(value = "SELECT subcategorie.id_categorie FROM subcategorie WHERE id_subcategorie=?1", nativeQuery = true)
    Long findIdCategorieByIdSubcategorie(Long idSubcategorie);

}
