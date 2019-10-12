package persistencia.dao.mariadb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dto.ProfesionalDTO;
import dto.ServicioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ServicioDAO;

public class ServicioDAOSQL implements ServicioDAO{
	
	private static final String insert = "INSERT INTO servicio(idServicio,Nombre,PrecioLocal,PrecioDolar,Duracion,Puntos,Estado) "
										+ "VALUES(?,?,?,?,?,?,?)";
	private static final String update = "UPDATE  Servicio SET Nombre=?, PrecioLocal=?, PrecioDolar=?,"
			+ " Duracion=?, Puntos=?, Estado=? WHERE IdServicio=?";
	private static final String delete = "UPDATE  Servicio SET Estado=? WHERE idServicio = ?";
	private static final String readall = " SELECT * FROM servicio";
	private static final String getById = "SELECT * FROM servicio WHERE idServicio = ?";
	private static final String deleteRealServicio = "DELETE FROM Servicio WHERE idServicio = ?";
	private static final String readBuscador = "SELECT * FROM servicio WHERE ? LIKE ?";
	
	private static final String ESTADO_INACTIVO = "Inactivo";


	public boolean insert(ServicioDTO servicio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, servicio.getIdServicio());
			statement.setString(2, servicio.getNombre());
			statement.setBigDecimal(3, servicio.getPrecioLocal());
			statement.setBigDecimal(4, servicio.getPrecioDolar());
			statement.setTime(5, Time.valueOf(servicio.getDuracion()));
			statement.setInt(6, servicio.getPuntos());
			statement.setString(7, servicio.getEstado());	
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
		
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			
			statement.setString(1, servicio_a_actualizar.getNombre());
			statement.setBigDecimal(2, servicio_a_actualizar.getPrecioLocal());
			statement.setBigDecimal(3, servicio_a_actualizar.getPrecioDolar());
			statement.setTime(4, Time.valueOf(servicio_a_actualizar.getDuracion()));
			statement.setInt(5, servicio_a_actualizar.getPuntos());
			statement.setString(6, servicio_a_actualizar.getEstado());
			statement.setInt(7, servicio_a_actualizar.getIdServicio());
			
			chequeoUpdate = statement.executeUpdate();
			conexion.getSQLConexion().commit();
			if(chequeoUpdate > 0)
					return true;
		} 
		catch (SQLException e) 
		{
			System.out.println("false");
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean delete(ServicioDTO servicio_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, ESTADO_INACTIVO);
			statement.setInt(2, servicio_a_eliminar.getIdServicio());
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
	
	public ServicioDTO getById(int idServicio)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Connection conexion = Conexion.getConexion().getSQLConexion();
		List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
		try 
		{
			statement = conexion.prepareStatement(getById);
			statement.setInt(1, idServicio);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				servicios.add(getServicioDTO(resultSet));
			}
	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return servicios.get(0);
	}
	
	protected static ServicioDTO obtenerPorId(int idServicio)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Connection conexion = Conexion.getConexion().getSQLConexion();
		List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
		try 
		{
			statement = conexion.prepareStatement(getById);
			statement.setInt(1, idServicio);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				servicios.add(getServicioDTO(resultSet));
			}
	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return servicios.get(0);
	}
	
	
	public boolean deleteRealServicio(ServicioDTO servicio_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(deleteRealServicio);
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
	
	protected static ServicioDTO getServicioDTO(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("idServicio");
		String nombre = resultSet.getString("Nombre");
		BigDecimal precioLocal = resultSet.getBigDecimal("PrecioLocal");
		BigDecimal precioDolar =resultSet.getBigDecimal("PrecioDolar");
		Time duracion = resultSet.getTime("Duracion");
		int puntos = resultSet.getInt("Puntos");
		String estado = resultSet.getString("Estado");
		
		return new ServicioDTO(id, nombre, precioLocal, precioDolar, duracion.toLocalTime(), puntos, estado);
	}
	
	@Override
	public List<ServicioDTO> obtenerServBuscado(String variable, String value) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ServicioDTO> servicio = new ArrayList<ServicioDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			if(variable.equals("Todos")) {
				statement = conexion.getSQLConexion().prepareStatement(readall);
			}else {
				statement = conexion.getSQLConexion().prepareStatement(readall+" WHERE "+variable+ " LIKE '"+value+"'");
			}
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				servicio.add(getServicioDTO(resultSet));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return servicio;
	}

}
