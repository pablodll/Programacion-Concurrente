package cliente;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import datos.Usuario;
import datos.mensajes.Mensaje_Cerrar_Conexion;
import datos.mensajes.Mensaje_Conexion;
import datos.mensajes.Mensaje_Lista_Usuarios;
import datos.mensajes.Mensaje_Pedir_Fichero;


public class Cliente {
	
	private String nombre;
	private InetAddress ip;
	
	private Socket socket = null;
	private ObjectInputStream fin = null;
	private ObjectOutputStream fout = null;
	
	private boolean connected = false;
	
	public Cliente(String nombre, InetAddress ip) {
		this.nombre = nombre;
		this.ip = ip;
	}
	
	private void startSocket(String hostname, int port) {
		try {
			
			socket = new Socket(hostname, port);
			
			fout = new ObjectOutputStream(socket.getOutputStream());
			fin = new ObjectInputStream(socket.getInputStream());
			(new OyenteServidor(socket, fin, fout)).start();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws UnknownHostException {
		if(args.length < 2) return;
		
		Scanner scan = new Scanner(System.in);
		
		String nombre = readNombre(scan);
		InetAddress ip = InetAddress.getLocalHost();
		
		System.out.println("Bienvenido, " + nombre);
		
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		
		Cliente c = new Cliente(nombre, ip);
		c.startSocket(hostname, port);
		
		while(true) {
			System.out.print("Introduzca opcion: ");
	        String s = scan.nextLine();
			String[] opt = s.split(" ");
	
			c.menu(opt);
		}
	}
	
	private void menu(String[] opt) {
		switch(opt[0]) {
		case "connect":
			if(!connected)connect(opt);
			else System.out.println("Ya existe una conexion");
			break;
		case "list":
			if(connected) lista();
			else System.out.println("Operacion no disponible");
			break;
		case "disconnect":
			if(connected)disconnect();
			else System.out.println("No existe ninguna conexion");
			break;
		case "get":
			if(connected) getFichero(opt[1]);
			else System.out.println("Operacion no disponible");
			break;
			
		default:
			System.out.println("Comando incorrecto");
		}
	}
	
	private void connect(String[] opt) {
		Usuario user;
		
		if(opt.length > 1) {
			List<File> files = new ArrayList<File>();
			
			for(int i = 1; i < opt.length; i++) {
				File f = new File(opt[i]);
				files.add(f);
			}
			user = new Usuario(this.nombre, this.ip, files);
		} 
		else {
			user = new Usuario(this.nombre, this.ip);
		}
		
		try {
			fout.writeObject(new Mensaje_Conexion(ip.getHostAddress(), socket.getInetAddress().getHostAddress(), user));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		connected = true;
	}
	
	private void disconnect() {
		try {
			fout.writeObject(new Mensaje_Cerrar_Conexion(ip.getHostAddress(), socket.getInetAddress().getHostAddress(), nombre));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		connected = false;
	}

	private void lista() {
		try {
			fout.writeObject(new Mensaje_Lista_Usuarios(ip.getHostAddress(), socket.getInetAddress().getHostAddress()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getFichero(String f) {
		try {
			fout.writeObject(new Mensaje_Pedir_Fichero(ip.getHostAddress(), socket.getInetAddress().getHostAddress(), f));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String readNombre(Scanner scan) {	
		System.out.print("Introduzca su nombre de usuario: ");
		String n = scan.nextLine();

		return n;
	}
	
	
}
