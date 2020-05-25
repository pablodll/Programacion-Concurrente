package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import datos.*;
import datos.mensajes.Mensaje;
import datos.mensajes.Mensaje_Confirmacion_Lista_Usuarios;

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
						System.out.println("EMITIENDO FICHERITO WEY");
						break;
						
					case PREPARADO_SERVIDORCLIENTE:
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
