package com.example.marketplace.controller;

import com.example.marketplace.model.ListaDorinte;
import com.example.marketplace.other.SchimbareParola;
import com.example.marketplace.security.JWT;
import com.example.marketplace.model.UserCredentials;
import com.example.marketplace.model.Utilizator;
import com.example.marketplace.service.ListaDorinteService;
import com.example.marketplace.service.UtilizatorService;
import org.apache.coyote.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/utilizator")
public class UtilizatorController {

    private UtilizatorService utilizatorService;
    private static final Logger LOGGER = LogManager.getLogger(UtilizatorController.class);
    private JWT jsonWebToken;
    private ListaDorinteService listaDorinteService;

    @Autowired
    public UtilizatorController(UtilizatorService utilizatorService, JWT jsonWebToken, ListaDorinteService listaDorinteService) {
        this.utilizatorService = utilizatorService;
        this.jsonWebToken=jsonWebToken;
        this.listaDorinteService=listaDorinteService;
    }


        @GetMapping("/findAll")
    public ResponseEntity<List<Utilizator>> findAllUsers() {
        try {

            List<Utilizator>users=utilizatorService.findAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }


    @GetMapping("/findUtilizatorById")
    public ResponseEntity<Utilizator> findUserById(@RequestHeader("Authorization") String header) {
        try {

            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            Utilizator utilizator = utilizatorService.findUserById(userId);
            return ResponseEntity.ok(utilizator);

        } catch (Exception e) {

            LOGGER.error("An exception occurred during finding a user");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping("/findUtilizatorByUsername/{username}")
    public Utilizator findUtilizatorByUsername(@PathVariable String username) {
        try {
            return utilizatorService.findUtilizatorByUsername(username);
        } catch (Exception e) {
            LOGGER.error("An exception occurred during trying to update a user with username {}", username, e);
        }
        return null;
    }




    @DeleteMapping("/deleteUtilizator/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable long id,@RequestHeader("Authorization") String header) {
        try {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            if(id!=userId)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();


            utilizatorService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            LOGGER.error("An exception occurred during trying to delete a user with id {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("/updateUtilizator")
    public ResponseEntity<Utilizator> updateUtilizator(@RequestBody Utilizator utilizator,@RequestHeader("Authorization") String header) {
        try {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            if(userId!=utilizator.getIdUtilizator())
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();


            Utilizator user=utilizatorService.updateUtilizator(utilizator);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            LOGGER.error("An exception occurred during trying to update a user with name {}", utilizator.getNumeUtilizator(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("/makeUtilizatorAnAdmin/{idUtilizator}")
    public ResponseEntity<Utilizator> updateUtilizatorStatusToAdmin(@PathVariable long idUtilizator) {
        try {
            Utilizator user = utilizatorService.updateUtilizatorStatusToAdmin(idUtilizator);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @PutMapping("/makeUtilizatorAUser/{idUtilizator}")
    public ResponseEntity<Utilizator> updateUtilizatorStatusToUser(@PathVariable long idUtilizator) {
        try {
            Utilizator user=utilizatorService.updateUtilizatorStatusToUser(idUtilizator);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @PutMapping("/schimbareParolaUtilizator")
    public ResponseEntity<Utilizator> updateParolaUtilizator(@RequestBody SchimbareParola parole,@RequestHeader("Authorization") String header)
    {
        try {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            Utilizator utilizator=utilizatorService.findUserById(userId);


           if(!(utilizator.getParolaUtilizator().equals(parole.getParolaVeche())))
               return ResponseEntity.status(HttpStatus.FORBIDDEN).build();


            Utilizator updatedUser=utilizatorService.updateParolaUtilizator(userId, parole.getParolaNoua());
            return ResponseEntity.ok(updatedUser);


        } catch (Exception e) {
            LOGGER.error("An exception occurred during trying to change password for a user ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @PostMapping("saveUser")

    @CrossOrigin(origins = "*", allowedHeaders = "Origin, X-Requested-With, Content-Type, Accept, Authorization", allowCredentials = "true")
    public ResponseEntity<Utilizator> saveUser(@RequestBody Utilizator utilizator) {
        try {
            String username=utilizator.getUsernameUtilizator();
            String parola=utilizator.getParolaUtilizator();

            Utilizator user=utilizatorService.saveUser(utilizator);

            Utilizator utilizatorsavedDb=utilizatorService.findUtilizatorByUsername(username);
            if(utilizatorsavedDb.getParolaUtilizator().equals(parola))
            {
                String token=jsonWebToken.generateToken(utilizatorsavedDb);
                utilizatorsavedDb.setToken(token);
                ListaDorinte listaUtilizator=listaDorinteService.createListaDorinteToUser(utilizatorsavedDb.getIdUtilizator());
                if(listaUtilizator==null)
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                return ResponseEntity.ok(utilizatorsavedDb);
            }


            return ResponseEntity.badRequest().build();

        } catch (Exception e) {
            LOGGER.error("An exception occurred during trying to save a user ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Utilizator> logIn(@RequestBody UserCredentials userCredentials)
    {
        Utilizator userDb=utilizatorService.findUtilizatorByUsername(userCredentials.getUsername());
        if(userDb==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if(userDb.getParolaUtilizator().equals(userCredentials.getParola())) {
            String token = jsonWebToken.generateToken(userDb);
            userDb.setToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(userDb);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


}
