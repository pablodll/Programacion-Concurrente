/*
 * Autores: Alberto García Doménech y Pablo Daurel Marina
 */

package PC2;

public interface Lock {
	public void takeLock(int pid);
	public void releaseLock(int pid);
}
