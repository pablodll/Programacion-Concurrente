/*
 * Autores: Alberto García Doménech y Pablo Daurel Marina
 */

package SeccionCritica;

public class MyThread2 extends MyThread{

	public MyThread2(SharedSC dato, int id) {
		super(dato, id);
	}

	@Override
	public void op() {
		dato.num--;
	}
}
