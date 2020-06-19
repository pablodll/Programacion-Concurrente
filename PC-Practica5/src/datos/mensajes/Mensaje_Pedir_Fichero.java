/*
 * Autor: Pablo Daurel Marina
 */

package datos.mensajes;

public class Mensaje_Pedir_Fichero extends Mensaje {
	
	private String fichero;
	
	public Mensaje_Pedir_Fichero(String origen, String destino, String fichero) {
		super(origen, destino);
		this.fichero = fichero;
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.PEDIR_FICHERO;
	}
	public String getFichero() {
		return fichero;
	}
	
}
