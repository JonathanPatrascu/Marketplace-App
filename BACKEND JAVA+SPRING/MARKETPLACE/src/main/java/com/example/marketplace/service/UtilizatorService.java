package com.example.marketplace.service;

import com.example.marketplace.model.UserCredentials;
import com.example.marketplace.model.Utilizator;

import java.util.List;

public interface UtilizatorService {

    public Utilizator findUserById(Long id);

    public List<Utilizator> findAllUsers();

    public Utilizator findUtilizatorByUsername(String username);

    public Utilizator saveUser(Utilizator utilizator);

    public void deleteUserById(Long id);

    public Utilizator updateUtilizator(Utilizator utilizator);

    public Utilizator updateUtilizatorStatusToAdmin(long idUtilizator);

    public Utilizator updateUtilizatorStatusToUser(long idUtilizator);

    public Utilizator updateParolaUtilizator(long id, String parolaNoua);

    public Utilizator findUserByUsername(UserCredentials user);


}
