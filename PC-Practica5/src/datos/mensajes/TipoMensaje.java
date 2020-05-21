package datos.mensajes;

public enum TipoMensaje {
	// Cliente
	CONEXION,
	LISTA_USUARIOS,
	PEDIR_FICHERO,
	PREPARADO_CLIENTE_SEVIDOR,
	CERRAR_CONEXION,
	
	// Servidor
	CONFIRMACION_CONEXION,
	CONFIRMACION_LISTA_USUARIOS,
	EMITIR_FICHERO,
	PREPARADO_SERVIDORCLIENTE,
	CONFIRMACION_CERRAR_CONEXION	 
}
