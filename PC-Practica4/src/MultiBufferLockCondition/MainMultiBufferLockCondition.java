/*
 * Autor: Pablo Daurel Marina
 */

package MultiBufferLockCondition;

import java.util.concurrent.ThreadLocalRandom;

public class MainMultiBufferLockCondition {
	
public static void main(String[] args) throws InterruptedException{
		
		int N = 5;
		int M = 10;
		int MAX = 10;
		
		int min = 1;
		int max = 5;
		
		MonitorMBLC m = new MonitorMBLC(MAX);
		SharedMBLC dato = new SharedMBLC();
		
		Thread[] productores = new Thread[N];
		Thread[] consumidores = new Thread[M];
		
		for(int i = 0; i < N; i++) {
			int num = ThreadLocalRandom.current().nextInt(min, max + 1);
			productores[i] = new ProductorMBLC(num, m, dato, i);
			productores[i].start();
		}
		for(int i = 0; i < M; i++) {
			int num = ThreadLocalRandom.current().nextInt(min, max + 1);
			consumidores[i] = new ConsumidorMBLC(num, m, dato, i);
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
