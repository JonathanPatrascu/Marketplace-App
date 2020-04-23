package com.example.marketplace.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "produs")
public class Produs implements Serializable {

    @Id
    @Column(name = "id_produs", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProdus;
    @Column(name = "nume_produs", nullable = false)
    private String numeProdus;
    @Column(name = "brand_produs", nullable = false)
    private String BrandProdus;
    @Column(name = "pret_produs", nullable = false)
    private float pretProdus;
    @Column(name = "transport_gratuit")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean transportGratuit;
    @Column(name = "descriere_produs", nullable = false,length = 65535, columnDefinition="TEXT")
    private String descriereProdus;
    @Column(name = "data_postare", nullable = false)
    private Date dataPostare;

    @ManyToOne(targetEntity = Categorie.class)
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categorie categorie;

    @ManyToOne(targetEntity = Subcategorie.class)
    @JoinColumn(name = "id_subcategorie", nullable = false)
    private Subcategorie subcategorie;

    @ManyToOne(targetEntity = StareProdus.class)
    @JoinColumn(name = "id_stare_produs", nullable = false)
    private StareProdus stareProdus;

    @ManyToOne()
    @JoinColumn(name = "id_utilizator")
    private Utilizator vanzator;

    @Column(name = "imagini_produs", nullable = false,length = 65535, columnDefinition="TEXT")
    private String imaginiProdus;

    public Produs() {
    }

    public Produs(String numeProdus, String brandProdus, float pretProdus, boolean transportGratuit, String descriereProdus, Date dataPostare, Categorie categorie, Subcategorie subcategorie, StareProdus stareProdus, Utilizator vanzator, String imaginiProdus) {
        this.numeProdus = numeProdus;
        BrandProdus = brandProdus;
        this.pretProdus = pretProdus;
        this.transportGratuit = transportGratuit;
        this.descriereProdus = descriereProdus;
        this.dataPostare = dataPostare;
        this.categorie = categorie;
        this.subcategorie = subcategorie;
        this.stareProdus = stareProdus;
        this.vanzator = vanzator;
        this.imaginiProdus = imaginiProdus;
    }

    public long getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(long idProdus) {
        this.idProdus = idProdus;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public String getBrandProdus() {
        return BrandProdus;
    }

    public void setBrandProdus(String brandProdus) {
        BrandProdus = brandProdus;
    }

    public float getPretProdus() {
        return pretProdus;
    }

    public void setPretProdus(float pretProdus) {
        this.pretProdus = pretProdus;
    }

    public boolean isTransportGratuit() {
        return transportGratuit;
    }

    public void setTransportGratuit(boolean transportGratuit) {
        this.transportGratuit = transportGratuit;
    }

    public String getDescriereProdus() {
        return descriereProdus;
    }

    public void setDescriereProdus(String descriereProdus) {
        this.descriereProdus = descriereProdus;
    }

    public String getDataPostare() {

        SimpleDateFormat formatorData = new SimpleDateFormat("dd/MM/yyyy");
        return formatorData.format(dataPostare);

    }

    public void setDataPostare(Date dataPostare) {

    this.dataPostare=new Date();
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Subcategorie getSubcategorie() {
        return subcategorie;
    }

    public void setSubcategorie(Subcategorie subcategorie) {
        this.subcategorie = subcategorie;
    }

    public StareProdus getStareProdus() {
        return stareProdus;
    }

    public void setStareProdus(StareProdus stareProdus) {
        this.stareProdus = stareProdus;
    }

    public Utilizator getVanzator() {
        return vanzator;
    }

    public void setVanzator(Utilizator vanzator) {
        this.vanzator = vanzator;
    }

    public String getImaginiProdus() {
        return imaginiProdus;
    }

    public void setImaginiProdus(String imaginiProdus) {
        this.imaginiProdus = imaginiProdus;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "idProdus=" + idProdus +
                ", numeProdus='" + numeProdus + '\'' +
                ", BrandProdus='" + BrandProdus + '\'' +
                ", pretProdus=" + pretProdus +
                ", transportGratuit=" + transportGratuit +
                ", descriereProdus='" + descriereProdus + '\'' +
                ", dataPostare=" + dataPostare +
                ", categorie=" + categorie +
                ", subcategorie=" + subcategorie +
                ", stareProdus=" + stareProdus +
                ", vanzator=" + vanzator +
                ", imaginiProdus=" + imaginiProdus +
                '}';
    }
}
