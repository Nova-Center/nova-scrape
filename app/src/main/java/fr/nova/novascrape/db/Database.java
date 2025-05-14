package fr.nova.novascrape.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final Logger log = LogManager.getLogger(Database.class);

    private static final String URL = "jdbc:sqlite:data/nova_scrape.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Table RESTAURANTS
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS restaurant (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            nom TEXT,
                            adresse TEXT,
                            lienDetail TEXT
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS restaurant_detail (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            restaurant_id INTEGER,
                            telephone TEXT,
                            fermetureHebdo TEXT,
                            metro TEXT,
                            genreEtablissement TEXT,
                            services TEXT,
                            prixMenu TEXT,
                            typeCuisine TEXT,
                            guide TEXT,
                            FOREIGN KEY (restaurant_id) REFERENCES restaurant(id)
                        );
                    """);

            // Table HAIR SALON
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS hair_salon (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            nom TEXT,
                            adresse TEXT,
                            lienDetail TEXT
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS hair_salon_detail (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            salon_id INTEGER, 
                            nom TEXT,
                            adresse TEXT,    
                            horaires TEXT,
                            tarif TEXT,
                            FOREIGN KEY (salon_id) REFERENCES hair_salon(id)
                        );
                    """);

            // Table SUPERMARKETS
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS supermarket (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            nom TEXT,
                            adresse TEXT,
                            lienDetail TEXT
                        );
                    """);

            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS supermarket_detail (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            supermarket_id INTEGER,
                            nom TEXT,
                            adresse TEXT,
                            horaires TEXT,
                            telephone TEXT,
                            FOREIGN KEY (supermarket_id) REFERENCES supermarket(id)
                        );
                    """);

            log.info("✅ Toutes les tables ont été créées avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
