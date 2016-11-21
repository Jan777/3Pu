package servidor;

import java.io.Serializable;


public class MensajeLogin implements Serializable {
    private int mundo;
    private String usuario;
    private String pass;
    
    public MensajeLogin() {
        
    }

    public MensajeLogin(int mundo, String usuario, String pass) {
    	this.mundo = mundo;
    	this.usuario = usuario;
    	this.pass = pass;
    }

    public int getMundo() {
        return this.mundo;
    }

    public void setSala(int mundo) {
        this.mundo = mundo;
    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
