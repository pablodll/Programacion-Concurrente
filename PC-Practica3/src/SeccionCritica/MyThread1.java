/*
 * Autores: Alberto Garc�a Dom�nech y Pablo Daurel Marina
 */

package SeccionCritica;

public class MyThread1 extends MyThread{

	public MyThread1(SharedSC dato, int id) {
		super(dato, id);
	}

	@Override
	public void op() {
		dato.num++;
	}
}
