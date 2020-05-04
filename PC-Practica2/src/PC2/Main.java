package PC2;

public class Main {

	public static void main(String[] args) throws InterruptedException{
		
		int N = 5;
		Shared dato = new Shared(new LockRompeEmpate(2*N), 0);
		Thread[] hilos1 = new Thread[N];
		Thread[] hilos2 = new Thread[N];

		for(int i = 0; i < N; i++) {
			hilos1[i] = new MyThread1(dato, 2*i);
			hilos1[i].start();
			hilos2[i] = new MyThread2(dato, 2*i+1);
			hilos2[i].start();
		}
		for(int i = 0; i < N; i++) {
			hilos1[i].join();
			hilos2[i].join();
		}
		
		System.out.println(dato.num);
		System.err.println("Acaba");
	}
}
