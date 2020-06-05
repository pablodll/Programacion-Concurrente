package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import datos.Usuario;


/* 
 * Monitor encargado de gestionar la tabla con los streams de cada usuario conectado al server
 */
public class MonitorStreams {
	
	volatile private Hashtable<String, List<Object>> users_streams = new Hashtable<String, List<Object>>();
	
	
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
