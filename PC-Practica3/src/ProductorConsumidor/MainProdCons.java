package ProductorConsumidor;

public class MainProdCons {
	
public static void main(String[] args) throws InterruptedException{
		
		int N = 5;
		int M = 10;
		int MAX = 3;
		
		
		Almacen almacen = new AlmacenNProductos(MAX);
		
		SharedPC dato = new SharedPC(almacen);
		
		
//		Thread[] hilos = new Thread[2*N];

//		for(int i = 0; i < N-1; i++) {
//			hilos[2*i] = new Productor(dato, 2*i);
//			hilos[2*i+1] = new Consumidor(dato, 2*i+1);
//		}
//		for(int i = 0; i < N-1; i++) {
//			hilos[2*i].start();
//			hilos[2*i+1].start();
//		}
//		for(int i = 0; i < N; i++) {
//			hilos[i].join();
//		}
		
		Thread[] productores = new Thread[N];
		Thread[] consumidores = new Thread[M];
		
		for(int i = 0; i < N; i++) {
			productores[i] = new Productor(dato, i);
			productores[i].start();
		}
		for(int i = 0; i < M; i++) {
			consumidores[i] = new Consumidor(dato, i);
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
