package MultiBufferSynchronized;

public class Productor extends Thread{
	
	private SharedMB dato;
	private MonitorMB m;
	private int num;
	
	public int id;
	
	public Productor(int num, MonitorMB m, SharedMB dato, int id) {
		this.dato = dato;
		this.m = m;
		this.num = num;
		this.id = id;
	}
	
	@Override
	public void run() {
		int i = 0;
		
		while(i < 10) {
			Producto[] prod = new Producto[num];
			for(int p = 0; p < num; p++) {
				prod[p] = new Producto(dato.prodId++);	
			}
			
			m.almacenar(prod, this.id);
			
//			i++;
		}
	} 
	
}
