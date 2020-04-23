package com.example.marketplace.service;

import com.example.marketplace.controller.UtilizatorController;
import com.example.marketplace.model.UserCredentials;
import com.example.marketplace.model.Utilizator;
import com.example.marketplace.repository.RolUtilizatorRepository;
import com.example.marketplace.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Service
@Transactional
public class UtilizatorServiceImpl implements UtilizatorService {

    private UtilizatorRepository utilizatorRepository;
    private RolUtilizatorRepository rolUtilizatorRepository;
    private static final Logger LOGGER = LogManager.getLogger(UtilizatorController.class);


    @Autowired
    public UtilizatorServiceImpl(UtilizatorRepository utilizatorRepository, RolUtilizatorRepository rolUtilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
        this.rolUtilizatorRepository = rolUtilizatorRepository;
    }


    @Override
    public Utilizator findUserById(Long id) {

        return utilizatorRepository.findById(id).get();
    }

    @Override
    public List<Utilizator> findAllUsers() {
        return utilizatorRepository.findAll();
    }

    @Override
    public Utilizator findUtilizatorByUsername(String username) {
        return utilizatorRepository.findUtilizatorByUsername(username);
    }

    @Override
    public Utilizator saveUser(Utilizator utilizator) {
        return utilizatorRepository.save(utilizator);
    }

    @Override
    public void deleteUserById(Long id) {
        utilizatorRepository.deleteById(id);
    }

    @Override
    public Utilizator updateUtilizator(Utilizator utilizator) {
        long id = utilizator.getIdUtilizator();
        Utilizator utilizatorUpdate = utilizatorRepository.findById(id).get();
        utilizatorUpdate.setNumeUtilizator(utilizator.getNumeUtilizator());
        utilizatorUpdate.setUsernameUtilizator(utilizator.getUsernameUtilizator());
        utilizatorUpdate.setOrasUtilizator(utilizator.getOrasUtilizator());
        utilizatorUpdate.setAdresaUtilizator(utilizator.getAdresaUtilizator());
        utilizatorUpdate.setTelefonUtilizator(utilizator.getTelefonUtilizator());
        utilizatorUpdate.setImagineUtilizator(utilizator.getImagineUtilizator());

        utilizatorRepository.save(utilizatorUpdate);

        return utilizatorUpdate;
    }

    @Override
    public Utilizator updateUtilizatorStatusToAdmin(long idUtilizator) {
        Utilizator utilizatorUpdate = utilizatorRepository.findById(idUtilizator).get();
        utilizatorUpdate.setRolUtilizator(null);
        utilizatorUpdate.setRolUtilizator(rolUtilizatorRepository.findById((long) 2).get());
        utilizatorRepository.save(utilizatorUpdate);

        return utilizatorUpdate;
    }

    @Override
    public Utilizator updateUtilizatorStatusToUser(long idUtilizator) {
        Utilizator utilizatorUpdate = utilizatorRepository.findById(idUtilizator).get();
        utilizatorUpdate.setRolUtilizator(rolUtilizatorRepository.findById((long) 1).get());

        utilizatorRepository.save(utilizatorUpdate);

        return utilizatorUpdate;
    }

    @Override
    public Utilizator updateParolaUtilizator(long id, String parolaNoua) {

        Utilizator utilizatorUpdate = utilizatorRepository.findById(id).get();
        if (utilizatorUpdate!=null) {
            utilizatorUpdate.setParolaUtilizator(parolaNoua);
            utilizatorRepository.save(utilizatorUpdate);
        }
        return utilizatorUpdate;
    }

    @Override
    public Utilizator findUserByUsername(UserCredentials user)
    {
        try
        {
            Utilizator userDb=utilizatorRepository.findUtilizatorByUsername(user.getUsername());
            if(userDb==null)
            return null;
            else {
                return userDb;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
