package com.example.marketplace.other;

public class WordsIntoQuery {

    public static String insertWordsIntoWuery(String fraza) {
        String [] words=fraza.toLowerCase().split(" ");
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM produs ");
        for (int i = 0; i <= (words.length)-1; i++) {
            if (i == 0) {
                query.append("WHERE nume_produs LIKE CONCAT('%',\'");
                query.append(words[i]);
                query.append("\','%') ");
                continue;
            }
            query.append("AND nume_produs LIKE CONCAT('%',\'");
            query.append(words[i]);
            query.append("\','%')");



        }
        return query.toString();

    }
}
