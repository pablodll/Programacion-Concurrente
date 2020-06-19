/*
 * Autor: Pablo Daurel Marina
 */

package datos.mensajes;

import java.util.List;

import datos.Usuario;

public class Mensaje_Confirmacion_Lista_Usuarios extends Mensaje {
	
	private List<Usuario> listaUsuarios;
	
	public Mensaje_Confirmacion_Lista_Usuarios(String origen, String destino, List<Usuario> lista) {
		super(origen, destino);
		this.listaUsuarios = lista;
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.CONFIRMACION_LISTA_USUARIOS;
	}
	
	public List<Usuario> getListaUsuarios(){
		return listaUsuarios;
	}
	
	
}
