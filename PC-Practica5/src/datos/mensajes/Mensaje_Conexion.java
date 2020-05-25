package datos.mensajes;

import datos.Usuario;

public class Mensaje_Conexion extends Mensaje {
	
	public Usuario user;
	
	public Mensaje_Conexion(String origen, String destino, Usuario user) {
		super(origen, destino);
		this.user = user;
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.CONEXION;
	}
	
	public Usuario getUser() {
		return user;
	}
	
	
	
}
