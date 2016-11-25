package cliente.hilos;

import cliente.Client;
import cliente.usuario.Usuario;
import cliente.vistas.ViewCrearPersonaje;

import java.io.PrintWriter;
import java.util.Scanner;

public class LoginThread implements Runnable{
    private Usuario usuario;
    private Client frame;
    private Scanner sc;
    private PrintWriter pw;

    public LoginThread(Usuario usuario, Client frame) {
        this.usuario = usuario;
        this.frame = frame;
    }

    @Override
    public void run() {
        try{
            sc = new Scanner(usuario.getSocket().getInputStream());

            if (sc.hasNext()) {
                String mensajeEntrante = sc.nextLine();
                if(mensajeEntrante.equals("Datos Incorrectos")){
                    frame.getLabelError().setVisible(true);
                    usuario.getSocket().close();
                    sc.close();
                }

                if(mensajeEntrante.equals("Datos Correctos")){
                    ViewCrearPersonaje viewCrearPersonaje = new ViewCrearPersonaje(usuario);
                    viewCrearPersonaje.setVisible(true);
                    frame.dispose();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
