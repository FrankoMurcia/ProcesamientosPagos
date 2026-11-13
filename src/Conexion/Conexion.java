package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Frank
 */
public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sistemapagos";

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    public static Connection conectar() {

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Conexion exitosa con MySQL");

        } catch (SQLException e) {

            System.out.println("Error al conectar con MySQL: "
                    + e.getMessage());

        }

        return conexion;
    }
}
