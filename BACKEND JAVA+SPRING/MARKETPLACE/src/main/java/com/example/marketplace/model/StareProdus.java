package com.example.marketplace.model;

import javax.persistence.*;

@Entity
@Table(name = "stare_produs")
public class StareProdus {
    @Id
    @Column(name = "id_stare_produs", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idStareProdus;
    @Column(name = "descriere_stare_produs", nullable = false)
    private String descriereStareProdus;

    public long getIdStareProdus() {
        return idStareProdus;
    }

    public void setIdStareProdus(long idStareProdus) {
        this.idStareProdus = idStareProdus;
    }

    public String getDescriereStareProdus() {
        return descriereStareProdus;
    }

    public void setDescriereStareProdus(String descriereStareProdus) {
        this.descriereStareProdus = descriereStareProdus;

    }

    @Override
    public String toString() {
        return "StareProdus{" +
                "idStareProdus=" + idStareProdus +
                ", descriereStareProdus='" + descriereStareProdus + '\'' +
                '}';
    }
}
