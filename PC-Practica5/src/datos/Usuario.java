package datos;

import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

public class Usuario implements Serializable{
	private String id;
	private InetAddress ip;
	private ArrayList<File> info;
	
	public Usuario(String id, InetAddress ip) {
		this.id = id;
		this.ip = ip;
		this.info = new ArrayList<File>();
	}
	
	public Usuario(String id, InetAddress ip, ArrayList<File> info) {
		this.id = id;
		this.ip = ip;
		this.info = new ArrayList<File>(info);
	}
	
	public String getUserid() {
		return id;
	}
	
	public InetAddress getUserip() {
		return ip;
	}
	
	public ArrayList<File> getUserInfo(){
		return info;
	}
	
	public String toString() {
		String str = "Usuario: " + id + "  IP: " + ip + "\n";
		for(File f : info) {
			str.concat("\t -" + f.getName() + "\n");
		}
		return str;
	}
}
