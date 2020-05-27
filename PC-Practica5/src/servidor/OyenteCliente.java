package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import datos.Usuario;
import datos.mensajes.Mensaje;
import datos.mensajes.Mensaje_Cerrar_Conexion;
import datos.mensajes.Mensaje_Conexion;
import datos.mensajes.Mensaje_Confirmacion_Cerrar_Conexion;
import datos.mensajes.Mensaje_Confirmacion_Conexion;
import datos.mensajes.Mensaje_Confirmacion_Lista_Usuarios;
import datos.mensajes.Mensaje_Emitir_Fichero;
import datos.mensajes.Mensaje_Pedir_Fichero;
import datos.mensajes.Mensaje_Preparado_ClienteServidor;
import datos.mensajes.Mensaje_Preparado_ServidorCliente;

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
						fout.writeObject(new Mensaje_Confirmacion_Conexion(server.getAddres().getHostAddress(), m.getOrigen()));
						break;
						
					case LISTA_USUARIOS:
						fout.writeObject(new Mensaje_Confirmacion_Lista_Usuarios(server.getAddres().getHostAddress(), m.getOrigen(), server.getUsers()));
						break;
						
					case PEDIR_FICHERO:
						String fichero = ((Mensaje_Pedir_Fichero)m).getFichero();
						String id = server.buscarFichero(fichero);
						ObjectOutputStream fout2 = server.getOutputStream(id);
						fout2.writeObject(new Mensaje_Emitir_Fichero(server.getAddres().getHostAddress(), server.getUserIP(id), fichero, m.getOrigen()));
						break;
						
					case PREPARADO_CLIENTE_SEVIDOR:
						String cliente = ((Mensaje_Preparado_ClienteServidor)m).getCliente();
						int port = ((Mensaje_Preparado_ClienteServidor)m).getPort();
						String ip = m.getOrigen();
						
						ObjectOutputStream fout1 = server.getOutputStream(cliente);
						fout1.writeObject(new Mensaje_Preparado_ServidorCliente(server.getAddres().getHostAddress(), server.getUserIP(cliente), ip, port));
						break;
						
					case CERRAR_CONEXION:
						server.removeUser(((Mensaje_Cerrar_Conexion)m).getNombre());
						fout.writeObject(new Mensaje_Confirmacion_Cerrar_Conexion(server.getAddres().getHostAddress(), m.getOrigen()));
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
