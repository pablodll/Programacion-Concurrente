/*
 * Pratica 5 - Programacion Concurrente
 * Autor: Pablo Daurel Marina
 */

package cliente;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import datos.*;
import datos.mensajes.Mensaje;
import datos.mensajes.Mensaje_Confirmacion_Lista_Usuarios;
import datos.mensajes.Mensaje_Emitir_Fichero;
import datos.mensajes.Mensaje_Preparado_ClienteServidor;
import datos.mensajes.Mensaje_Preparado_ServidorCliente;

public class OyenteServidor extends Thread{
	
	private Socket socket;
	private ObjectInputStream fin;
	private ObjectOutputStream fout;
	
	public OyenteServidor(Socket socket, ObjectInputStream fin, ObjectOutputStream fout) {
		this.fin = fin;
		this.fout = fout;
		this.socket = socket;
	}
	
	@Override
	public void run() {	
		try {
			while(true) {
				Mensaje m = (Mensaje) fin.readObject();
				
				switch(m.getTipo()) {
					case CONFIRMACION_CONEXION:
						System.out.println("Conexion Establecida");
						break;
						
					case CONFIRMACION_LISTA_USUARIOS:
						List<Usuario> listaUsuarios = ((Mensaje_Confirmacion_Lista_Usuarios) m).getListaUsuarios();
						System.out.println("\nUSUARIOS CONECTADOS:");
						for(Usuario u : listaUsuarios) {
							System.out.println(u);
						}
						break;
						
					case EMITIR_FICHERO:
						Mensaje_Emitir_Fichero mef = (Mensaje_Emitir_Fichero)m;
						
						try {
							Emisor e = new Emisor(mef.getFichero());
							e.start();
							fout.writeObject(new Mensaje_Preparado_ClienteServidor(m.getDestino(), m.getOrigen(), mef.getCliente(), mef.getFichero(), e.getPort(), socket.getRemoteSocketAddress().toString()));
						
						} catch (FileNotFoundException e) {
							System.err.println("Fichero " + mef.getFichero() + " no encontrado");
						}
						break;
						
					case PREPARADO_SERVIDORCLIENTE:
						Mensaje_Preparado_ServidorCliente mpsc = (Mensaje_Preparado_ServidorCliente)m;
						
						(new Receptor(mpsc.getPort(), mpsc.getIp(), mpsc.getFichero())).start();
						break;
						
					case CONFIRMACION_CERRAR_CONEXION:
						System.out.println("Conexion con servidor terminada");
						break;
						
					default:
						break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
