package persistencia.dao.mariadb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import dto.HorarioDTO;
import dto.ProfesionalDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.HorarioDAO;

public class HorarioDAOSQL implements HorarioDAO {
	private static final String insert = "INSERT INTO DiasLaborales( idDiasLaborales, Dia, HoraEntrada, HoraSalida, IdProfesional) VALUES(?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM DiasLaborales WHERE idDiasLaborales = ?";
	private static final String readall = "SELECT * FROM DiasLaborales WHERE idDiasLaborales = ? ";
	private static final String readone = "SELECT * FROM DiasLaborales WHERE IdProfesional = ? ";
	private static final String update = "UPDATE DiasLaborales SET Dia=? , HoraEntrada=? , HoraSalida=?  WHERE idDiasLaborales = ?";
	

	public boolean insert(HorarioDTO horario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt	(1, horario.getidDiasLaborales());
			statement.setString (2, horario.getDia());
			statement.setTime (3, horario.getHoraEntrada());
			statement.setTime (4, horario.getHoraSalida());
			statement.setInt (5, horario.getIdProfesional());
			
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

	public boolean delete(HorarioDTO horario_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, horario_a_eliminar.getidDiasLaborales());
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


	public List<HorarioDTO> readAll(int id) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<HorarioDTO> horario = new ArrayList<HorarioDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				horario.add(getHorarioDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return horario;
	}

	public List<HorarioDTO> readOne(int id) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<HorarioDTO> horario = new ArrayList<HorarioDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readone);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				horario.add(getHorarioDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return horario;
	}

	@Override
	public boolean update(HorarioDTO horario_a_editar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			
			statement.setString(1, horario_a_editar.getDia());
			statement.setTime(2, horario_a_editar.getHoraEntrada());
			statement.setTime(3, horario_a_editar.getHoraSalida());
			statement.setInt(4, horario_a_editar.getidDiasLaborales());

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

	private HorarioDTO getHorarioDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("IdDiasLaborales");
		String dia = resultSet.getString("Dia");
		Time horaEntrada = resultSet.getTime("HoraEntrada");
		Time horaSalida = resultSet.getTime("HoraSalida");
		int idProfesional= resultSet.getInt("IdProfesional");
		
		return new HorarioDTO(id, dia, horaEntrada, horaSalida, idProfesional);
	}

	
}
