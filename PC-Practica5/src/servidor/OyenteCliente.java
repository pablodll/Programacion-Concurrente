package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import datos.Usuario;
import datos.mensajes.Mensaje;
import datos.mensajes.Mensaje_Confirmacion_Conexion;
import datos.mensajes.Mensaje_Confirmacion_Lista_Usuarios;

public class OyenteCliente extends Thread{

	private Socket socket;
	private ObjectInputStream fin;
	private ObjectOutputStream fout;
	private Servidor server;
	
	public OyenteCliente(Socket socket, ObjectInputStream fin, ObjectOutputStream fout, Servidor server) {
		this.fin = fin;
		this.fout = fout;
		this.server = server;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				Mensaje m = (Mensaje) fin.readObject();
				
				switch(m.getTipo()) {
					case CONEXION:
						server.addUser(new Usuario(m.getOrigen(), socket.getInetAddress()), fin, fout);
						fout.writeObject(new Mensaje_Confirmacion_Conexion(server.getAddres().getHostName(), m.getOrigen()));
						break;
						
					case LISTA_USUARIOS:
						fout.writeObject(new Mensaje_Confirmacion_Lista_Usuarios(server.getAddres().getHostName(), m.getOrigen(), server.getUsers()));
						break;
						
					case PEDIR_FICHERO:
						break;
						
					case PREPARADO_CLIENTE_SEVIDOR:
						break;
					case CERRAR_CONEXION:
						break;
					default:
						break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
