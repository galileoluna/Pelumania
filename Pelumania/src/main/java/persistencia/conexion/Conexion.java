package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Conexion {
		public static Conexion instancia;
		private Connection connection;
		private Logger log = Logger.getLogger(Conexion.class);	
		
		private Conexion()
		{
			try
			{
				//en caso de que alguno este usando MySQL
				Class.forName("com.mysql.jdbc.Driver");
				this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Pelumania?serverTimezone=UTC","root","root");

				//Class.forName("org.mariadb.jdbc.Driver"); // quitar si no es necesario
				//this.connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/Pelumania?serverTimezone=UTC","root","root");
				this.connection.setAutoCommit(false);
				log.info("Conexión exitosa");
			}
			catch(Exception e)
			{
				log.error("Conexión fallida", e);
			}
		}
		
		
		public static Conexion getConexion()   
		{								
			if(instancia == null)
			{
				instancia = new Conexion();
			}
			return instancia;
		}

		public Connection getSQLConexion() 
		{
			return this.connection;
		}
		
		public void cerrarConexion()
		{
			try 
			{
				this.connection.close();
				log.info("Conexion cerrada");
			}
			catch (SQLException e) 
			{
				log.error("Error al cerrar la conexión!", e);
			}
			instancia = null;
		}
}
