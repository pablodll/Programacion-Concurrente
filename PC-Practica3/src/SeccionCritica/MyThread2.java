/*
 * Autores: Alberto Garc�a Dom�nech y Pablo Daurel Marina
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
