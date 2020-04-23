package com.example.marketplace.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lista_dorinte")
public class ListaDorinte {
    @Id
    @Column(name = "id_lista_dorinte", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idListaDorinte;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_utilizator", unique = true)
    private Utilizator utilizator;

    @ManyToMany()
    @JoinTable(name = "produse_dorite", joinColumns = {@JoinColumn(name = "idListaDorinte")}, inverseJoinColumns = {@JoinColumn(name = "id_produs")})
    private List<Produs> listaProduseDorite = new ArrayList<Produs>();


    public ListaDorinte() {
    }

    public long getIdListaDorinte() {
        return idListaDorinte;
    }

    public void setIdListaDorinte(long idListaDorinte) {
        this.idListaDorinte = idListaDorinte;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public List<Produs> getListaProduseDorite() {
        return listaProduseDorite;
    }

    public void setListaProduseDorite(List<Produs> listaProduseDorite) {
        this.listaProduseDorite = listaProduseDorite;
    }

    public List<Produs> addProdustoListaDorinte(Produs p) {
        this.listaProduseDorite.add(p);
        return this.listaProduseDorite;
    }
}
