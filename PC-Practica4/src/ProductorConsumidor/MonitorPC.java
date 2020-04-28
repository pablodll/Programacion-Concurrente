package ProductorConsumidor;

public class MonitorPC {
	
	private int MAX;
	volatile private int front = 0, rear = 0, count = 0;
	
	volatile private Producto[] buf = null;
	
	public MonitorPC(int MAX) {
		this.MAX = MAX;
		this.buf = new Producto[MAX];
	}
	
	public synchronized void almacenar(Producto prod, int ProdId){
		while(count == MAX) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		this.buf[rear] = prod; this.buf = this.buf;
		System.out.println("Productor " + ProdId + ": Producto " + buf[rear].id + " creado");
		
		rear = (rear + 1) % MAX;
		count++;
		
		notifyAll();
	}
	
	public synchronized Producto extraer(int ConsId) {
		while(count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Producto ret = this.buf[front];
		this.buf[front] = null; this.buf = this.buf;
		System.out.println("Consumidor " + ConsId + ": Producto " + ret.id + " consumido");
		
		front = (front + 1) % MAX;
		count--;
		
		notifyAll();
		
		return ret;
	}
}
