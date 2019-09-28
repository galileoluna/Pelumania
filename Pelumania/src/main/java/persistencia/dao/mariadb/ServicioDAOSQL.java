package persistencia.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProfesionalDTO;
import dto.ServicioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ServicioDAO;

public class ServicioDAOSQL implements ServicioDAO{
	
	private static final String insert = "INSERT INTO servicio(idServicio,Nombre,PrecioLocal,PrecioDolar,Puntos) "
										+ "VALUES(?,?,?,?,?)";
	private static final String update = "";
	private static final String delete = "DELETE FROM servicio WHERE IdServicio = ?";
	private static final String readall = " SELECT * FROM servicio";

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

	public boolean delete(ServicioDTO servicio_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, servicio_a_eliminar.getIdServicio());
			if(statement.executeUpdate() > 0){
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	public List<ServicioDTO> readAll() {

		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				servicios.add(getServicioDTO(resultSet));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return servicios;
	}
	
	private ServicioDTO getServicioDTO(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("idServicio");
		String nombre = resultSet.getString("Nombre");
		double precioLocal = resultSet.getDouble("PrecioLocal");
		double precioDolar =resultSet.getDouble("PrecioDolar");
		int puntos = resultSet.getInt("Puntos");
		
		return new ServicioDTO(id, nombre, precioLocal, precioDolar, puntos );
	}

}
