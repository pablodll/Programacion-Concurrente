package datos.mensajes;

public class Mensaje_Preparado_ClienteServidor extends Mensaje {
	
	public Mensaje_Preparado_ClienteServidor(String origen, String destino) {
		super(origen, destino);
	}

	@Override
	public TipoMensaje getTipo() {
		return  TipoMensaje.PREPARADO_CLIENTE_SEVIDOR;
	}
	
}
