/*
 * Autor: Pablo Daurel Marina
 */

package MultiBufferLockCondition;

public class ProductorMBLC extends Thread{
	
	private SharedMBLC dato;
	private MonitorMBLC m;
	private int num;
	
	public int id;
	
	public ProductorMBLC(int num, MonitorMBLC m, SharedMBLC dato, int id) {
		this.dato = dato;
		this.m = m;
		this.num = num;
		this.id = id;
	}
	
	@Override
	public void run() {
		int i = 0;
		
		while(i < 10) {
			ProductoMBLC[] prod = new ProductoMBLC[num];
			for(int p = 0; p < num; p++) {
				prod[p] = new ProductoMBLC(dato.prodId++);	
			}
			
			try {
				m.almacenar(prod, this.id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			i++;
		}
	} 
	
}
