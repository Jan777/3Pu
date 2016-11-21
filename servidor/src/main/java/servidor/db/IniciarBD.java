package servidor.db;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by gustavo on 20/11/2016.
 */
public class IniciarBD {
    private ConexionSQLite conexionSQLite;

    public IniciarBD() throws SQLException {
        this.conexionSQLite = ConexionSQLite.getInstancia();
        Statement stmt = this.conexionSQLite.getStatement();

        String sql = "create table if not exists user_login " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " USUARIO           TEXT    NOT NULL, " +
                " PASSWORD       INT     NOT NULL, " +
                " MUNDO        INT)";

        try {
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
