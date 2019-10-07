package persistencia.dao.mariadb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dto.CitaDTO;
import dto.ClienteDTO;
import dto.ProfesionalDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.CitaDAO;

public class CitaDAOSQL implements CitaDAO{

	private static final String insert = "INSERT INTO Cita( idCita, idUsuario, idCliente,"
			+ "NombreCliente, ApellidoCliente, EstadoTurno, IdProfesional, IdServicio,"
			+ "PrecioLocal, PrecioDolar, HoraInicio, HoraFin,  Dia, IdSucursal) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String insertSinRegistrar = "INSERT INTO Cita( idCita, idUsuario,"
			+ "NombreCliente, ApellidoCliente, EstadoTurno, IdProfesional, IdServicio,"
			+ "PrecioLocal, PrecioDolar, HoraInicio, HoraFin,  Dia, IdSucursal) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String delete = "UPDATE  Cita SET EstadoCita =? WHERE idCita = ?";
	private static final String deleteReal = "DELETE FROM Cita WHERE idCita = ?";
	private static final String cancel = "UPDATE Cita SET EstadoCita = ? WHERE idCita = ?";
	private static final String readall = "SELECT * FROM Cita";
	private static final String readTabla = "SELECT p.Nombre,p.Apellido,cl.Nombre,cl.Apellido,s.NombreSucursal,c.idCita, c.EstadoTurno, c.PrecioLocal,c.PrecioDolar, c.Hora,c.Dia FROM cita c JOIN cliente cl USING (idCliente) JOIN sucursal s USING (idSucursal) JOIN profesional p USING (idProfesional)WHERE c.Dia=?";
	private static final String update = "UPDATE  Cita SET ____ WHERE idCliente=?";
	
	private static final String DADODEBAJA = "Cerrado";
	private static final String CANCELADA = "Cancelada";

	@Override
	public boolean insert(CitaDTO cita) {
		{
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
			try
			{
				statement = conexion.prepareStatement(insert);
				statement.setInt	    (1, cita.getIdCita());
				statement.setInt	    (2, cita.getIdUsuario());
				statement.setInt	    (3, cita.getIdCliente());
				statement.setString     (4, cita.getNombre());
				statement.setString     (5, cita.getApellido());
				statement.setString     (6, cita.getEstado());
				statement.setInt        (7, cita.getIdProfesional());
				statement.setInt        (8, cita.getIdServicio());
				statement.setBigDecimal (9, cita.getPrecioLocal());
				statement.setBigDecimal (10, cita.getPrecioDolar());
				statement.setTime       (11, Time.valueOf(cita.getHoraInicio()));
				statement.setTime       (12, Time.valueOf(cita.getHoraFin()));
				statement.setDate       (13, Date.valueOf(cita.getFecha()));
				statement.setInt        (14, cita.getIdSucursal());

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
	}
	
	@Override
	public boolean insertSinRegistrar(CitaDTO cita) {
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
			try
			{
				statement = conexion.prepareStatement(insertSinRegistrar);
				statement.setInt	    (1, cita.getIdCita());
				statement.setInt	    (2, cita.getIdUsuario());
				statement.setString     (3, cita.getNombre());
				statement.setString     (4, cita.getApellido());
				statement.setString     (5, cita.getEstado());
				statement.setInt        (6, cita.getIdProfesional());
				statement.setInt        (7, cita.getIdServicio());
				statement.setBigDecimal (8, cita.getPrecioLocal());
				statement.setBigDecimal (9, cita.getPrecioDolar());
				statement.setTime       (10, Time.valueOf(cita.getHoraInicio()));
				statement.setTime       (11, Time.valueOf(cita.getHoraFin()));
				statement.setDate       (12, Date.valueOf(cita.getFecha()));
				statement.setInt        (13, cita.getIdSucursal());

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
	
	@Override
	public boolean delete(CitaDTO cita_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setString (1, DADODEBAJA);
			statement.setInt	(2, cita_a_eliminar.getIdCita());
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
	
	public boolean cancelar(CitaDTO cita_a_cancelar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(cancel);
			statement.setString (1, CANCELADA);
			statement.setInt	(2, cita_a_cancelar.getIdCita());
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
	
	@Override
	public boolean update(CitaDTO cita_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<CitaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<CitaDTO> citas = new ArrayList<CitaDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				citas.add(getCitaDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return citas;
	}
	
	@Override
	public boolean deleteReal(CitaDTO cita_a_eliminar) {
		{
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isInsertExitoso = false;
			try
			{
				statement = conexion.prepareStatement(deleteReal);
				statement.setInt	(1, cita_a_eliminar.getIdCita());
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
	}

	public List<CitaDTO> readCitaPorDia (String dia_a_buscar) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<CitaDTO> profesional = new ArrayList<CitaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readTabla);
			statement.setString(1, dia_a_buscar);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				profesional.add(getCitaDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return profesional;
	}
	
	
	// Este get Cita recibe los parametros necesarios para armar CitaDTO para la visualizacion del turno por tabla
	private CitaDTO getCitaDTO(ResultSet resultSet) throws SQLException{
		int idCita = resultSet.getInt("c.idCita");
		String profesional = resultSet.getString("p.Nombre")+" "+resultSet.getString("p.Apellido");
		String cliente= resultSet.getString("cl.Nombre")+" "+resultSet.getString("cl.Apellido");
		Time hora=resultSet.getTime("hora");
		Date dia=resultSet.getDate("Dia");
		String sucursal=resultSet.getString("s.NombreSucursal");
		String estado=resultSet.getString("c.EstadoTurno");
		System.out.println(estado);
		return new CitaDTO(idCita,profesional,cliente,hora,dia,sucursal,estado);
	}
}
