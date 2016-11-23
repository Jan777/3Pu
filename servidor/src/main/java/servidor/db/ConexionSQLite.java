package servidor.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by gustavo on 20/11/2016.
 */
public class ConexionSQLite {
    private Connection c;
    private static ConexionSQLite instancia;

    public ConexionSQLite() {

    }

    public static ConexionSQLite getInstancia() {
        try {
            if (instancia == null) {
                instancia = new ConexionSQLite();
                Class.forName("org.sqlite.JDBC");
                instancia.c = DriverManager.getConnection("jdbc:sqlite:test.db");
                System.out.println("DB conectada");
            } else
                return instancia;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return null;
    }

    public void cerrarConexion() throws SQLException {
        this.instancia.getC().close();
    }

    public Connection getC() {
        return this.c;
    }

    public Statement getStatement() throws SQLException {
        return this.c.createStatement();
    }
}
