package servidor.service;

import servidor.Login;
import servidor.db.LoginDAO;

import java.sql.SQLException;

/**
 * Created by gustavo on 20/11/2016.
 */
public class LoginService {
    private LoginDAO loginDAO;

    public LoginService() {
        this.loginDAO = new LoginDAO();
    }

    public boolean autenticar(Login login) throws Exception {
        return this.loginDAO.autenticarUsuario(login);
    }

    public void crearNuevoUsuario(Login login) throws SQLException {
        this.loginDAO.crearNuevoUsuario(login);
    }
}
