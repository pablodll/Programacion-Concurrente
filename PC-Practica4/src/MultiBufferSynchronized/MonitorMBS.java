/*
 * Autor: Pablo Daurel Marina
 */

package MultiBufferSynchronized;

public class MonitorMBS {
	
	private int MAX;
	volatile private int front = 0, rear = 0, count = 0;
	
	volatile private ProductoMBS[] buf = null;
	
	public MonitorMBS(int MAX) {
		this.MAX = MAX;
		this.buf = new ProductoMBS[MAX];
	}
	
	public synchronized void almacenar(ProductoMBS prod[], int ProdId){
		while(count == MAX || (MAX - count) < prod.length) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Productor " + ProdId + " (Produce: " + prod.length + "):");
		
		for(ProductoMBS p : prod) {
			this.buf[rear] = p; this.buf = this.buf;
			System.out.println("\t -Producto " + buf[rear].id + " creado");
		
			rear = (rear + 1) % MAX;
			count++;
		}
		
		notifyAll();
	}
	
	public synchronized ProductoMBS[] extraer(int num, int ConsId) {
		while(count == 0 || count < num) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		ProductoMBS[] ret = new ProductoMBS[num];
		
		System.out.println("Consumidor " + ConsId + " (Consume: " + num + "):"); 
		
		for(int i = 0; i < num; i++) {
			ret[i] = this.buf[front];
			this.buf[front] = null; this.buf = this.buf;
			System.out.println("\t -Producto " + ret[i].id + " consumido");
		
			front = (front + 1) % MAX;
			count--;
		}
		
		notifyAll();
		
		return ret;
	}
}
