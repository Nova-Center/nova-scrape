package fr.nova.novascrape.db.dao.detail;

import fr.nova.novascrape.db.Database;

import java.sql.*;
public abstract class AbstractDetailDAO<T> implements DetailDAO<T> {

    protected abstract String getBaseTableName();
    protected abstract String getInsertQuery();
    protected abstract void fillInsertParams(PreparedStatement stmt, int baseId, T detail) throws SQLException;

    @Override
    public void save(String url, T detail) {
        int baseId = getIdByUrl(url);
        if (baseId == -1) {
            System.out.println("Aucun ID trouvé pour l'URL : " + url);
            return;
        }

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(getInsertQuery())) {
            fillInsertParams(stmt, baseId, detail);
            stmt.executeUpdate();
            System.out.println(getBaseTableName() + "saved");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getIdByUrl(String url) {
        String sql = "SELECT id FROM " + getBaseTableName() + " WHERE lienDetail = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, url);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
