package com.example.marketplace.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @Column(name = "id_categorie", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategorie;
    @Column(name = "nume_categorie", nullable = false)
    private String numeCategorie;

    @JsonManagedReference
    @OneToMany(mappedBy = "categorieSubcategorie", targetEntity = Subcategorie.class, cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    List<Subcategorie> subcategoriiInCategorie;

    public long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNumeCategorie() {
        return numeCategorie;
    }

    public void setNumeCategorie(String numeCategorie) {
        this.numeCategorie = numeCategorie;
    }


    public List<Subcategorie> getSubcategoriiInCategorie() {
        return subcategoriiInCategorie;
    }

    public void setSubcategoriiInCategorie(List<Subcategorie> subcategoriiInCategorie) {
        this.subcategoriiInCategorie = subcategoriiInCategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCategorie=" + idCategorie +
                ", numeCategorie='" + numeCategorie + '\'' +
                ", subcategoriiInCategorie=" + subcategoriiInCategorie +
                '}';
    }

}
