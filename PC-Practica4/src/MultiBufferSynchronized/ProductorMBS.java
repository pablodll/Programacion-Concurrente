/*
 * Autor: Pablo Daurel Marina
 */

package MultiBufferSynchronized;

public class ProductorMBS extends Thread{
	
	private SharedMBS dato;
	private MonitorMBS m;
	private int num;
	
	public int id;
	
	public ProductorMBS(int num, MonitorMBS m, SharedMBS dato, int id) {
		this.dato = dato;
		this.m = m;
		this.num = num;
		this.id = id;
	}
	
	@Override
	public void run() {
		int i = 0;
		
		while(i < 10) {
			ProductoMBS[] prod = new ProductoMBS[num];
			for(int p = 0; p < num; p++) {
				prod[p] = new ProductoMBS(dato.prodId++);	
			}
			
			m.almacenar(prod, this.id);
			
//			i++;
		}
	} 
	
}
