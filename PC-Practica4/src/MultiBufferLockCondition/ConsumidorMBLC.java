/*
 * Autor: Pablo Daurel Marina
 */

package MultiBufferLockCondition;

public class ConsumidorMBLC extends Thread{
	
	private SharedMBLC dato;
	private MonitorMBLC m;
	private int num;
	
	public int id;
	
	public ConsumidorMBLC(int num, MonitorMBLC m, SharedMBLC dato, int id) {
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
				ProductoMBLC[] prod = m.extraer(this.num, this.id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
//			i++;
		}
	}
}
