/// Autor: Pablo Daurell Marina

package ProductorConsumidor;

public interface Almacen {
	/**
	* Almacena (como �ltimo) un producto en el almac�n. Si no hay
	* hueco el proceso que ejecute el m�todo bloquear� hasta que lo
	* haya.
	*/
	public void almacenar(Producto producto, int ProdId);
	
	/**
	* Extrae el primer producto disponible. Si no hay productos el
	* proceso que ejecute el m�todo bloquear� hasta que se almacene un
	* dato.
	*/
	public Producto extraer(int ConsId);
}
