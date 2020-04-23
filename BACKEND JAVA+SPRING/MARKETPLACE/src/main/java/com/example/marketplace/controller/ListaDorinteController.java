package com.example.marketplace.controller;


import com.example.marketplace.security.JWT;
import com.example.marketplace.model.ListaDorinte;
import com.example.marketplace.service.ListaDorinteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/listaDorinte")

public class ListaDorinteController {

    private static final Logger LOGGER = LogManager.getLogger(ProdusController.class);
    private ListaDorinteService listaDorinteService;
    private JWT jsonWebToken;



    @Autowired
    public ListaDorinteController(ListaDorinteService listaDorinteService, JWT jsonWebToken) {
        this.listaDorinteService = listaDorinteService;
        this.jsonWebToken=jsonWebToken;
    }

    @GetMapping("findListaDorinteById/{id}")
    public ResponseEntity<ListaDorinte> findListaDorinteById(@PathVariable long id,@RequestHeader("Authorization") String header) {
        try {

            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            ListaDorinte lista = listaDorinteService.findById(id);
            if(!(lista.getUtilizator().getIdUtilizator()==userId))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            return ResponseEntity.ok(lista);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping("/createListaDorinte/{idUtilizator}")
    public ResponseEntity<ListaDorinte> save(@PathVariable long idUtilizator,@RequestHeader("Authorization") String header) {
        try {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            if(idUtilizator!=userId)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();


            ListaDorinte lista = listaDorinteService.save(idUtilizator);
            return ResponseEntity.status(HttpStatus.CREATED).body(lista);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @PutMapping("/addProductToListaDorinte/{idProdus}")
    public ResponseEntity<ListaDorinte> addProductToListaDorinte(@PathVariable long idProdus,@RequestHeader("Authorization") String header) {
        try {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            ListaDorinte lista = listaDorinteService.addProdusInListaDorinte(userId, idProdus);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PutMapping("/deleteProductFromListaDorinte/{idProdus}")
    public ResponseEntity<ListaDorinte> deleteProductFromListaDorinte(@PathVariable long idProdus,@RequestHeader("Authorization") String header) {
        try {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            ListaDorinte lista = listaDorinteService.deleteProductFromListaDorinte(userId, idProdus);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PutMapping("/clearAllListaDorinte")
    public ResponseEntity<ListaDorinte> clearAllListaDorinte(@RequestHeader("Authorization") String header) {
        try { Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            ListaDorinte lista = listaDorinteService.clearAllListaDorinte(userId);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


    @GetMapping("/findWishlistForUser/{id}")
    public ResponseEntity<ListaDorinte> findWishlistForUser(@PathVariable long id,@RequestHeader("Authorization") String header) {
        try {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            if(userId!=id)
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            ListaDorinte lista = listaDorinteService.findListaDorinteForCertainUser(id);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
