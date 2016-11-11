package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ClientThread implements Runnable {
    private Socket socket;
    private Scanner sc;
    private PrintWriter out;
    private PantallaJuego mapa;
    private PantallaCreacionPersonaje interfazCreacion;
    private DataOutputStream outStream;
    private DataInputStream inStream;

    public ClientThread(Socket socket, PantallaCreacionPersonaje interfazCreacion) throws IOException {
        this.socket = socket;
        //this.mapa = mapa;
        inStream = new DataInputStream(socket.getInputStream());
        outStream = new DataOutputStream(socket.getOutputStream());
        this.interfazCreacion = interfazCreacion;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void run() {
        try {
            this.sc = new Scanner(this.socket.getInputStream());
            this.out = new PrintWriter(this.socket.getOutputStream());
            this.out.flush();

            while (true) {
            	String mensaje =  inStream.readUTF();
                String split[] = mensaje.split(";");
                for(int i=0; i < mensaje.length(); i++)
                	interfazCreacion.getComboBox().addItem(split[i]);
                recibirDatos();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public PantallaJuego getMapa() {
		return mapa;
	}

	public void setMapa(PantallaJuego mapa) {
		this.mapa = mapa;
	}

	private void recibirDatos() {
        if (this.sc.hasNext()) {
            String mensajeEntrante = this.sc.nextLine();
            System.out.println(mensajeEntrante);
//            interfazCreacion.getComboBox().addItem(mensajeEntrante);
        }
    }

    public void enviarDatos(String mensajeSaliente) {
        this.out.println(mensajeSaliente);
        this.out.flush();
    }

    public void desconectar() throws Exception {
        this.out.println(" se ha retirado de la sala");
        this.out.flush();
        this.socket.close();
        System.exit(0);
    }
}
