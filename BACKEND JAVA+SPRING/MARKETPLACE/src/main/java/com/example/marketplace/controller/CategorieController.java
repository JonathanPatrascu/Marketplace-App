package com.example.marketplace.controller;


import com.example.marketplace.model.Categorie;
import com.example.marketplace.model.Subcategorie;
import com.example.marketplace.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/categorie")
@Transactional

public class CategorieController {

    private CategorieService categorieService;

    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("/findById/{id}")
    public Categorie findById(@PathVariable Long id)
    {
        return categorieService.findCategoryById(id);
    }

    @GetMapping("/findAll")
    public List<Categorie> findAllCategories()
    {
        return categorieService.findAllCategories();
    }


    @PostMapping("/saveCategory")
    public Categorie saveCategory(@RequestBody Categorie categorie)
    {
        return categorieService.saveCategory(categorie);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public void deleteCategory(@PathVariable long id)
    {
        categorieService.deleteCategory(id);
    }

    @PutMapping("/updateCategory")
    public String updateCategory(@RequestBody Categorie categorie)
    {
        categorieService.updateCategory(categorie);
        return "Category Updated";
    }


    @GetMapping(value="subcategoriiInCategorie/{numeCategorie}")
    public ResponseEntity<Set<Subcategorie>> findSubcategotiiInCategorii(@PathVariable String numeCategorie)
    {
        try
        {
            Set<Subcategorie> subcategorii=categorieService.findSubcategoriiInCategorie(numeCategorie);
            return ResponseEntity.ok(subcategorii);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping(value="subcategoriiFor/{numeSubcategorie}")
    public ResponseEntity<Set<String>> findSubcategotiiForSubcategory(@PathVariable String numeSubcategorie)
    {
        try
        {
            Set<String> subcategorii=categorieService.findSubcategoriesOfSubcategory(numeSubcategorie);
            return ResponseEntity.ok(subcategorii);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
