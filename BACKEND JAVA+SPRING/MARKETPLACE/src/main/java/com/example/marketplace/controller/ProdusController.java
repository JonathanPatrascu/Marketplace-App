package com.example.marketplace.controller;

import com.example.marketplace.model.Produs;
import com.example.marketplace.security.JWT;
import com.example.marketplace.service.CategorieService;
import com.example.marketplace.service.ProdusService;
import com.example.marketplace.service.UtilizatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/produs")
@Transactional
public class ProdusController {

    private static final Logger LOGGER = LogManager.getLogger(ProdusController.class);
    private ProdusService produsService;
    private JWT jsonWebToken;
    private UtilizatorService utilizatorService;
    private CategorieService categorieService;



    @Autowired
    public ProdusController(ProdusService produsService,JWT jsonWebToken,UtilizatorService utilizatorService,CategorieService categorieService) {

        this.produsService = produsService;
        this.jsonWebToken=jsonWebToken;
        this.utilizatorService=utilizatorService;
        this.categorieService=categorieService;
    }


    @CrossOrigin(maxAge = 3600)
    @GetMapping(value = "/findAllProduse")
    public ResponseEntity<List<Produs>> findAllProducts() {
        try {
            List<Produs> listaProduse = produsService.findAllProduse();
            return ResponseEntity.ok(listaProduse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @CrossOrigin(maxAge = 3600)
    @GetMapping("/findProdusById/{id}")
    public ResponseEntity<Produs> findById(@PathVariable Long id) {
        try {
            Produs produs = produsService.findProdusById(id);
            return ResponseEntity.ok(produs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
//    @GetMapping("/img/{name})
//    public ResponseEntity<Produs> findById(@PathVariable Long id) {
//        try {
//            Produs produs = produsService.findProdusById(id);
//            return ResponseEntity.ok(produs);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//
//    }

    @CrossOrigin(maxAge = 3600)
    @GetMapping("/findProduseByWord/{frazaCautare}")
    public ResponseEntity<List<Produs>> findProdusForSearchBar(@PathVariable String frazaCautare) {
        try {
            List<Produs> listaProduse = produsService.findProdusByDescriere(frazaCautare);
            return ResponseEntity.ok(listaProduse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    @CrossOrigin(maxAge = 3600)
    @GetMapping("/findProduseByCategorie/{numeCategorie}")
    public ResponseEntity<Set<Produs>> findProdusByCategorie(@PathVariable String numeCategorie) {
        try {
            Set<Produs> listaProduse = produsService.findProdusByCategorie(numeCategorie);
            return ResponseEntity.ok(listaProduse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @CrossOrigin(maxAge = 3600)
    @GetMapping("/findProduseBySubcategorie/{numeSubcategorie}")
    public ResponseEntity<Set<Produs>> findProdusBySubcategorie(@PathVariable String numeSubcategorie) {
        try {
            Set<Produs> listaProduse = produsService.findProdusBySubcategorie(numeSubcategorie);
            return ResponseEntity.ok(listaProduse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

//    @CrossOrigin( allowedHeaders = "Content-Type", origins = "*", maxAge = 3600)
    @PostMapping(value="/saveProdus",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@RequestBody Produs produs,@RequestHeader("Authorization") String header) {
        try {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();


//            produs.setDataPostare(new Date());
            produs.setVanzator(utilizatorService.findUserById(userId));
            produs.setCategorie(categorieService.findCategorieByIdSubcategorie(produs.getSubcategorie().getIdSubcategorie()));
            Produs p = produsService.saveProdus(produs);
            return ResponseEntity.created(new URI("/produs/" + p.getIdProdus())).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PutMapping("/updateProdus")
    public ResponseEntity<Produs> updatePrdodus(@RequestBody Produs produs,@RequestHeader("Authorization") String header) {
        try {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            if(!(userId.equals(produs.getVanzator().getIdUtilizator())))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();



            Produs p = produsService.updateProdus(produs);
            return ResponseEntity.created(new URI("/produs/" + p.getIdProdus())).build();
        } catch (Exception e) {
            LOGGER.error("An exception occurred during trying to update a product with name {}", produs.getIdProdus(), e);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/deleteProdus/{id}")
    public ResponseEntity<Void> deleteProdus(@PathVariable long id,@RequestHeader("Authorization") String header) {
        try {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

            if(!(userId.equals(produsService.findProdusById(id).getVanzator().getIdUtilizator())))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            produsService.deleteProdus(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOGGER.error("An exception occurred during trying to delete a product with name {}", id, e);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/findProduseByPret/{pretStart}/{pretStop}")
    public ResponseEntity<Set<Produs>> findProduseByPrice(@PathVariable float pretStart, @PathVariable float pretStop) {
        try {
            Set<Produs> listaProduse = produsService.findProductsByPrice(pretStart, pretStop);
            return ResponseEntity.ok(listaProduse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/findAlBrands")
    public ResponseEntity<Set<String>> findAllBrands() {
        try {
            Set<String> brands = produsService.findAllBrands();
            return ResponseEntity.ok(brands);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/findBrandsByCategorie/{idCategorie}")
    public ResponseEntity<Set<String>> findABrandsByCategorie(@PathVariable long idCategorie) {
        try {
            Set<String> brands = produsService.findBrandsByCategorie(idCategorie);
            return ResponseEntity.ok(brands);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/findBrandsBySubcategorie/{idSubcategorie}")
    public ResponseEntity<Set<String>> findABrandsBySubcategorie(@PathVariable long idSubcategorie) {
        try {
            Set<String> brands = produsService.findBrandsBySubcategorie(idSubcategorie);
            return ResponseEntity.ok(brands);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value="/findProductsForUser",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produs>> findProductsForUser(@RequestHeader("Authorization") String header)
    {

        try
        {
            Long userId=jsonWebToken.findIdFromToken(header);
            if(userId==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            List<Produs> listaProduseUtilizator= produsService.findProductsForUser(userId);
            return ResponseEntity.ok(listaProduseUtilizator);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
