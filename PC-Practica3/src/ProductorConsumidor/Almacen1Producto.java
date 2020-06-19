/*
 * Autores: Alberto García Doménech y Pablo Daurel Marina
 */

package ProductorConsumidor;

import java.util.concurrent.Semaphore;

public class Almacen1Producto implements Almacen{

	volatile private Producto prod = null;
	public SharedPC dato;
	
	Semaphore lleno;
	Semaphore vacio ;
	
	public  Almacen1Producto() {
		lleno = new Semaphore(0);
		vacio = new Semaphore(1);
	}
	
	@Override
	public void almacenar(Producto producto, int ProdId) {
		try {
			this.vacio.acquire();
		}catch(InterruptedException e) {
			System.out.println(e);
		}
		
		this.prod = producto;
		System.out.println("Productor " + ProdId + ": Producto " + prod.id + " creado");
		
		this.lleno.release();
	}

	@Override
	public Producto extraer(int ConsId) {
		try {
			this.lleno.acquire();
		}catch(Exception e) {
			System.err.println(e);
		}
		
		Producto ret = this.prod;
		this.prod = null;
		
		System.out.println("Consumidor " + ConsId + ": Producto " + ret.id + " consumido");
		
		this.vacio.release();
		
		return ret;
	}
	
}
