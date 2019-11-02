package persistencia.dao.mariadb;

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
import dto.ServicioTurnoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ServicioTurnoDAO;

public class ServicioTurnoDAOSQL implements ServicioTurnoDAO {
	
	private static final String insert = "INSERT INTO ServicioTurno (idCita, idServicio, idProfesional, horaInicio, horaFin) VALUES(?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM ServicioTurno WHERE idCita = ? AND idServicio = ? AND idProfesional = ?";
	private static final String update = "";
	private static final String readAll = "";
	private static final String getCitasByIdServicio = "SELECT * FROM ServicioTurno WHERE idServicio = ?";
	private static final String getByIdCita = "SELECT * FROM ServicioTurno WHERE idCita = ?";
	private static final String getByIdServicio = "SELECT * FROM ServicioTurno WHERE idServicio = ?";
	
	private static final String profesionalTrabaja = "SELECT 1 AS Disponible"+
													 " FROM DiasLaborales"+  
													 " WHERE DiasLaborales.IdProfesional = ?"+ 
													 " AND HoraEntrada <= ? AND HoraSalida >= ? AND Dia = ?";
	// Recibe idProfesional, hora que iniciaria el turno, hora que terminaria, y dia de la semana (lunes, mart, ect)
	
	private static final String profesionalOcupado = "SELECT 1 AS Ocupado, idCita,"+
													" idServicio, horaInicio, horaFin" + 
													" FROM ServicioTurno"+
													" WHERE idProfesional = ?"+ 
													" AND (( ? < HoraInicio AND ? > HoraInicio)"+
													" OR (?= HoraInicio)"+
													" OR (? > HoraInicio AND ? <= HoraFin))"+
													" AND idCita IN"+
													" (SELECT idCita FROM Cita WHERE dia = ? AND EstadoTurno = ?)";
	// Recibe idProfesional, todos los ? de la izquierda son hora que iniciaria el turno
	// el ultimo ? es hora que finalizaria
	// fecha del turno 
	// Estado = Activa siempre
	
	@Override
	public boolean insert(ServicioTurnoDTO servicioTurno_a_insertar) 
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt	(1, servicioTurno_a_insertar.getIdCita());
			statement.setInt	(2, servicioTurno_a_insertar.getIdServicio());
			statement.setInt	(3, servicioTurno_a_insertar.getIdProfesional());
			statement.setTime	(4, Time.valueOf(servicioTurno_a_insertar.getHoraInicio()));
			statement.setTime	(5, Time.valueOf(servicioTurno_a_insertar.getHoraFin()));
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
	public boolean delete(ServicioTurnoDTO servicioTurno_a_eliminar)
		{
			PreparedStatement statement;
			Connection conexion = Conexion.getConexion().getSQLConexion();
			boolean isdeleteExitoso = false;
			try
			{
				statement = conexion.prepareStatement(delete);
				statement.setInt	(1, servicioTurno_a_eliminar.getIdCita());
				statement.setInt	(2, servicioTurno_a_eliminar.getIdServicio());
				statement.setInt	(3, servicioTurno_a_eliminar.getIdProfesional());
				if(statement.executeUpdate() > 0)
				{
					conexion.commit();
					isdeleteExitoso = true;
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return isdeleteExitoso;
	}


	@Override
	public boolean update(ServicioTurnoDTO servicioTurno_a_Actualizar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ServicioTurnoDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ServicioTurnoDTO> servicios = new ArrayList<ServicioTurnoDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				servicios.add(getServicioTurnoDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return servicios;
	}
	
	@Override
	public List<Integer> getCitasByIdServicio(int idServicio) 
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Integer> citas = new ArrayList<Integer>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(getCitasByIdServicio);
			statement.setInt	(1, idServicio);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				citas.add(getServicioTurnoDTO(resultSet).getIdCita());
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println(citas);
		return citas;
	}
	@Override
	public int profesionalEnSucursal(Integer idProfesional, LocalTime horaInicio, 
		LocalTime horaFin, String diaDeLaSemana) {
		PreparedStatement statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Integer disponible = 0;
		try
		{
			statement = conexion.prepareStatement(profesionalTrabaja);
			System.out.println(horaInicio);
			System.out.println("----------------------------------ENTRE--------------------------------------");
			statement.setInt	(1, idProfesional);
			statement.setTime	(2, Time.valueOf(horaInicio));
			statement.setTime	(3, Time.valueOf(horaFin));
			statement.setString	(4, diaDeLaSemana);
			System.out.println(statement);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()){
				disponible = resultSet.getInt("ocupado");
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
		return disponible;
	}
	
	
	@Override
	public int profesionalOcupado(Integer idProfesional, LocalTime horaInicio, 
		LocalTime horaFin, LocalDate fecha) {
		PreparedStatement statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Integer ocupado = 0;
		try
		{
			statement = conexion.prepareStatement(profesionalOcupado);
			System.out.println(horaInicio);
			System.out.println("----------------------------------ENTRE--------------------------------------");
			statement.setInt	(1, idProfesional);
			statement.setTime	(2, Time.valueOf(horaInicio));
			statement.setTime	(3, Time.valueOf(horaFin));
			statement.setTime	(4, Time.valueOf(horaInicio));
			statement.setTime	(5, Time.valueOf(horaInicio));
			statement.setTime	(6, Time.valueOf(horaFin));
			statement.setDate	(7, Date.valueOf(fecha));
			statement.setString	(8, "Activa");
			System.out.println(statement);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()){
				ocupado = resultSet.getInt("ocupado");
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
		return ocupado;
	}
	
	
	private ServicioTurnoDTO getServicioTurnoDTO(ResultSet resultSet) throws SQLException
		{
			int idCita = resultSet.getInt("idCita");
			int idServicio = resultSet.getInt("idServicio");
			int idProfesional = resultSet.getInt("idprofesional");
			LocalTime horainicio = resultSet.getTime("horaInicio").toLocalTime();
			LocalTime horafin = resultSet.getTime("horaFin").toLocalTime();

			return new ServicioTurnoDTO(idCita, idServicio, idProfesional, horainicio, horafin);
		}
	
	@Override
	public List<ServicioTurnoDTO> getByIdCita(int idCita) 	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ServicioTurnoDTO> servicios = new ArrayList<ServicioTurnoDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(getByIdCita);
			statement.setInt	(1, idCita);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				servicios.add(getServicioTurnoDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return servicios;
	}

}
