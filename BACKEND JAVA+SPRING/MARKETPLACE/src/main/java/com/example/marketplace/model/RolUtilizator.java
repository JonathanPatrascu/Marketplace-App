package com.example.marketplace.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rol_utilizator")
public class RolUtilizator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol_utilizator", nullable = false)
    private long idRolUtilizator;
    @Column(name = "nume_rol_utilizator", nullable = false)
    private String numeRolUtilizator;

    public RolUtilizator() {
    }

    public long getIdRolUtilizator() {
        return idRolUtilizator;
    }

    public void setIdRolUtilizator(long idRolUtilizator) {
        this.idRolUtilizator = idRolUtilizator;
    }

    public String getNumeRolUtilizator() {
        return numeRolUtilizator;
    }

    public void setNumeRolUtilizator(String numrRolUtilizator) {
        this.numeRolUtilizator = numrRolUtilizator;
    }

//    public List<Utilizator> getListaUtilizatoriInRol() {

}
