/*
 * Autor: Pablo Daurel Marina
 */

package datos.mensajes;

public class Mensaje_Cerrar_Conexion extends Mensaje {
	
	public String nombre;
	
	public Mensaje_Cerrar_Conexion(String origen, String destino, String nombre) {
		super(origen, destino);
		this.nombre = nombre;
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.CERRAR_CONEXION;
	}
	
	public String getNombre() {
		return nombre;
	}
	
}
