/*
 * Autor: Pablo Daurel Marina
 */

package SeccionCritica;

public class MyThread1 extends Thread{
	
	private MonitorSC m;
	
	public MyThread1(MonitorSC m, int id) {
		this.m = m;
	}

	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			m.inc();
		}
	}
}
