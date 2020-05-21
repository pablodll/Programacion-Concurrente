package datos.mensajes;

public class Mensaje_Cerrar_Conexion extends Mensaje {
	
	public Mensaje_Cerrar_Conexion(String origen, String destino) {
		super(origen, destino);
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.CERRAR_CONEXION;
	}
	
}
