package cliente.comunicacion;

import java.io.Serializable;

public class IniciarBatalla implements Serializable{

    private String personajeAtacante;
    private String personajeAtacado;

    public String getPersonajeAtacante() {
        return personajeAtacante;
    }

    public void setPersonajeAtacante(String personajeAtacante) {
        this.personajeAtacante = personajeAtacante;
    }

    public String getPersonajeAtacado() {
        return personajeAtacado;
    }

    public void setPersonajeAtacado(String personajeAtacado) {
        this.personajeAtacado = personajeAtacado;
    }
}
