package PC2;

public class MyThread2 extends Thread {
	private Shared dato;
	
	public int id;
	
	public MyThread2(Shared dato, int id) {
		this.dato = dato;
		this.id = id;
	}
	
	public void run(){
		
		for(int i = 0; i < 3000; i++) {
			dato.lock.takeLock(id);
			dato.num--;
			dato.lock.releaseLock(id);
		}
		
//		for(int i = 0; i < 3000; i++) {
//			dato.inDec = true;
//			dato.last = 2;
//			while(dato.inInc &&	 dato.last == 2) {}
//			dato.num--;
//			dato.inDec = false;
//		}
		
	}
}
