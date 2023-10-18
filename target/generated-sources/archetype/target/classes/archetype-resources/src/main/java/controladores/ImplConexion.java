#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;

public class ImplConexion implements IntzConexion{

	@Override
	public Connection generaConexion() {
		
		Connection conexion = null;
		String[] parametrosConexion = configuracionConexion();
		
		if(!parametrosConexion[2].isEmpty()) {
			try {
				Class.forName("org.postgresql.Driver");
				
				conexion = DriverManager.getConnection(parametrosConexion[0],parametrosConexion[1],parametrosConexion[2]);
				boolean esValida = conexion.isValid(50000);
				if(esValida == false) {
					conexion = null;
				}
				System.out.println(esValida ? "[INFORMACIÓN-ConexionPostgresqlImplementacion-generaConexion] Conexión a PostgreSQL válida" : "[ERROR-ConexionPostgresqlImplementacion-generaConexion] Conexión a PostgreSQL no válida");
	            return conexion;
	            
			} catch (ClassNotFoundException cnfe) {
				System.out.println("[ERROR-ConexionPostgresqlImplementacion-generaConexion] Error en registro driver PostgreSQL: " + cnfe);
				return conexion = null;
			} catch (SQLException jsqle) {
				System.out.println("[ERROR-ConexionPostgresqlImplementacion-generaConexion] Error en conexión a PostgreSQL (" + parametrosConexion[0] + "): " + jsqle);
				return conexion = null;
			}
			
		}else {
			System.out.println("[ERROR-ConexionPostgresqlImplementacion-generaConexion] Los parametros de conexion no se han establecido correctamente");	
			return conexion;
		}
		
	}

	private String[] configuracionConexion() {
		
		String user="", pass="", port="", host="", db="", url="";	
		
		Properties propiedadesConexionPostgresql = new Properties();
		try {
			propiedadesConexionPostgresql.load(new FileInputStream(new File("C:${symbol_escape}${symbol_escape}Users${symbol_escape}${symbol_escape}Puesto15${symbol_escape}${symbol_escape}Desktop${symbol_escape}${symbol_escape}CLASES${symbol_escape}${symbol_escape}DWS${symbol_escape}${symbol_escape}EjercicioConexionbdCRUD${symbol_escape}${symbol_escape}src${symbol_escape}${symbol_escape}utils${symbol_escape}${symbol_escape}conexion.properties")));
			user = propiedadesConexionPostgresql.getProperty("user");
			pass = propiedadesConexionPostgresql.getProperty("pass");
			port = propiedadesConexionPostgresql.getProperty("port");
			host = propiedadesConexionPostgresql.getProperty("host");
			db = propiedadesConexionPostgresql.getProperty("db");
			url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
			String[] stringConfiguracion = {url,user,pass};
			
			return stringConfiguracion;
			
		} catch (Exception e) {
			System.out.println("[ERROR-ConexionPostgresqlImplementacion-configuracionConexion] - Error al acceder al fichero propiedades de conexion.");
			return null;
		}

	}
	
}
