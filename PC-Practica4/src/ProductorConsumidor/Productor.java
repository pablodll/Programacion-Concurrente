package ProductorConsumidor;

public class Productor extends Thread{
	
	private SharedPC dato;
	
	private MonitorPC m;
	public int id;
	
	public Productor(MonitorPC m, SharedPC dato, int id) {
		this.dato = dato;
		
		this.m = m;
		this.id = id;
	}
	
	@Override
	public void run() {
		int i = 0;
		
		while(i < 10) {
			
			Producto prod = new Producto(dato.prodId++);
			m.almacenar(prod, this.id);
			
			i++;
		}
	} 
	
}
