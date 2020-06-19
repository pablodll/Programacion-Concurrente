/*
 * Autor: Pablo Daurel Marina
 */

package SeccionCritica;

public class MonitorSC {
	private int n = 0;
	
	synchronized void inc() {
		n++;
	}
	
	synchronized void dec() {
		n--;
	}
	
	public int getN() {
		return n;
	}
}
