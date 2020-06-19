/*
 * Autor: Pablo Daurel Marina
 */

package datos.mensajes;

public class Mensaje_Lista_Usuarios extends Mensaje {
	
	public Mensaje_Lista_Usuarios(String origen, String destino) {
		super(origen, destino);
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.LISTA_USUARIOS;
	}
	
}
