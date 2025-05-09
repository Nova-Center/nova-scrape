package fr.nova.novascrape.db.dao.base;

import fr.nova.novascrape.Application;
import fr.nova.novascrape.db.Database;
import fr.nova.novascrape.model.base.HairSalon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class HairSalonDAO {
    private static final Logger log = LogManager.getLogger(HairSalonDAO.class);

    public int saveAndReturnId(HairSalon salon) {
        String sql = "INSERT INTO hair_salon(nom, adresse, lienDetail) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, salon.getNom());
            stmt.setString(2, salon.getAddress());
            stmt.setString(3, salon.getLienDetail());

            stmt.executeUpdate();
            log.info("Salon saved");
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
