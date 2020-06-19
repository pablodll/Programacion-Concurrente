/*
 * Autor: Pablo Daurel Marina
 */

package datos.mensajes;

public class Mensaje_Emitir_Fichero extends Mensaje {
	
	String fichero;
	String cliente;
	
	public Mensaje_Emitir_Fichero(String origen, String destino, String fichero, String cliente) {
		super(origen, destino);
		this.fichero = fichero;
		this.cliente = cliente;
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.EMITIR_FICHERO;
	}
	
	public String getFichero() {
		return fichero;
	}
	
	public String getCliente(){
		return cliente;
	}
	
}
