package servidor.service;

import servidor.comunicacion.Login;
import servidor.db.LoginDAO;

import java.sql.SQLException;

public class ServicioLogin {
    private LoginDAO loginDAO;

    public ServicioLogin() {
        this.loginDAO = new LoginDAO();
    }

    public boolean autenticar(Login login) throws Exception {
        return this.loginDAO.autenticarUsuario(login);
    }

    public void crearNuevoUsuario(Login login) throws SQLException {
        this.loginDAO.crearNuevoUsuario(login);
    }
}
