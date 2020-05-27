package datos.mensajes;

public class Mensaje_Preparado_ClienteServidor extends Mensaje {
	
	String cliente;
	int port;
	
	public Mensaje_Preparado_ClienteServidor(String origen, String destino, String cliente, int port) {
		super(origen, destino);
		this.cliente = cliente;
		this.port = port;
	}

	@Override
	public TipoMensaje getTipo() {
		return  TipoMensaje.PREPARADO_CLIENTE_SEVIDOR;
	}
	
	public String getCliente() {
		return cliente;
	}

	public int getPort() {
		return port;
	}
	
}
