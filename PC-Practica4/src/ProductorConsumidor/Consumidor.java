package ProductorConsumidor;

public class Consumidor extends Thread{
	
	private SharedPC dato;
	
	private MonitorPC m;
	public int id;
	
	public Consumidor(MonitorPC m, SharedPC dato, int id) {
		this.dato = dato;
		
		this.m = m;
		this.id = id;
	}
	
	@Override
	public void run() {
		int i = 0;
		
		while(i < 10) {
				
			@SuppressWarnings("unused")
			Producto prod = m.extraer(this.id);
		
			i++;
		}
	}
}
