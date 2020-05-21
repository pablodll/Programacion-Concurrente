package datos.mensajes;

public class Mensaje_Pedir_Fichero extends Mensaje {
	
	public Mensaje_Pedir_Fichero(String origen, String destino) {
		super(origen, destino);
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.PEDIR_FICHERO;
	}
	
}
