package fr.nova.novascrape.db.dao.base;

import fr.nova.novascrape.db.Database;
import fr.nova.novascrape.model.base.HairSalon;

import java.sql.*;

public class HairSalonDAO {
    public int saveAndReturnId(HairSalon salon) {
        String sql = "INSERT INTO hair_salon(nom, adresse, lienDetail) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, salon.getNom());
            stmt.setString(2, salon.getAddress());
            stmt.setString(3, salon.getLienDetail());

            stmt.executeUpdate();
            System.out.println("salon saved");
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
