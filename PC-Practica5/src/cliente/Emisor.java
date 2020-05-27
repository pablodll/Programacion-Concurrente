package cliente;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Emisor extends Thread {
	
	
	private int port;
	private String fichero;
	
	public Emisor(int port, String fichero) {
		this.port = port;
		this.fichero = fichero;
	}
	
	@Override
	public void run() {
		try(ServerSocket serverSocket = new ServerSocket(port)){
					
			System.out.println("Esperando receptor...");
			Socket socket = serverSocket.accept();
			System.out.println("Receptor conectado");
			
			ObjectOutputStream fout = new ObjectOutputStream(socket.getOutputStream());
			
			// Transmitir fichero
			System.out.println("TRANSMITIENDO FICHERO");
			fout.writeObject(fichero);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
