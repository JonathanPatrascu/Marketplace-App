package com.example.marketplace.service;

import com.example.marketplace.model.Categorie;
import com.example.marketplace.model.Subcategorie;
import com.example.marketplace.repository.CategorieRepository;
import com.example.marketplace.repository.SubcategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class CategorieServiceImpl implements CategorieService {

    private CategorieRepository categorieRepository;
    private SubcategorieRepository subcategorieRepository;

    @Autowired
    public CategorieServiceImpl(CategorieRepository categorieRepository,SubcategorieRepository subcategorieRepository) {
        this.categorieRepository = categorieRepository;
        this.subcategorieRepository=subcategorieRepository;
    }

    @Override
    public Categorie findCategoryById(long id) {
        return categorieRepository.findById(id).get();
    }

    @Override
    public List<Categorie> findAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public void deleteCategory(long id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public Categorie saveCategory(Categorie categorie) {

        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategory(Categorie categorie) {

        long id = categorie.getIdCategorie();
        Categorie cat = categorieRepository.findById(id).get();
        cat.setNumeCategorie(categorie.getNumeCategorie());
        cat.setSubcategoriiInCategorie(null);
        cat.setSubcategoriiInCategorie(categorie.getSubcategoriiInCategorie());

        categorieRepository.save(cat);
        return cat;

    }

    @Override
    public Set<Subcategorie> findSubcategoriiInCategorie(String numeCategorie) {
        return subcategorieRepository.findSubcategoriiInCategorie(numeCategorie);
    }

    @Override
    public Set<String> findSubcategoriesOfSubcategory(String subcategoryName) {

        int idCategorie=subcategorieRepository.findIdCategoryForSubcategory(subcategoryName);
        return subcategorieRepository.findSubcategoriesByCategoryId(idCategorie);

    }

    @Override
    public Categorie findCategorieByIdSubcategorie(Long idSubcategorie) {
        long idCatgorie= categorieRepository.findIdCategorieByIdSubcategorie(idSubcategorie);
        return categorieRepository.findById(idCatgorie).get();
    }
}
