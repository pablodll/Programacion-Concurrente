/*
 * Autor: Pablo Daurel Marina
 */

package servidor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import datos.Usuario;

/*
 *  Monitor encargado de gestionar la tabla con la informacion de los usuarios conectados al server 
 */
public class MonitorUsuarios {
	
	volatile private Hashtable<String, Usuario> users = new Hashtable<String, Usuario>();
	
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
	
}
