package servidor;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import datos.Usuario;

public class Servidor {
	
	private InetAddress host;
	private int port;
	
	private MonitorUsuarios users;
	
	public Servidor(InetAddress host, int port) {
		this.host = host;
		this.port = port;
		this.users = new MonitorUsuarios();
	}
	
	private void startSocket() {
		try(ServerSocket serverSocket = new ServerSocket(port)){
			
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("Canal establecido con " + socket.getLocalAddress());
				
				ObjectOutputStream fout = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream fin = new ObjectInputStream(socket.getInputStream());
				(new OyenteCliente(socket, fin, fout, this)).start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws UnknownHostException { 
		if(args.length < 1) return;
		
		InetAddress host = InetAddress.getLocalHost();
		int port = Integer.parseInt(args[0]);
		
		Servidor s = new Servidor(host, port);
		
		System.out.println("Servidor - Programacion Concurrente (Practica 5)");
		s.startSocket();
	}
	
	public InetAddress getAddres() {
		return host;
	}

	public void addUser(Usuario user, ObjectInputStream fin, ObjectOutputStream fout) {
		users.addUser(user);
		users.addUserStreams(user, fin, fout);
	}
	
	public void removeUser(String id) {
		users.removeUser(id);
		users.removeUserStreams(id);
	}
	
	public String buscarFichero(String fichero) throws FileNotFoundException {
		return users.buscarFichero(fichero);
	}
	
	public ObjectOutputStream getOutputStream(String id) {
		return users.getOutputStream(id);
	}
	
	public String getUserIP(String id) {
		return users.getUserIP(id);
	}
	
	public List<Usuario> getUsers() {
		return users.getUsers();
	}
	
}
