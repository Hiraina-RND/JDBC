package pac.jdbc;


import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = dotenv.get("DB_URL") + dotenv.get("DB_NAME");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");
    private static final String USER = dotenv.get("DB_USER");

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base PostgreSQL", e);
        }
    }

    static void main(String[] args) {

        try (Connection connection = Main.getConnection()) {
            if (connection != null) {
                System.out.println("Connexion réussie !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
