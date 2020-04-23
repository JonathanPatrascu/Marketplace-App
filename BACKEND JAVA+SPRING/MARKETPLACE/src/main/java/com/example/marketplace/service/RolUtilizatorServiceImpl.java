package com.example.marketplace.service;

import com.example.marketplace.model.RolUtilizator;
import com.example.marketplace.repository.RolUtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolUtilizatorServiceImpl implements RolUtilizatorService {


    private RolUtilizatorRepository rolUtilizatorRepository;

    @Autowired
    public RolUtilizatorServiceImpl(RolUtilizatorRepository rolUtilizatorRepository) {
        this.rolUtilizatorRepository = rolUtilizatorRepository;
    }

    @Override
    public RolUtilizator findRolById(Long id) {
        return rolUtilizatorRepository.findById(id).get();
    }

    @Override
    public RolUtilizator saveRolUtilizator(RolUtilizator rol) {
        rolUtilizatorRepository.save(rol);
        return rol;
    }

    @Override
    public List<RolUtilizator> findAllRoles() {
        return rolUtilizatorRepository.findAll();
    }

    @Override
    public void deleteRoleById(Long id) {
        rolUtilizatorRepository.deleteById(id);

    }

    @Override
    public RolUtilizator updateRole(RolUtilizator rolUtilizator) {

        long id = rolUtilizator.getIdRolUtilizator();
        RolUtilizator rol = rolUtilizatorRepository.findById(id).get();
        rol.setNumeRolUtilizator(rolUtilizator.getNumeRolUtilizator());
        rolUtilizatorRepository.save(rol);

        return rol;

    }
}
