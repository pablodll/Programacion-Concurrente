/*
 * Autores: Alberto García Doménech y Pablo Daurel Marina
 */

package ProductorConsumidor;

public class SharedPC {
	
	public Almacen almacen;
	
	public int prodId = 0;
	
	public SharedPC(Almacen a) {
		this.almacen = a;
	}
	
}
