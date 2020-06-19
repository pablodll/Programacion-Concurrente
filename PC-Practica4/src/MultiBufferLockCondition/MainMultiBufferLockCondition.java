/*
 * Autor: Pablo Daurel Marina
 */

package MultiBufferLockCondition;

public class MainMultiBufferLockCondition {
	
public static void main(String[] args) throws InterruptedException{
		
		int N = 1;
		int M = 2;
		int MAX = 3;
		
		int num = 3;
		
		MonitorMB m = new MonitorMB(MAX);
		
		SharedMB dato = new SharedMB();
		
		
		Thread[] productores = new Thread[N];
		Thread[] consumidores = new Thread[M];
		
		
		for(int i = 0; i < N; i++) {
			productores[i] = new Productor(num, m, dato, i);
			productores[i].start();
		}
		for(int i = 0; i < M; i++) {
			consumidores[i] = new Consumidor(num, m, dato, i);
			consumidores[i].start();
		}
		
		
		for(int i = 0; i < N; i++) {
			productores[i].join();
		}
		for(int i = 0; i < M; i++) {
			consumidores[i].join();
		}

		System.err.println("Acaba");
	}
	
}
