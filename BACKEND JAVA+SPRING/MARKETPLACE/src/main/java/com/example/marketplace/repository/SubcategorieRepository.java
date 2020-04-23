package com.example.marketplace.repository;

import com.example.marketplace.model.Subcategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface SubcategorieRepository<List> extends JpaRepository<Subcategorie, Long> {

    @Query(value="SELECT * FROM subcategorie INNER JOIN categorie ON subcategorie.id_categorie=categorie.id_categorie AND categorie.nume_categorie=?1", nativeQuery=true)
    Set<Subcategorie> findSubcategoriiInCategorie(String numeCategorie);


    @Query(value="SELECT subcategorie.id_categorie FROM subcategorie WHERE nume_subcategorie=?1", nativeQuery = true)
    int findIdCategoryForSubcategory(String subcategoryName);

    @Query(value="SELECT subcategorie.nume_subcategorie FROM subcategorie WHERE subcategorie.id_categorie=2;", nativeQuery = true)
    Set<String> findSubcategoriesByCategoryId(int id_category);
}
