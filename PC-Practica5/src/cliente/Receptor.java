package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Receptor extends Thread{

	private int port;
	private String ip;
	
	public Receptor(int port, String ip) {
		this.port = port;
		this.ip = ip;
	}
	
	@Override
	public void run() {
		try {
			
			Socket socket = new Socket(ip, port);
			
			ObjectInputStream fin = new ObjectInputStream(socket.getInputStream());
			
			// LEER INFO
			System.out.println("RECIBIENDO FICHERO");
			String fichero = (String) fin.readObject();
			
			System.out.println("Fichero recibido: " + fichero);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
