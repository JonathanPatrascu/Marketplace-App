package com.example.marketplace.service;

import com.example.marketplace.model.ListaDorinte;
import com.example.marketplace.model.Produs;
import com.example.marketplace.model.Utilizator;
import com.example.marketplace.repository.ListaDorinteRepository;
import com.example.marketplace.repository.ProdusRepository;
import com.example.marketplace.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ListaDorinteServiceImpl implements ListaDorinteService {

    private ListaDorinteRepository listaDorinteRepository;
    private ProdusRepository produsRepository;
    private UtilizatorRepository utilizatorRepository;

    @Autowired
    public ListaDorinteServiceImpl(ListaDorinteRepository listaDorinteRepository,
                                   ProdusRepository produsRepository,
                                   UtilizatorRepository utilizatorRepository) {
        this.listaDorinteRepository = listaDorinteRepository;
        this.produsRepository = produsRepository;
        this.utilizatorRepository = utilizatorRepository;
    }

    @Override
    public ListaDorinte deleteProductFromListaDorinte(long idListaDorinte, long idProdus) {
        ListaDorinte lista = listaDorinteRepository.findById(idListaDorinte).get();
        List<Produs> listaProduse = lista.getListaProduseDorite();
        if (listaProduse.size() == 1) {
            listaProduse.clear();
        }
        for (Produs p : listaProduse) {
            if (p.getIdProdus() == idProdus) {
                listaProduse.remove(p);
            }
        }

        listaDorinteRepository.save(lista);

        return lista;
    }


    @Override
    public ListaDorinte findById(long id) {

        try {
            ListaDorinte lista = listaDorinteRepository.findById(id).get();
            return lista;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<ListaDorinte> findAll() {
        return listaDorinteRepository.findAll();
    }

    @Override
    public ListaDorinte save(long idUtilizator) {
        try {

            if(listaDorinteRepository.findWishlistForCertainUser(idUtilizator)==null) {
                ListaDorinte listaNoua = new ListaDorinte();
                Utilizator user = utilizatorRepository.findById(idUtilizator).get();
                listaNoua.setUtilizator(user);
                listaDorinteRepository.save(listaNoua);
                return listaNoua;
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        listaDorinteRepository.deleteById(id);

    }

    @Override
    public ListaDorinte update(ListaDorinte listaDorinte) {
        long id = listaDorinte.getIdListaDorinte();
        ListaDorinte lista = listaDorinteRepository.findById(id).get();
        lista.setUtilizator(null);
        lista.setUtilizator(listaDorinte.getUtilizator());
        lista.setListaProduseDorite(null);
        lista.setListaProduseDorite(listaDorinte.getListaProduseDorite());

        listaDorinteRepository.save(lista);

        return lista;
    }

    @Override
    public ListaDorinte addProdusInListaDorinte(long idListaDorinte, long idProdus) {

            ListaDorinte lista = listaDorinteRepository.findById(idListaDorinte).get();
            Produs produs = produsRepository.findById(idProdus).get();
            for (Produs p : lista.getListaProduseDorite()) {
                if (p.getIdProdus() == idProdus)
                    return null;
            }

            lista.addProdustoListaDorinte(produs);
            listaDorinteRepository.save(lista);

            return lista;

    }


    @Override
    public ListaDorinte clearAllListaDorinte(long idListaDorinte) {
        ListaDorinte lista = listaDorinteRepository.findById(idListaDorinte).get();
        List<Produs> listaProduse = lista.getListaProduseDorite();
        listaProduse.clear();
        listaDorinteRepository.save(lista);

        return lista;
    }

    @Override
    public ListaDorinte createListaDorinteToUser(long idUser) {
        ListaDorinte listaDorinte = new ListaDorinte();
        listaDorinte.setUtilizator(utilizatorRepository.findById(idUser).get());
        listaDorinte.setListaProduseDorite(new ArrayList<>());

        listaDorinteRepository.save(listaDorinte);
        return listaDorinte;

    }


    @Override
    public ListaDorinte findListaDorinteForCertainUser(long idUser) {
        return listaDorinteRepository.findWishlistForCertainUser(idUser);
    }
}
