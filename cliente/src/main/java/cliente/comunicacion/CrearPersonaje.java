package cliente.comunicacion;

import cliente.Mensaje;
import cliente.Usuario;

import java.util.ArrayList;


public class CrearPersonaje extends Mensaje {

    public CrearPersonaje(Usuario usuario) {
        super(usuario);
    }

    @Override
    public void ejecutarMensaje(ArrayList<Usuario> arrayUsuario) {
        for (Usuario u : arrayUsuario) {
            if (u.getSocket() == this.usuario.getSocket()) {
                u.setPersonaje(this.usuario.getPersonaje());
            }
        }
    }

}
