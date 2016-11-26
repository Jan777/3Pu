package servidor.service;

import servidor.usuario.Usuario;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ServicioUsuario {
    private static ServicioUsuario ourInstance = new ServicioUsuario();
    private static Map<String, Usuario> usuarios = new HashMap<>();

    public static ServicioUsuario getInstancia() {
        return ourInstance;
    }

    private ServicioUsuario() {
    }

    public void addUsuario(Usuario usuario) {
        usuarios.put(usuario.getUsuario(), usuario);
    }

    public Collection<Usuario> getUsuarios() {
        return usuarios.values();
    }

    public Usuario getUsuario(String usuario) {
        return usuarios.get(usuario);
    }

    public void removerUsuario(String usuario) {
        usuarios.remove(usuario);
    }
}
