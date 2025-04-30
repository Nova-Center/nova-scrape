package fr.nova.novascrape.db.dao.detail;

import fr.nova.novascrape.model.details.HairSalonDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HairSalonDetailDAO extends AbstractDetailDAO<HairSalonDetail> {

    @Override
    protected String getBaseTableName() {
        return "hair_salon";
    }

    @Override
    protected String getInsertQuery() {
        return """
            INSERT INTO hair_salon_detail (
                salon_id, nom, adresse,  horaires, tarif
            ) VALUES (?, ?, ?, ?, ?)
        """;
    }

    @Override
    protected void fillInsertParams(PreparedStatement stmt, int baseId, HairSalonDetail detail) throws SQLException {
        stmt.setInt(1, baseId);
        stmt.setString(2, detail.getNom());
        stmt.setString(3, detail.getAdresse());
        stmt.setString(4, detail.getHoraire());
        stmt.setString(5, detail.getTarif());
    }
}
