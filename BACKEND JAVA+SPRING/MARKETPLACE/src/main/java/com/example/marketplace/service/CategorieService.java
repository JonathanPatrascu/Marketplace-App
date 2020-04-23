package com.example.marketplace.service;

import com.example.marketplace.model.Categorie;
import com.example.marketplace.model.Subcategorie;

import java.util.List;
import java.util.Set;

public interface CategorieService {

    public Categorie findCategoryById(long id);

    public List<Categorie> findAllCategories();

    public void deleteCategory(long id);

    public Categorie saveCategory(Categorie categorie);

    public Categorie updateCategory(Categorie categorie);

    public Set<Subcategorie> findSubcategoriiInCategorie(String numeCategorie);

    public Set<String> findSubcategoriesOfSubcategory(String subcategoryName);

    public Categorie findCategorieByIdSubcategorie(Long idSubcategorie);
}
