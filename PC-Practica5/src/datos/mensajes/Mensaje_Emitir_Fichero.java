package datos.mensajes;

public class Mensaje_Emitir_Fichero extends Mensaje {
	
	public Mensaje_Emitir_Fichero(String origen, String destino) {
		super(origen, destino);
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.EMITIR_FICHERO;
	}
	
}
