package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
	
	synchronized List<Usuario> getUsers(){
		return new ArrayList<Usuario>(users.values());
	}
	
	synchronized void connectUser(Usuario user, ObjectInputStream fin, ObjectOutputStream fout) {
		List<Object> streams = new ArrayList<Object>();
		streams.add(fin);
		streams.add(fout);
		users_streams.put(user.getUserid(), streams);
		users_streams = users_streams;  
	}
	
	synchronized void disconnectUser(String id) {
		users_streams.remove(id);
		users_streams = users_streams;
	}
}
