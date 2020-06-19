/*
 * Autores: Alberto García Doménech y Pablo Daurel Marina
 */

package ProductorConsumidor;

public class Productor extends Thread{
	
	private SharedPC dato;
	public int id;
	
	public Productor(SharedPC dato, int id) {
		this.dato = dato;
		this.id = id;
	}
	
	@Override
	public void run() {
		int i = 0;
		
		while(i < 10) {
			
			Producto prod = new Producto(dato.prodId++);
			dato.almacen.almacenar(prod, this.id);
			
			i++;
		}
	}
	
}
