package MultiBufferSynchronized;

public class MonitorMB {
	
	private int MAX;
	volatile private int front = 0, rear = 0, count = 0;
	
	volatile private Producto[] buf = null;
	
	public MonitorMB(int MAX) {
		this.MAX = MAX;
		this.buf = new Producto[MAX];
	}
	
	public synchronized void almacenar(Producto prod[], int ProdId){
		while(count == MAX || (MAX - count) < prod.length) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		for(Producto p : prod) {
			this.buf[rear] = p; this.buf = this.buf;
			System.out.println("Productor " + ProdId + ": Producto " + buf[rear].id + " creado");
		
			rear = (rear + 1) % MAX;
			count++;
		}
		
		notifyAll();
	}
	
	public synchronized Producto[] extraer(int num, int ConsId) {
		while(count == 0 || count < num) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Producto[] ret = new Producto[num];
		
		for(int i = 0; i < num; i++) {
			ret[i] = this.buf[front];
			this.buf[front] = null; this.buf = this.buf;
			System.out.println("Consumidor " + ConsId + ": Producto " + ret[i].id + " consumido");
		
			front = (front + 1) % MAX;
			count--;
		}
		
		notifyAll();
		
		return ret;
	}
}
