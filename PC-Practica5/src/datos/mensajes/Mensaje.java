/*
 * Pratica 5 - Programacion Concurrente
 * Autor: Pablo Daurel Marina
 */

package datos.mensajes;

import java.io.Serializable;

public abstract class Mensaje implements Serializable{
	
	String origen;
	String destino;
	
	public Mensaje(String origen, String destino) {
		this.origen = origen;
		this.destino = destino;
	}
	
	public abstract TipoMensaje getTipo();
	
	public String getOrigen() {
		return origen;
	}
	public String getDestino() {
		return destino;
	}
}
