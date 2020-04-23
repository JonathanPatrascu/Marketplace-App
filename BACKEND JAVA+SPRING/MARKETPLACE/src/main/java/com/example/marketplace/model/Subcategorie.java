package com.example.marketplace.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "subcategorie")
public class Subcategorie {
    @Id
    @Column(name = "id_subcategorie", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSubcategorie;
    @Column(name = "nume_subcategorie", nullable = false)
    private String numeSubcategorie;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categorie categorieSubcategorie;

    public long getIdSubcategorie() {
        return idSubcategorie;
    }

    public void setIdSubcategorie(long idSubcategorie) {
        this.idSubcategorie = idSubcategorie;
    }

    public String getNumeSubcategorie() {
        return numeSubcategorie;
    }

    public void setNumeSubcategorie(String numeSubcategorie) {
        this.numeSubcategorie = numeSubcategorie;
    }


    public Categorie getCategorieSubcategorie() {
        return categorieSubcategorie;
    }

    public void setCategorieSubcategorie(Categorie categorieSubcategorie) {
        this.categorieSubcategorie = categorieSubcategorie;
    }

    @Override
    public String toString() {
        return "Subcategorie{" +
                "idSubcategorie=" + idSubcategorie +
                ", numeSubcategorie='" + numeSubcategorie + '\'' +

                '}';
    }
}
