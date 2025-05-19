package juego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BD {
    private static final String URL = "jdbc:mysql://localhost:3306/lajaula_db";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";

    public static void conectar(String nomJugador, int cintas, int pelotas, int zapatillas, int videsRestants) {
        String sql = "INSERT INTO partides (nom_jugador, cintes_recollides, pilotes_recollides, sabatilles_recollides, vides_restants, data_hora) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nomJugador);
            ps.setInt(2, cintas);
            ps.setInt(3, pelotas);
            ps.setInt(4, zapatillas);
            ps.setInt(5, videsRestants);
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

            ps.executeUpdate();

            System.out.println("Partida guardada correctamente a la base de dades.");
        } catch (SQLException e) {
            System.out.println("La conexión o la inserción a la base de dades ha fallado.");
            e.printStackTrace();
        }
    }
}
