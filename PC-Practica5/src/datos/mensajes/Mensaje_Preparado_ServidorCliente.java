/*
 * Autor: Pablo Daurel Marina
 */

package datos.mensajes;

public class Mensaje_Preparado_ServidorCliente extends Mensaje {
	
	private String ip;
	private int port;
	private String fichero;
	
	public Mensaje_Preparado_ServidorCliente(String origen, String destino, String fichero, String ip, int port) {
		super(origen, destino);
		this.ip = ip;
		this.port = port;
		this.fichero = fichero;
	}

	@Override
	public TipoMensaje getTipo() {
		return TipoMensaje.PREPARADO_SERVIDORCLIENTE;
	}
	
	public String getFichero() {
		return fichero;
	}
	
	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	
}
