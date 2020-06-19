/*
 * Autores: Alberto García Doménech y Pablo Daurel Marina
 */

package ProductorConsumidor;

public class Consumidor extends Thread{
	
	private SharedPC dato;
	public int id;
	
	public Consumidor(SharedPC dato, int id) {
		this.dato = dato;
		this.id = id;
	}
	
	@Override
	public void run() {
		int i = 0;
		
		while(i < 10) {
				
			Producto prod = dato.almacen.extraer(this.id);
		
			i++;
		}
	}
}
