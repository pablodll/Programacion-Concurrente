package SeccionCritica;

public class MyThread2 extends Thread{

	private MonitorSC m;
	
	public MyThread2(MonitorSC m, int id) {
		this.m = m;
	}

	@Override
	public void run() {
		m.dec();
	}
}
