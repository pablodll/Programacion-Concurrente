package ProductorConsumidor;

public class MainProdCons {
	
public static void main(String[] args) throws InterruptedException{
		
		int N = 5;
		int M = 10;
		int MAX = 1;
		
		MonitorPC m = new MonitorPC(MAX);
		
		SharedPC dato = new SharedPC();
		
		
		Thread[] productores = new Thread[N];
		Thread[] consumidores = new Thread[M];
		
		
		for(int i = 0; i < N; i++) {
			productores[i] = new Productor(m, dato, i);
			productores[i].start();
		}
		for(int i = 0; i < M; i++) {
			consumidores[i] = new Consumidor(m, dato, i);
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
