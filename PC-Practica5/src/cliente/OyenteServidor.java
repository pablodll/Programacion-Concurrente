package cliente;

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
						System.out.println("USUARIOS");
						for(Usuario u : listaUsuarios) {
							System.out.println(u);
						}
						break;
						
					case EMITIR_FICHERO:
						int port = 350;
						String cliente = ((Mensaje_Emitir_Fichero)m).getCliente();
						String fichero = ((Mensaje_Emitir_Fichero)m).getFichero();
						
						(new Emisor(port, fichero)).start();
						fout.writeObject(new Mensaje_Preparado_ClienteServidor(m.getDestino(), m.getOrigen(), cliente, port));
						break;
						
					case PREPARADO_SERVIDORCLIENTE:
						String ip = ((Mensaje_Preparado_ServidorCliente)m).getIp();
						int port_ = ((Mensaje_Preparado_ServidorCliente)m).getPort();
						
						(new Receptor(port_, ip)).start();
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
