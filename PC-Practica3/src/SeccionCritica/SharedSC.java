/*
 * Autores: Alberto García Doménech y Pablo Daurel Marina
 */

package SeccionCritica;
import java.util.concurrent.Semaphore;

public class SharedSC {
	
	public int num;
	public Semaphore mutex;
	
	public SharedSC(Semaphore s, int n) {
		this.num = n;
		this.mutex = s;
	}
	
}
