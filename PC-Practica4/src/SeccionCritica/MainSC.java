/*
 * Autor: Pablo Daurel Marina
 */

package SeccionCritica;

public class MainSC {
	public static void main(String[] args) throws InterruptedException{
		
		int N = 10;
		MonitorSC m = new MonitorSC();
		
		Thread[] hilos1 = new Thread[N];
		Thread[] hilos2 = new Thread[N];
		
		for(int i = 0; i < N; i++) {
			hilos1[i] = new MyThread1(m, 2*i);
			hilos2[i] = new MyThread2(m, 2*i+1);
		}
		for(int i = 0; i < N; i++) {
			hilos1[i].start();
			hilos2[i].start();
		}
		for(int i = 0; i < N; i++) {
			hilos1[i].join();
			hilos2[i].join();
		}
		
		System.out.println(m.getN());
		System.err.println("Acaba");
	}
}
