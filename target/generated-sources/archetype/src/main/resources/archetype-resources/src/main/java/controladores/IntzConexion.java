#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package controladores;

import java.sql.Connection;

public interface IntzConexion {

	public Connection generaConexion();
}
