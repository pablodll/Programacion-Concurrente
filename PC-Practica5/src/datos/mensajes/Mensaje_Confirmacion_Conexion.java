/*
 * Autor: Pablo Daurel Marina
 */

package datos.mensajes;

public class Mensaje_Confirmacion_Conexion extends Mensaje {
	
	public Mensaje_Confirmacion_Conexion(String origen, String destino) {
		super(origen, destino);
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.CONFIRMACION_CONEXION;
	}
	
}
