package servidor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import datos.Usuario;

public class MonitorUsuarios {
	
	volatile private Hashtable<String, Usuario> users = new Hashtable<String, Usuario>();
	volatile private Hashtable<String, List<Object>> users_streams = new Hashtable<String, List<Object>>();
	
	synchronized void addUser(Usuario user) {
		users.put(user.getUserid(), user);
		users = users;
	}
	
	synchronized void removeUser(String id) {
		users.remove(id);
		users = users;
	}
	
	synchronized String getUserIP(String id) {
		return users.get(id).getUserip().getHostAddress();
	}
	
	synchronized List<Usuario> getUsers(){
		return new ArrayList<Usuario>(users.values());
	}
	
	synchronized String buscarFichero(String fichero) throws FileNotFoundException {
		for(Usuario u : users.values()) {
			for(File f : u.getUserInfo()) {
				if(f.getName().equals(fichero)) { return u.getUserid(); }
			}
		}
		
		throw new FileNotFoundException();
	}
	
	synchronized ObjectOutputStream getOutputStream(String id) {
		return (ObjectOutputStream) users_streams.get(id).get(1);
	}
	
	synchronized void addUserStreams(Usuario user, ObjectInputStream fin, ObjectOutputStream fout) {
		List<Object> streams = new ArrayList<Object>();
		streams.add(fin);
		streams.add(fout);
		users_streams.put(user.getUserid(), streams);
		users_streams = users_streams;  
	}
	
	synchronized void removeUserStreams(String id) {
		users_streams.remove(id);
		users_streams = users_streams;
	}
	
}
