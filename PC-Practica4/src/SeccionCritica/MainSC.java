package SeccionCritica;

public class MainSC {
	public static void main(String[] args) throws InterruptedException{
		
		int N = 10;
		MonitorSC m = new MonitorSC();
		Thread[] hilos = new Thread[2*N];

		for(int i = 0; i < N-1; i++) {
			hilos[2*i] = new MyThread1(m, 2*i);
			hilos[2*i+1] = new MyThread2(m, 2*i+1);
		}
		for(int i = 0; i < N-1; i++) {
			hilos[2*i].start();
			hilos[2*i+1].start();
		}
		for(int i = 0; i < N; i++) {
			hilos[i].join();
		}
		
		System.out.println(m.getN());
		System.err.println("Acaba");
	}
}
