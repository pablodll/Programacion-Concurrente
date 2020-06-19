/*
 * Autor: Pablo Daurel Marina
 */

package cliente;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Receptor extends Thread{


	private String fichero;
	private Socket socket;
	private FileOutputStream fileOut;
	
	public Receptor(int port, String ip, String fichero) throws UnknownHostException, IOException {
		this.socket = new Socket(ip, port);
		fileOut = new FileOutputStream(fichero);
		this.fichero = fichero;
	}
	
	@Override
	public void run() {
		try {
			
			System.out.println("Descargando " + fichero);
			socket.getInputStream().transferTo(fileOut);		
			System.out.println("Descarga de " + fichero + " finalizada");
			
			socket.close();
			
		} catch (IOException e) {
			System.err.println("Fallo al descargar " + fichero);
			e.printStackTrace();
		}
	}
	
}
