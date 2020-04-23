package com.example.marketplace.controller;

import com.example.marketplace.dto.RolUtilizatorDTO;
import com.example.marketplace.model.RolUtilizator;
import com.example.marketplace.service.RolUtilizatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/rolUtilizator")
public class RolUtilizatorController {

    RolUtilizatorService rolUtilizatorService;
    private static final Logger LOGGER = LogManager.getLogger(UtilizatorController.class);

    @Autowired
    public RolUtilizatorController(RolUtilizatorService rolUtilizatorService) {
        this.rolUtilizatorService = rolUtilizatorService;
    }

    @GetMapping("/findById/{id}")
    public RolUtilizator findById(@PathVariable Long id) {

        return rolUtilizatorService.findRolById(id);
    }

    @GetMapping("/findAll")
    public List<RolUtilizator> findAllRoles() {
        try {
            return rolUtilizatorService.findAllRoles();
        } catch (Exception e) {
            LOGGER.error("An exception occurred during trying find all user roles", e);

        }
        return null;

    }

    @PostMapping("/saveUserRole")
    public RolUtilizator saveUserRole(@RequestBody RolUtilizator rol) {
        try {
            rolUtilizatorService.saveRolUtilizator(rol);
        } catch (Exception e) {
            LOGGER.error("An exception occurred during trying to save a new user role", e);
        }
        return null;
    }

    @DeleteMapping("/deleteRole/{id}")
    public void deleteRole(@PathVariable long id) {
        try {
            rolUtilizatorService.deleteRoleById(id);
        } catch (Exception e) {
            LOGGER.error("An exception occurred during trying to delete user role with id {}", id, e);

        }
    }

    @PutMapping("/updateRole")
    public void updateRole(@RequestBody RolUtilizator rolUtilizator)
    {
        try
        {
            rolUtilizatorService.updateRole(rolUtilizator);

        }
        catch (Exception e) {
            LOGGER.error("An exception occurred during trying to update user role with id {}", rolUtilizator.getIdRolUtilizator(), e);

        }
    }




}
