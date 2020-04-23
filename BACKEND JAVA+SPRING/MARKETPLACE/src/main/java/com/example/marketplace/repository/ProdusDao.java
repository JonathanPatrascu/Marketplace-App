package com.example.marketplace.repository;

import com.example.marketplace.model.Produs;
import com.example.marketplace.other.WordsIntoQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ProdusDao {
    @PersistenceContext
    EntityManager entityManager;

    public List<Produs> findProduseByCuvinteCheie(String fraza) {
        List<Produs> listaProduse = null;
        try {
            String sqlquery = WordsIntoQuery.insertWordsIntoWuery(fraza);
            Query query = entityManager.createNativeQuery(sqlquery, Produs.class);
            listaProduse = (List<Produs>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProduse;

    }

}
