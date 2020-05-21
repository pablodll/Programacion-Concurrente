package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import datos.mensajes.Mensaje_Conexion;
import datos.mensajes.Mensaje_Lista_Usuarios;


public class Cliente {
	
	private String nombre;
	private InetAddress ip;
	 
	public Cliente(String nombre, InetAddress ip) {
		this.nombre = nombre;
		this.ip = ip;
	}
	
	private void startSocket(String hostname, int port) {
		try {
			
			Socket socket = new Socket(hostname, port);
			
			ObjectOutputStream fout = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream fin = new ObjectInputStream(socket.getInputStream());
			(new OyenteServidor(socket, fin, fout)).start();

			
			
			fout.writeObject(new Mensaje_Conexion(nombre, hostname));
			fout.writeObject(new Mensaje_Lista_Usuarios(nombre, hostname));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws UnknownHostException {
		if(args.length < 2) return;
		
		String nombre = readNombre();
		InetAddress ip = InetAddress.getLocalHost();
		
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		
		Cliente c = new Cliente(nombre, ip);
		c.startSocket(hostname, port);
	}
	
	private static String readNombre() {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Introduzca su nombre de usuario: ");
		String n = scan.nextLine();
		
		scan.close();
		return n;
	}
	
	
}
