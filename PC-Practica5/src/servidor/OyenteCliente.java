package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import datos.Usuario;
import datos.mensajes.Mensaje;
import datos.mensajes.Mensaje_Conexion;
import datos.mensajes.Mensaje_Confirmacion_Cerrar_Conexion;
import datos.mensajes.Mensaje_Confirmacion_Conexion;
import datos.mensajes.Mensaje_Confirmacion_Lista_Usuarios;
import datos.mensajes.Mensaje_Emitir_Fichero;
import datos.mensajes.Mensaje_Pedir_Fichero;

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
						Usuario user = ((Mensaje_Conexion)m).getUser();
						server.addUser(user, fin, fout);
						fout.writeObject(new Mensaje_Confirmacion_Conexion(server.getAddres().getHostName(), m.getOrigen()));
						break;
						
					case LISTA_USUARIOS:
						fout.writeObject(new Mensaje_Confirmacion_Lista_Usuarios(server.getAddres().getHostName(), m.getOrigen(), server.getUsers()));
						break;
						
					case PEDIR_FICHERO:
						String fichero = ((Mensaje_Pedir_Fichero)m).getFichero();
						String id = server.buscarFichero(fichero);
						ObjectOutputStream fout2 = server.getOutputStream(id);
						fout2.writeObject(new Mensaje_Emitir_Fichero(server.getAddres().getHostName(), id, fichero));
						break;
						
					case PREPARADO_CLIENTE_SEVIDOR:
						break;
						
					case CERRAR_CONEXION:
						server.removeUser(m.getOrigen());
						fout.writeObject(new Mensaje_Confirmacion_Cerrar_Conexion(server.getAddres().getHostName(), m.getOrigen()));
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
