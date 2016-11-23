package servidor.db;

import servidor.Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by gustavo on 20/11/2016.
 */
public class LoginDAO {
    private ConexionSQLite conexionSQLite;

    public LoginDAO() {
        this.conexionSQLite = ConexionSQLite.getInstancia();
    }

    public void crearNuevoUsuario(Login login) {
        try {
            Statement stmt = this.conexionSQLite.getStatement();
            Connection conn = this.conexionSQLite.getInstancia().getC();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO user_login (USUARIO, PASSWORD, MUNDO) " +
                    "VALUES ('" + login.getUsuario() + "', " + login.getPassword() + ", " + login.getMundo() + ")";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.commit();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public boolean autenticarUsuario(Login login) throws Exception {
        Statement stmt = this.conexionSQLite.getStatement();
        ResultSet rs;
        String query = "SELECT password " +
                "FROM user_login " +
                "WHERE usuario = '" + login.getUsuario() + "'";

        rs = stmt.executeQuery(query);
        if (rs.next()) {
            int pass = rs.getInt("password");
            rs.close();
            stmt.close();

            return pass == login.getPassword();
        }

        return false;
    }

}
