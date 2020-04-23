package com.example.marketplace.service;

import com.example.marketplace.model.Produs;

import java.util.List;
import java.util.Set;

public interface ProdusService {

    public List<Produs> findAllProduse();

    public Produs findProdusById(long id);

    public Set<Produs> findProdusByCategorie(String numeCategorie);

    public Set<Produs> findProdusBySubcategorie(String numeSubcategorie);

    public List<Produs> findProdusByDescriere(String stringCautare);

    public Produs saveProdus(Produs produs);

    public Produs updateProdus(Produs produs);

    public void deleteProdus(long id);

    public Set<Produs> findProductsByPrice(float pretStart, float pretStop);

    public Set<String> findAllBrands();

    public Set<String> findBrandsByCategorie(long idCategorie);

    public Set<String> findBrandsBySubcategorie(long idSubcategorie);

    public List<Produs> findProductsForUser(Long idUser);

}

