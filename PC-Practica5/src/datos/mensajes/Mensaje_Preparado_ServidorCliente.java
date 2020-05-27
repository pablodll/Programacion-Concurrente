package datos.mensajes;

public class Mensaje_Preparado_ServidorCliente extends Mensaje {
	
	private String ip;
	private int port;
	
	public Mensaje_Preparado_ServidorCliente(String origen, String destino, String ip, int port) {
		super(origen, destino);
		this.ip = ip;
		this.port = port;
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.PREPARADO_SERVIDORCLIENTE;
	}
	
	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	
}
