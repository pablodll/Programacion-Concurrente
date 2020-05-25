package datos;

import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable{
	
	private String id;
	private InetAddress ip;
	private List<File> info;
	
	
	public Usuario(String id, InetAddress ip) {
		this.id = id;
		this.ip = ip;
		this.info = new ArrayList<File>();
	}
	
	public Usuario(String id, InetAddress ip, List<File> info) {
		this.id = id;
		this.ip = ip;
		this.info = new ArrayList<File>(info);
	}
	
	public void addFile(File f) {
		info.add(f);
	}
	
	public String getUserid() {
		return id;
	}
	
	public InetAddress getUserip() {
		return ip;
	}
	
	public List<File> getUserInfo(){
		return info;
	}
	
	public String toString() {
		String str = id + ": " + ip.getHostAddress() + "\n";
		for(File f : info) {
			str = str.concat("\t -" + f.getName() + "\n");
		}
		return str;
	}
}
