/*
 * Autores: Alberto García Doménech y Pablo Daurel Marina
 */

package SeccionCritica;

public abstract class MyThread extends Thread{
	
	public SharedSC dato;
	public int id;
	
	public MyThread(SharedSC dato, int id) {
		this.dato = dato;
		this.id = id;
	}
	
	public void run() {
		for(int i = 0; i < 3000; i++) {
			try {
				
				dato.mutex.acquire();
				this.op();
				dato.mutex.release();
				
			}catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}
	
	public abstract void op();

}
