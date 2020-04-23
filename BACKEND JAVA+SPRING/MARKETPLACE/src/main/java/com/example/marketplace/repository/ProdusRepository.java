package com.example.marketplace.repository;

import com.example.marketplace.model.Produs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ProdusRepository extends JpaRepository<Produs, Long> {
    @Query(value="SELECT brand_produs FROM produs ORDER BY brand_produs ASC", nativeQuery = true)
    Set<String> findAllBrands();

    @Query(value="SELECT brand_produs FROM produs WHERE id_categorie=?1 order by brand_produs ASC", nativeQuery = true)
    Set<String> findBrandsByCategorie(long idCtagorie);

    @Query(value="SELECT brand_produs FROM produs WHERE id_subcategorie=?1 order by brand_produs ASC", nativeQuery = true)
    Set<String> findBrandsBySubcategorie(long idSubcategorie);

    @Query(value="SELECT* FROM produs INNER JOIN categorie ON produs.id_categorie=categorie.id_categorie AND categorie.nume_categorie=?1", nativeQuery = true)
    Set<Produs> findProdusByCategorie(String numeCategorie);

    @Query(value="SELECT* FROM produs INNER JOIN subcategorie ON produs.id_subcategorie=subcategorie.id_subcategorie AND subcategorie.nume_subcategorie=?1", nativeQuery = true)
    Set<Produs> findProdusBySubcategorie(String numeSubcategorie);

    @Query(value="SELECT * FROM produs WHERE pret_produs BETWEEN ?1 AND ?2", nativeQuery = true)
    Set<Produs> findProduseByPrice(float pretStart, float pretStop);

    @Query(value="SELECT * FROM produs WHERE id_utilizator=?1", nativeQuery=true)
    List<Produs> findProductsForUser(long idUser);

}
