/*
 * Autor: Pablo Daurel Marina
 */

package MultiBufferSynchronized;

import java.util.concurrent.ThreadLocalRandom;

public class MainMultiBufferSynchronized {
	
public static void main(String[] args) throws InterruptedException{
		
		int N = 5;
		int M = 10;
		int MAX = 10;
		
		int min = 1;
		int max = 5;
		
		MonitorMBS m = new MonitorMBS(MAX);
		SharedMBS dato = new SharedMBS();
		
		Thread[] productores = new Thread[N];
		Thread[] consumidores = new Thread[M];
		
		
		for(int i = 0; i < N; i++) {
			int num = ThreadLocalRandom.current().nextInt(min, max + 1);
			productores[i] = new ProductorMBS(num, m, dato, i);
			productores[i].start();
		}
		for(int i = 0; i < M; i++) {
			int num = ThreadLocalRandom.current().nextInt(min, max + 1);
			consumidores[i] = new ConsumidorMBS(num, m, dato, i);
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
