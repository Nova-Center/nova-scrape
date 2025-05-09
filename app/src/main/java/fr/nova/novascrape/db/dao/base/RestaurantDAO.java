package fr.nova.novascrape.db.dao.base;

import fr.nova.novascrape.Application;
import fr.nova.novascrape.db.Database;
import fr.nova.novascrape.model.base.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class RestaurantDAO {
    private static final Logger log = LogManager.getLogger(RestaurantDAO.class);

    public int saveAndReturnId(Restaurant restaurant) {
        String sql = "INSERT INTO restaurant(nom, adresse, lienDetail) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, restaurant.getNom());
            stmt.setString(2, restaurant.getAddress());
            stmt.setString(3, restaurant.getLienDetail());

            stmt.executeUpdate();
            log.info("Restaurant {} saved", restaurant.getNom());
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

