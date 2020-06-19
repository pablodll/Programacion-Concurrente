/*
 * Autor: Pablo Daurel Marina
 */

package MultiBufferLockCondition;

public class Consumidor extends Thread{
	
	private SharedMB dato;
	private MonitorMB m;
	private int num;
	
	public int id;
	
	public Consumidor(int num, MonitorMB m, SharedMB dato, int id) {
		this.dato = dato;
		this.m = m;
		this.num = num;
		this.id = id;
	}
	
	@Override
	public void run() {
		int i = 0;
		
		while(i < 10) {
				
			try {
				@SuppressWarnings("unused")
				Producto[] prod = m.extraer(this.num, this.id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
//			i++;
		}
	}
}
