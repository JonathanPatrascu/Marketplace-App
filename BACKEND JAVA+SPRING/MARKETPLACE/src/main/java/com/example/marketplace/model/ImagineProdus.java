package com.example.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "imagine_produs")
public class ImagineProdus {
    @Id
    @Column(name = "id_imagine", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idImagine;

    @Column(name = "cale_imagine", nullable = false, length = 65535, columnDefinition="TEXT")
    private String caleImagine;

    @Column(name="id_produs")
    private long idProdus;

    public long getIdImagine() {
        return idImagine;
    }

    public void setIdImagine(long idImagine) {
        this.idImagine = idImagine;
    }

    public String getCaleImagine() {
        return caleImagine;
    }

    public void setCaleImagine(String caleImagine) {
        this.caleImagine = caleImagine;
    }

    public long getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(long idProdus) {
        this.idProdus = idProdus;
    }

    @Override
    public String toString() {
        return "ImagineProdus{" +
                "idImagine=" + idImagine +
                ", caleImagine='" + caleImagine + '\'' +
                ", idProdus=" + idProdus +
                '}';
    }
}
