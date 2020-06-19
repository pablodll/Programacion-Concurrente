/*
 * Autor: Pablo Daurel Marina
 */

package MultiBufferSynchronized;

public class ConsumidorMBS extends Thread{
	
	private SharedMBS dato;
	private MonitorMBS m;
	private int num;
	
	public int id;
	
	public ConsumidorMBS(int num, MonitorMBS m, SharedMBS dato, int id) {
		this.dato = dato;
		this.m = m;
		this.num = num;
		this.id = id;
	}
	
	@Override
	public void run() {
		int i = 0;
		
		while(i < 10) {
				
			@SuppressWarnings("unused")
			ProductoMBS[] prod = m.extraer(this.num, this.id);
		
//			i++;
		}
	}
}
