package fr.nova.novascrape.db.dao.detail;

import fr.nova.novascrape.model.details.RestaurantDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RestaurantDetailDAO extends AbstractDetailDAO<RestaurantDetail> {

    @Override
    protected String getBaseTableName() {
        return "restaurant";
    }

    @Override
    protected String getInsertQuery() {
        return """
            INSERT INTO restaurant_detail (
                restaurant_id, telephone, fermetureHebdo, metro,
                genreEtablissement, services, prixMenu, typeCuisine, guide
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
    }

    @Override
    protected void fillInsertParams(PreparedStatement stmt, int baseId, RestaurantDetail detail) throws SQLException {
        stmt.setInt(1, baseId);
        stmt.setString(2, detail.getTelephone());
        stmt.setString(3, detail.getFermetureHebdo());
        stmt.setString(4, detail.getMetro());
        stmt.setString(5, detail.getGenreEtablissement());
        stmt.setString(6, detail.getServices());
        stmt.setString(7, detail.getPrixMenu());
        stmt.setString(8, detail.getTypeCuisine());
        stmt.setString(9, detail.getGuide());
    }
}
