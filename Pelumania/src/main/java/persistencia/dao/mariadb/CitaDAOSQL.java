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
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.CitaDAO;

public class CitaDAOSQL implements CitaDAO{

	private static final String insert = "INSERT INTO Cita( idCita, idUsuario, idCliente,"
			+ "NombreCliente, ApellidoCliente, EstadoTurno,"
			+ "PrecioLocal, PrecioDolar, HoraInicio, HoraFin,  Dia, IdSucursal) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String insertSinRegistrar = "INSERT INTO Cita( idCita, idUsuario,"
			+ "NombreCliente, ApellidoCliente, EstadoTurno,"
			+ "PrecioLocal, PrecioDolar, HoraInicio, HoraFin,  Dia, IdSucursal) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String getCitaMax = "SELECT * FROM Cita WHERE IdCita IN (SELECT MAX(IdCita) FROM Cita);";

	private static final String getById = "SELECT * FROM Cita WHERE idCita = ?";
	private static final String delete = "UPDATE  Cita SET EstadoCita =? WHERE idCita = ?";
	private static final String deleteReal = "DELETE FROM Cita WHERE idCita = ?";
	private static final String cambioDeEstado = "UPDATE Cita SET EstadoTurno = ? WHERE idCita = ?";
	private static final String readall = "SELECT * FROM Cita";
	private static final String readTabla = "SELECT p.Nombre,p.Apellido,cl.Nombre,cl.Apellido,s.NombreSucursal,c.idCita, c.EstadoTurno, c.PrecioLocal,c.PrecioDolar, c.HoraInicio,c.Dia FROM cita c JOIN cliente cl USING (idCliente) JOIN sucursal s USING (idSucursal) JOIN profesional p USING (idProfesional)WHERE c.Dia=?";
	private static final String update = "UPDATE  Cita SET idUsuario=?, IdCliente=?, NombreCliente=?, ApellidoCliente=?, "
										+ "EstadoTurno=?, PrecioLocal=?, PrecioDolar=?, HoraInicio=?, HoraFin=?, Dia=?, IdSucursal=?  WHERE idCita=?";
	
	private static final String readByDia = "SELECT * FROM Cita WHERE Dia = ? ORDER BY HoraInicio";
	private static final String getCitasByHoraInicio = "SELECT * FROM CITA WHERE HoraInicio > ? AND HoraInicio < ? AND Dia= ?";
	
	private static final String DADODEBAJA = "Cerrado";
	private static final String CANCELADA = "Cancelada";
	private static final String FINALIZADA = "Finalizada"; 
	private static final String REPROGRAMAR = "Reprogramar";
	
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
				statement.setBigDecimal (7, cita.getPrecioLocal());
				statement.setBigDecimal (8, cita.getPrecioDolar());
				statement.setTime       (9, Time.valueOf(cita.getHoraInicio()));
				statement.setTime       (10, Time.valueOf(cita.getHoraFin()));
				statement.setDate       (11, Date.valueOf(cita.getFecha()));
				statement.setInt        (12, cita.getIdSucursal());

				if(statement.executeUpdate() > 0)
				{
//					conexion.commit();
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
				statement.setBigDecimal (6, cita.getPrecioLocal());
				statement.setBigDecimal (7, cita.getPrecioDolar());
				statement.setTime       (8, Time.valueOf(cita.getHoraInicio()));
				statement.setTime       (9, Time.valueOf(cita.getHoraFin()));
				statement.setDate       (10, Date.valueOf(cita.getFecha()));
				statement.setInt        (11, cita.getIdSucursal());

				if(statement.executeUpdate() > 0)
				{
//					conexion.commit();
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
		boolean isCancelarExitoso = false;
		try
		{
			statement = conexion.prepareStatement(cambioDeEstado);
			statement.setString (1, CANCELADA);
			statement.setInt	(2, cita_a_cancelar.getIdCita());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isCancelarExitoso = true;
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
		return isCancelarExitoso;
	}
	
	
	public boolean finalizar(CitaDTO cita_a_finalizar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isFinalizarExitoso = false;
		try
		{
			statement = conexion.prepareStatement(cambioDeEstado);
			statement.setString (1, FINALIZADA);
			statement.setInt	(2, cita_a_finalizar.getIdCita());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isFinalizarExitoso = true;
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
		return isFinalizarExitoso;
	}
	
	
	public boolean reprogramar(CitaDTO cita_a_reprogramar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isReprogramarExitoso = false;
		try
		{
			statement = conexion.prepareStatement(cambioDeEstado);
			statement.setString (1, REPROGRAMAR);
			statement.setInt	(2, cita_a_reprogramar.getIdCita());
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isReprogramarExitoso = true;
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
		return isReprogramarExitoso;
	}
	
	@Override
	public boolean update(CitaDTO cita_a_editar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		System.out.println("cita a editar: "+cita_a_editar.getIdCliente());
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setInt 		(1, cita_a_editar.getIdUsuario());
			statement.setInt		(2, cita_a_editar.getIdCliente());
			statement.setString		(3, cita_a_editar.getNombre());
			statement.setString		(4, cita_a_editar.getApellido());
			statement.setString		(5, cita_a_editar.getEstado());
			statement.setBigDecimal	(6, cita_a_editar.getPrecioLocal());
			statement.setBigDecimal	(7, cita_a_editar.getPrecioDolar());
			statement.setTime		(8, Time.valueOf(cita_a_editar.getHoraInicio()));
			statement.setTime		(9, Time.valueOf(cita_a_editar.getHoraFin()));
			statement.setDate		(10, Date.valueOf(cita_a_editar.getFecha()));
			statement.setInt		(11, cita_a_editar.getIdSucursal());
			statement.setInt		(12, cita_a_editar.getIdCita());
			
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
				citas.add(getCitaDTOMati(resultSet));
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

	@Override
	public CitaDTO getCitaMax() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<CitaDTO> citas = new ArrayList<CitaDTO>();
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(getCitaMax);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				citas.add(getCitaDTOMati(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return citas.get(0);
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
	 
	public CitaDTO getById(int idCita) {
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			Connection conexion = Conexion.getConexion().getSQLConexion();
			List<CitaDTO> citas = new ArrayList<CitaDTO>();
			try 
			{
				statement = conexion.prepareStatement(getById);
				statement.setInt(1, idCita);
				resultSet = statement.executeQuery();
				
				while(resultSet.next())
				{
					citas.add(getCitaDTOMati(resultSet));
				}
		
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			return citas.get(0);
		}
		
	// Este get Cita recibe los parametros necesarios para armar CitaDTO para la visualizacion del turno por tabla
	private CitaDTO getCitaDTO(ResultSet resultSet) throws SQLException{
		int idCita = resultSet.getInt("c.idCita");
		String profesional = resultSet.getString("p.Nombre")+" "+resultSet.getString("p.Apellido");
		String cliente= resultSet.getString("cl.Nombre")+" "+resultSet.getString("cl.Apellido");
		Time hora=resultSet.getTime("HoraInicio");
		Date dia=resultSet.getDate("Dia");
		String sucursal=resultSet.getString("s.NombreSucursal");
		String estado=resultSet.getString("c.EstadoTurno");
		return new CitaDTO(idCita,profesional,cliente,hora,dia,sucursal,estado);
	}
	
	public List<CitaDTO> getCitasPorDia (String dia_a_buscar) {
		//Los strings a buscar son de la forma 20191015 (Para el 15 de Octubre de 2019)
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<CitaDTO> citasPorDia = new ArrayList<CitaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readByDia);
			statement.setString(1, dia_a_buscar);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				citasPorDia.add(getCitaDTOMati(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return citasPorDia;
	}
	 
	
	private CitaDTO getCitaDTOMati(ResultSet resultSet) throws SQLException{
		int idCita = resultSet.getInt("idCita");
		int idUsuario = resultSet.getInt("idUsuario");
		int idCliente = resultSet.getInt("IdCliente");
		String nombre = resultSet.getString("NombreCliente");
		String apellido = resultSet.getString("ApellidoCliente");
		String estado = resultSet.getString("EstadoTurno");
		BigDecimal precioLocal = resultSet.getBigDecimal("PrecioLocal");
		BigDecimal precioDolar = resultSet.getBigDecimal("PrecioDolar");
		LocalTime horaInicio = resultSet.getTime("HoraInicio").toLocalTime();
		LocalTime horaFin = resultSet.getTime("HoraFin").toLocalTime();
		LocalDate fechaCita = resultSet.getDate("Dia").toLocalDate().plusDays(1);
		int idSucursal = resultSet.getInt("IdSucursal");
		
		return new CitaDTO(idCita, idUsuario, idCliente, nombre, apellido, estado, precioLocal,
				precioDolar, horaInicio, horaFin, fechaCita, idSucursal);
	}

	@Override
	public List<CitaDTO> readAllActivas() {
		List<CitaDTO> citasActivas = new ArrayList<CitaDTO>();
		for (CitaDTO cita : this.readAll()) {
			if (cita.esActiva()) {
				citasActivas.add(cita);
			}
		}
		return citasActivas;
	}
	
	public List<CitaDTO> getCitasByRangoHorario(LocalTime desde, LocalTime hasta, LocalDate dia) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<CitaDTO> citasPorHora = new ArrayList<CitaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getCitasByHoraInicio);
			statement.setTime(1, Time.valueOf(desde));
			statement.setTime(2, Time.valueOf(hasta));
			statement.setDate(3, Date.valueOf(dia));
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				citasPorHora.add(getCitaDTOMati(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return citasPorHora;
	}
}
