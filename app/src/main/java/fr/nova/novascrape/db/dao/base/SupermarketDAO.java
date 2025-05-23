package fr.nova.novascrape.db.dao.base;

import fr.nova.novascrape.db.Database;
import fr.nova.novascrape.model.base.Supermarket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SupermarketDAO {
    private static final Logger log = LogManager.getLogger(SupermarketDAO.class);

    public int saveAndReturnId(Supermarket market) {
        String sql = "INSERT INTO supermarket(nom, adresse, lienDetail) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, market.getNom());
            stmt.setString(2, market.getAddress());
            stmt.setString(3, market.getDetailsUrl());

            stmt.executeUpdate();
            log.info("Supermarket added");
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
