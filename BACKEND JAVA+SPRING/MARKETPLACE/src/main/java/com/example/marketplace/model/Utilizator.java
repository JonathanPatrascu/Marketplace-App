package com.example.marketplace.model;

import javax.persistence.*;

@Entity
@Table(name = "utilizator")
public class Utilizator {
    @Id
    @Column(name = "id_utilizator", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUtilizator;
    @Column(name = "nume_utilizator", nullable = false)
    private String numeUtilizator;
    @Column(name = "username_utilizator", nullable = false)
    private String usernameUtilizator;
    @Column(name = "parola_utilizator", nullable = false)
    private String parolaUtilizator;
    @Column(name = "oras_utilizator", nullable = false)
    private String orasUtilizator;
    @Column(name = "adresa_utilizator", nullable = false)
    private String adresaUtilizator;
    @Column(name = "telefon_utilizator", nullable = false)
    private String telefonUtilizator;
    @Column(name = "email_utilizator", nullable = false)
    private String emailUtilizator;
    @Column(name = "imagine_utilizator",nullable=true)
    private String imagineUtilizator;
    @Transient
    private String token=null;
    @ManyToOne()
    @JoinColumn(name = "id_rol_utilizator")
    private RolUtilizator rolUtilizator;


    public Utilizator() {
    }

    public long getIdUtilizator() {
        return idUtilizator;
    }

    public void setIdUtilizator(long idUtilizator) {
        this.idUtilizator = idUtilizator;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getUsernameUtilizator() {
        return usernameUtilizator;
    }

    public void setUsernameUtilizator(String usernameUtilizator) {
        this.usernameUtilizator = usernameUtilizator;
    }

    public String getParolaUtilizator() {
        return parolaUtilizator;
    }

    public void setParolaUtilizator(String parolaUtilizator) {
        this.parolaUtilizator = parolaUtilizator;
    }

    public String getOrasUtilizator() {
        return orasUtilizator;
    }

    public void setOrasUtilizator(String orasUtilizator) {
        this.orasUtilizator = orasUtilizator;
    }

    public String getAdresaUtilizator() {
        return adresaUtilizator;
    }

    public void setAdresaUtilizator(String adresaUtilizator) {
        this.adresaUtilizator = adresaUtilizator;
    }

    public String getTelefonUtilizator() {
        return telefonUtilizator;
    }

    public void setTelefonUtilizator(String telefonUtilizator) {
        this.telefonUtilizator = telefonUtilizator;
    }

    public String getEmailUtilizator() {
        return emailUtilizator;
    }

    public void setEmailUtilizator(String emailUtilizator) {
        this.emailUtilizator = emailUtilizator;
    }

    public String getImagineUtilizator() {
        return imagineUtilizator;
    }

    public void setImagineUtilizator(String imagineUtilizator) {
        this.imagineUtilizator = imagineUtilizator;
    }

    public RolUtilizator getRolUtilizator() {
        return rolUtilizator;
    }

    public void setRolUtilizator(RolUtilizator rolUtilizator) {
        this.rolUtilizator = rolUtilizator;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "idUtilizator=" + idUtilizator +
                ", numeUtilizator='" + numeUtilizator + '\'' +
                ", usernameUtilizator='" + usernameUtilizator + '\'' +
                ", parolaUtilizator='" + parolaUtilizator + '\'' +
                ", orasUtilizator='" + orasUtilizator + '\'' +
                ", adresaUtilizator='" + adresaUtilizator + '\'' +
                ", telefonUtilizator='" + telefonUtilizator + '\'' +
                ", emailUtilizator='" + emailUtilizator + '\'' +
                ", imagineUtilizator='" + imagineUtilizator + '\'' +
                ", rolUtilizator=" + rolUtilizator +
//                ", listaProduseUtilizator=" + listaProduseUtilizator +
                '}';
    }
}
