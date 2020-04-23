package com.example.marketplace.service;

import com.example.marketplace.dto.RolUtilizatorDTO;
import com.example.marketplace.model.RolUtilizator;

import java.util.List;

public interface RolUtilizatorService {

    public RolUtilizator findRolById(Long id);

    public List<RolUtilizator> findAllRoles();

    public RolUtilizator saveRolUtilizator(RolUtilizator rol);

    public void deleteRoleById(Long id);

    public RolUtilizator updateRole(RolUtilizator rolUtilizator);
}
