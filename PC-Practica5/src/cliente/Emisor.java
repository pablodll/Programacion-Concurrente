/*
 * Autor: Pablo Daurel Marina
 */

package cliente;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Emisor extends Thread {
	
	private ServerSocket serverSocket;
	private String fichero;
	private FileInputStream fileIn;
	
	public Emisor(String fichero) throws IOException, FileNotFoundException {
		this.serverSocket = new ServerSocket(0);
		this.fichero = fichero;
		this.fileIn = new FileInputStream(fichero);
	}
	
	public int getPort() {
		return serverSocket.getLocalPort();
	}
	
	@Override
	public void run() {
		try(Socket socket = serverSocket.accept()){
			
			fileIn.transferTo(socket.getOutputStream());
			serverSocket.close();
			
		} catch (IOException e) {
			System.out.println("Fallo al enviar el archivo " + fichero);
		} 
	}
	
}
