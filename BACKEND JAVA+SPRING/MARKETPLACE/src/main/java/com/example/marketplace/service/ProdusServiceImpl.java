package com.example.marketplace.service;

import com.example.marketplace.model.Produs;
import com.example.marketplace.repository.ProdusDao;
import com.example.marketplace.repository.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@Transactional
public class ProdusServiceImpl implements ProdusService {


    private ProdusRepository produsRepository;
    private ProdusDao produsDao;

    public ProdusServiceImpl(ProdusRepository produsRepository, ProdusDao produsDao) {
        this.produsRepository = produsRepository;
        this.produsDao = produsDao;
    }

    @Autowired


    @Override
    public List<Produs> findAllProduse() {
        return produsRepository.findAll();

    }

    @Override
    public Produs findProdusById(long id) {
        return produsRepository.findById(id).get();  //findById(id).get;
    }


    @Override
    public Set<Produs> findProdusByCategorie(String numeCategorie) {
        return produsRepository.findProdusByCategorie(numeCategorie);
    }


    @Override
    public Set<Produs> findProdusBySubcategorie(String numeSubcategorie) {
        return produsRepository.findProdusBySubcategorie(numeSubcategorie);
    }

    @Override
    public List<Produs> findProdusByDescriere(String stringCautare) {
        return produsDao.findProduseByCuvinteCheie(stringCautare);
    }

    @Override
    public Produs saveProdus(Produs produs) {
        produsRepository.save(produs);
        return produs;
    }

    @Override
    public Produs updateProdus(Produs produs) {
        Produs p =produsRepository.findById(produs.getIdProdus()).get();
        p.setNumeProdus(produs.getNumeProdus());
        p.setPretProdus(produs.getPretProdus());
        p.setTransportGratuit(produs.isTransportGratuit());
        p.setDescriereProdus(produs.getDescriereProdus());
        p.setStareProdus(produs.getStareProdus());
        p.setBrandProdus(produs.getBrandProdus());
        p.setImaginiProdus(produs.getImaginiProdus());

        produsRepository.save(p);

        return p;
    }


    @Override
    public void deleteProdus(long id) {
            produsRepository.deleteById(id);
    }

    @Override
    public Set<Produs> findProductsByPrice(float pretStart, float pretStop) {

        return produsRepository.findProduseByPrice(pretStart, pretStop);
    }

    @Override
    public Set<String> findAllBrands() {
        return produsRepository.findAllBrands();
    }

    @Override
    public Set<String> findBrandsByCategorie(long idCategorie) {
        return produsRepository.findBrandsByCategorie(idCategorie);
    }

    @Override
    public Set<String> findBrandsBySubcategorie(long idSubcategorie) {
        return produsRepository.findBrandsBySubcategorie(idSubcategorie);
    }

    @Override
    public List<Produs> findProductsForUser(Long idUser) {
        return produsRepository.findProductsForUser(idUser);
    }
}
