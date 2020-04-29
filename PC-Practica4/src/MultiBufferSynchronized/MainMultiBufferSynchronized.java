package MultiBufferSynchronized;

public class MainMultiBufferSynchronized {
	
public static void main(String[] args) throws InterruptedException{
		
		int N = 5;
		int M = 10;
		int MAX = 6;
		
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
