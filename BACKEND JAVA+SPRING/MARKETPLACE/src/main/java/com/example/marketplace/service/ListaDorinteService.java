package com.example.marketplace.service;

import com.example.marketplace.model.ListaDorinte;

import java.util.List;

public interface ListaDorinteService {

    public ListaDorinte findById(long id);

    public List<ListaDorinte> findAll();

    public ListaDorinte save(long idUtilizator);

    public void delete(long id);

    public ListaDorinte update(ListaDorinte listaDorinte);

    public ListaDorinte addProdusInListaDorinte(long idListaDorinte, long idProdus); //adaoga produs in lista dupa idLista si idProdus

    public ListaDorinte deleteProductFromListaDorinte(long idListaDorinte, long idProdus); //sterge produs din lista dupa idLista si idProdus

    public ListaDorinte clearAllListaDorinte(long idListaDorinte);  //sterge toate produsele dintro lista fiind dat idLista

    public ListaDorinte createListaDorinteToUser(long idUser); //creaza o lista dorinte fiind dat id-ul utilizatorului

    public ListaDorinte findListaDorinteForCertainUser(long idUser); //returneaza din baza de date o lista de dorinte a unui utilizator fiind dat id-ul utilizatorului

}
