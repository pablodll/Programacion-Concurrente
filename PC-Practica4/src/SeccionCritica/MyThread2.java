/*
 * Autor: Pablo Daurel Marina
 */

package SeccionCritica;

public class MyThread2 extends Thread{

	private MonitorSC m;
	
	public MyThread2(MonitorSC m, int id) {
		this.m = m;
	}

	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			m.dec();
		}
	}
}
