package MultiBufferLockCondition;

import java.util.concurrent.locks.*;

public class MonitorMB {
	
	private int MAX;
	volatile private int front = 0, rear = 0, count = 0;
	volatile private Producto[] buf = null;
	
	final Lock lock;
	final Condition cond1;
	final Condition cond2;
	
	public MonitorMB(int MAX) {
		this.MAX = MAX;
		this.buf = new Producto[MAX];
		
		lock = new ReentrantLock();
		cond1 = lock.newCondition();
		cond2 = lock.newCondition();
	}
	
	public void almacenar(Producto prod[], int ProdId) throws InterruptedException{
//		System.out.println("Consumidor " + ProdId + " ESPERA LOCK");
		lock.lock();
		
//		System.out.println("Productor " + ProdId + " coge LOCK");
		
		try {
			while(count == MAX || (MAX - count) < prod.length) {
				System.out.println("Productor " + ProdId + " ESPERA");
				cond1.await();
			}
			
			for(Producto p : prod) {
				this.buf[rear] = p; this.buf = this.buf;
				System.out.println("Productor " + ProdId + ": Producto " + buf[rear].id + " creado");
			
				rear = (rear + 1) % MAX;
				count++;
			}
			
			cond2.signal();
			
		} finally {
			lock.unlock();
		}
	}
	
	public Producto[] extraer(int num, int ConsId) throws InterruptedException {
//		System.out.println("Consumidor " + ConsId + " ESPERA LOCK");
		
		lock.lock();
		
//		System.out.println("Consumidor " + ConsId + " coge LOCK");
		
		Producto[] ret = new Producto[num];
		try {
			while(count == 0 || count < num) {
				System.out.println("Consumidor " + ConsId + " ESPERA");
				cond2.await();
			}
			
			for(int i = 0; i < num; i++) {
				ret[i] = this.buf[front];
				this.buf[front] = null; this.buf = this.buf;
				System.out.println("Consumidor " + ConsId + ": Producto " + ret[i].id + " consumido");
			
				front = (front + 1) % MAX;
				count--;
			}
			
			cond1.signal();
			
		} finally {
			lock.unlock();
		}
		
		return ret;
	}
}
