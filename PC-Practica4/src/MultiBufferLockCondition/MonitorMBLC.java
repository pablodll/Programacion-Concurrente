/*
 * Autor: Pablo Daurel Marina
 */

package MultiBufferLockCondition;

import java.util.concurrent.locks.*;

public class MonitorMBLC {
	
	private int MAX;
	volatile private int front = 0, rear = 0, count = 0;
	volatile private ProductoMBLC[] buf = null;
	
	final Lock lock;
	final Condition cond1;
	final Condition cond2;
	
	public MonitorMBLC(int MAX) {
		this.MAX = MAX;
		this.buf = new ProductoMBLC[MAX];
		
		lock = new ReentrantLock();
		cond1 = lock.newCondition();
		cond2 = lock.newCondition();
	}
	
	public void almacenar(ProductoMBLC prod[], int ProdId) throws InterruptedException{
		lock.lock();
		
		while(count == MAX || (MAX - count) < prod.length) {
			cond1.await();
		}
		
		System.out.println("Productor " + ProdId + " (Produce: " + prod.length + "):");
		
		for(ProductoMBLC p : prod) {
			this.buf[rear] = p; this.buf = this.buf;
			System.out.println("\t- Producto " + buf[rear].id + " creado");
		
			rear = (rear + 1) % MAX;
			count++;
		}
		
		cond2.signal();
		lock.unlock();
	}
	
	public ProductoMBLC[] extraer(int num, int ConsId) throws InterruptedException {
		lock.lock();
		
		ProductoMBLC[] ret = new ProductoMBLC[num];
		
		while(count == 0 || count < num) {
			cond2.await();
		}
		
		System.out.println("Consumidor " + ConsId + " (Consume: " + num + "):");
		
		for(int i = 0; i < num; i++) {
			ret[i] = this.buf[front];
			this.buf[front] = null; this.buf = this.buf;
			System.out.println("\t -Producto " + ret[i].id + " consumido");
		
			front = (front + 1) % MAX;
			count--;
		}
		
		cond1.signal();
		lock.unlock();
		
		return ret;
	}
}
