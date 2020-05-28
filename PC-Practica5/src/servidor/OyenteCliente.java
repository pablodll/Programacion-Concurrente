/*
 * Pratica 5 - Programacion Concurrente
 * Autor: Pablo Daurel Marina
 */

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
						Mensaje_Conexion mc = (Mensaje_Conexion)m;
						server.addUser(mc.getUser(), fin, fout);
						
						System.out.println("Conexion establecida con " + mc.getOrigen());
						fout.writeObject(new Mensaje_Confirmacion_Conexion(server.getAddres().getHostAddress(), mc.getOrigen()));
						break;
						
					case LISTA_USUARIOS:
						fout.writeObject(new Mensaje_Confirmacion_Lista_Usuarios(server.getAddres().getHostAddress(), m.getOrigen(), server.getUsers()));
						break;
						
					case PEDIR_FICHERO:
						Mensaje_Pedir_Fichero mpf = (Mensaje_Pedir_Fichero)m;
						String id = server.buscarFichero(mpf.getFichero());
						ObjectOutputStream fout2 = server.getOutputStream(id);
						
						fout2.writeObject(new Mensaje_Emitir_Fichero(server.getAddres().getHostAddress(), id, mpf.getFichero(), mpf.getOrigen()));
						break;
						
					case PREPARADO_CLIENTE_SEVIDOR:
						Mensaje_Preparado_ClienteServidor mpcs = (Mensaje_Preparado_ClienteServidor)m;
						ObjectOutputStream fout1 = server.getOutputStream(mpcs.getCliente());
						
						fout1.writeObject(new Mensaje_Preparado_ServidorCliente(server.getAddres().getHostAddress(), server.getUserIP(mpcs.getCliente()), mpcs.getFichero(), mpcs.getIP(), mpcs.getPort()));
						break;
						
					case CERRAR_CONEXION:
						server.removeUser(((Mensaje_Cerrar_Conexion)m).getNombre());
						
						System.out.println("Conexion finalizada con " + m.getOrigen());
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
