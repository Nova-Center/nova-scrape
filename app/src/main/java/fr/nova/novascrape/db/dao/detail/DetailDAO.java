package fr.nova.novascrape.db.dao.detail;

public interface DetailDAO <T>{
    void save(String url, T detail);
}
