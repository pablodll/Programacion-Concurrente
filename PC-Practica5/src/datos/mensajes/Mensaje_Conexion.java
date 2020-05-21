package datos.mensajes;

public class Mensaje_Conexion extends Mensaje {
	
	public Mensaje_Conexion(String origen, String destino) {
		super(origen, destino);
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.CONEXION;
	}
	
	
	
}
