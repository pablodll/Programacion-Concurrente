package datos.mensajes;

public class Mensaje_Preparado_ClienteServidor extends Mensaje {
	
	String cliente;
	String ip;
	String fichero;
	int port;
	
	public Mensaje_Preparado_ClienteServidor(String origen, String destino, String cliente, String fichero, int port, String ip) {
		super(origen, destino);
		this.cliente = cliente;
		this.port = port;
		this.fichero = fichero;
	}

	@Override
	public TipoMensaje getTipo() {
		return  TipoMensaje.PREPARADO_CLIENTE_SEVIDOR;
	}
	
	public String getCliente() {
		return cliente;
	}

	public String getFichero(){
		return fichero;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getIP() {
		return ip;
	}
	
}
