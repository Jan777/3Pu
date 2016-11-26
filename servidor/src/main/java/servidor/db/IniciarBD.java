package servidor.db;

import java.sql.Statement;

/**
 * Created by gustavo on 20/11/2016.
 */
public class IniciarBD {

    public static void initDB() {
        ConexionSQLite conexionSQLite = ConexionSQLite.getInstancia();

        String query = "create table if not exists user_login " +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " USUARIO           TEXT    NOT NULL, " +
                " PASSWORD       INT     NOT NULL, " +
                " MUNDO        INT)";

        try {
            Statement stmt = conexionSQLite.getStatement();
            stmt.executeUpdate(query);
            stmt.close();

        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
