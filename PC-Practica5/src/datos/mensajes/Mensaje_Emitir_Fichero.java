package datos.mensajes;

public class Mensaje_Emitir_Fichero extends Mensaje {
	
	String fichero;
	
	public Mensaje_Emitir_Fichero(String origen, String destino, String fichero) {
		super(origen, destino);
		this.fichero = fichero;
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.EMITIR_FICHERO;
	}
	
	public String getFicjero() {
		return fichero;
	}
	
}
