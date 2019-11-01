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
	
	private static final String profesionalOcupado = "SELECT 1 as ocupado" + 
			" FROM servicioturno st" +  
			" JOIN profesional p USING (IdProfesional)" + 
			" JOIN cita c USING (idCita)" + 
			" JOIN diaslaborales d ON  p.IdProfesional=d.IdProfesional" + 
			" WHERE (st.horaInicio <= ? OR st.horaFin >= ?)  AND st.horaInicio <= ? AND   st.horaFin >= ? AND d.HoraEntrada < ?" + 
			" AND d.HoraSalida > ? AND d.Dia = ? AND p.IdProfesional = ? AND c.Dia=?; ";

	
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
	public int profesionalOcupado(LocalTime horaInicio, LocalTime horaFin,
			String dia, Integer idProfesional, LocalDate fecha) {
		PreparedStatement statement;
		ResultSet resultSet;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		Integer ocupado = 0;
		try
		{
			statement = conexion.prepareStatement(profesionalOcupado);
			System.out.println(horaInicio);
			System.out.println("----------------------------------ENTRE--------------------------------------");
			statement.setTime	(1,  Time.valueOf(horaInicio));
			statement.setTime	(2,  Time.valueOf(horaFin));
			statement.setTime	(3,  Time.valueOf(horaInicio));
			statement.setTime	(4,  Time.valueOf(horaFin));
			statement.setTime	(5,  Time.valueOf(horaInicio));
			statement.setTime	(6,  Time.valueOf(horaFin));
			statement.setString	(7,  dia);
			statement.setInt	(8,  idProfesional);
			statement.setDate	(9,  Date.valueOf(fecha));
			
			System.out.println(statement);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()){
				//System.out.println("NICO MIRA: "+resultSet.getString("ocupado"));
				ocupado = resultSet.getInt("ocupado");
			}
			/*if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				System.out.println("NICO MIRA: "+resultSet.getString("ocupado"));
				ocupado = resultSet.getInt("ocupado");
			}*/
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
