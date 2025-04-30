package fr.nova.novascrape.db.dao.detail;


import fr.nova.novascrape.model.details.SupermarketDetail;
import java.sql.*;

public class SupermarketDetailDAO extends AbstractDetailDAO<SupermarketDetail> {

    @Override
    protected String getBaseTableName() {
        return "supermarket";
    }

    @Override
    protected String getInsertQuery() {
        return """
            INSERT INTO supermarket_detail (
                supermarket_id, nom, adresse, horaires, telephone
            ) VALUES (?, ?, ?, ?, ?)
        """;
    }

    @Override
    protected void fillInsertParams(PreparedStatement stmt, int baseId, SupermarketDetail detail) throws SQLException {
        stmt.setInt(1, baseId);
        stmt.setString(2, detail.getNom());
        stmt.setString(3, detail.getAdresse());
        stmt.setString(4, detail.getHoraires());
        stmt.setString(5, detail.getTelephone());
    }
}
