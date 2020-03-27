package PC2;

public class Main {

	public static void main(String[] args) throws InterruptedException{
			
		int N = 5;
		Shared dato = new Shared(new LockRompeEmpate(2*N), 0);
		Thread[] hilos = new Thread[2*N];

		for(int i = 0; i < N-1; i++) {
			hilos[2*i] = new MyThread1(dato, 2*i);
			hilos[2*i].start();
			hilos[2*i+1] = new MyThread2(dato, 2*i+1);
			hilos[2*i+1].start();
		}
		for(int i = 0; i < N; i++) {
			hilos[i].join();
		}
		
		System.out.println(dato.num);
		System.err.println("Acaba");
	}
}
