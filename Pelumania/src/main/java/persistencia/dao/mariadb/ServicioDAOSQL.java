package persistencia.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dto.ServicioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ServicioDAO;

public class ServicioDAOSQL implements ServicioDAO{
	
	private static final String insert = "INSERT INTO servicio(idServicio,Nombre,PrecioLocal,PrecioDolar,Puntos) "
										+ "VALUES(?,?,?,?,?)";
	private static final String update = "";
	private static final String delete = "";
	private static final String readall = "";

	public boolean insert(ServicioDTO servicio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, servicio.getIdServicio());
			statement.setString(2, servicio.getNombre());
			statement.setDouble(3, servicio.getPrecioLocal());
			statement.setDouble(4, servicio.getPrecioDolar());
			statement.setInt(5, servicio.getPuntos());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}
	
	public boolean update (ServicioDTO servicio_a_actualizar) {
		return false;
	}

	public boolean delete(ServicioDTO cliente_a_eliminar) {

		return false;
	}

	public List<ServicioDTO> readAll() {

		return null;
	}

}
