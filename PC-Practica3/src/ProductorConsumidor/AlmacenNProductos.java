/*
 * Autores: Alberto García Doménech y Pablo Daurel Marina
 */

package ProductorConsumidor;

import java.util.concurrent.Semaphore;

public class AlmacenNProductos implements Almacen{
	
	private int MAX;
	volatile private int INI = 0, FIN = 0;
	
	volatile private Producto[] prod = null;
	
	private Semaphore lleno;
	private Semaphore vacio;
	private Semaphore mutexP;
	private Semaphore mutexC;
	
	public AlmacenNProductos(int MAX) {
		this.MAX = MAX;
		this.prod = new Producto[MAX];
		this.lleno = new Semaphore(0);
		this.vacio = new Semaphore(MAX);
		this.mutexP = new Semaphore(1);
		this.mutexC = new Semaphore(1);
	}
	
	@Override
	public void almacenar(Producto producto, int ProdId) {
		try {
			this.vacio.acquire();
			this.mutexP.acquire();
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		
		this.prod[FIN] = producto; this.prod = this.prod;
		System.out.println("Productor " + ProdId + ": Producto " + prod[FIN].id + " creado");
		
		FIN = (FIN + 1) % MAX;
		
		this.mutexP.release();
		this.lleno.release();
	}

	@Override
	public Producto extraer(int ConsId) {
		try {
			this.lleno.acquire();
			this.mutexC.acquire();
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		
		Producto ret = this.prod[INI];
		this.prod[INI] = null;
		System.out.println("Consumidor " + ConsId + ": Producto " + ret.id + " consumido");
		
		INI = (INI + 1) % MAX;
		
		this.mutexC.release();
		this.vacio.release();
		
		return ret;
	}
	
}
