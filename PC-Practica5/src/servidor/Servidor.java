package servidor;

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
				System.out.println("Esperando conexion...");
				Socket socket = serverSocket.accept();
				System.out.println("Socket conectado");
				
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
		
		s.startSocket();
	}
	
	public InetAddress getAddres() {
		return host;
	}

	public void addUser(Usuario user, ObjectInputStream fin, ObjectOutputStream fout) {
		users.addUser(user);
		users.connectUser(user, fin, fout);
	}
	
	public void disconnectUser(String id) {
		users.disconnectUser(id);
	}
	
	public List<Usuario> getUsers() {
		return users.getUsers();
	}
	
}
