/*
 * Pratica 5 - Programacion Concurrente
 * Autor: Pablo Daurel Marina
 */

package datos.mensajes;

public class Mensaje_Confirmacion_Cerrar_Conexion extends Mensaje {
	
	public Mensaje_Confirmacion_Cerrar_Conexion(String origen, String destino) {
		super(origen, destino);
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.CONFIRMACION_CERRAR_CONEXION;
	}
	
}
