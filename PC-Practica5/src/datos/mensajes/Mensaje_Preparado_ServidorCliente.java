package datos.mensajes;

public class Mensaje_Preparado_ServidorCliente extends Mensaje {
	
	public Mensaje_Preparado_ServidorCliente(String origen, String destino) {
		super(origen, destino);
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.PREPARADO_SERVIDORCLIENTE;
	}
	
}
