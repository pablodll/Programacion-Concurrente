package SeccionCritica;

public class MyThread1 extends Thread{
	
	private MonitorSC m;
	
	public MyThread1(MonitorSC m, int id) {
		this.m = m;
	}

	@Override
	public void run() {
		m.inc();
	}
}
