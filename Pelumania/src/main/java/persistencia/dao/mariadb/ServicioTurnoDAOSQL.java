package persistencia.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ServicioTurnoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ServicioTurnoDAO;

public class ServicioTurnoDAOSQL implements ServicioTurnoDAO {
	
	private static final String insert = "INSERT INTO ServicioTurno (idCita, idServicio, idProfesional) VALUES(?, ?, ?)";
	private static final String delete = "DELETE FROM ServicioTurno WHERE idCita = ? AND idServicio = ? AND idProfesional = ?";
	private static final String update = "";
	private static final String readAll = "";
	private static final String getByIdCita = "SELECT * FROM ServicioTurno WHERE idCita = ?";

	@Override
	public boolean insert(ServicioTurnoDTO servicioTurno_a_insertar) 
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			System.out.println(servicioTurno_a_insertar.getIdCita());
			System.out.println(servicioTurno_a_insertar.getIdServicio());
			System.out.println(servicioTurno_a_insertar.getIdProfesional());
			statement = conexion.prepareStatement(insert);
			statement.setInt	(1, servicioTurno_a_insertar.getIdCita());
			statement.setInt	(2, servicioTurno_a_insertar.getIdServicio());
			statement.setInt	(3, servicioTurno_a_insertar.getIdProfesional());
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
	
	private ServicioTurnoDTO getServicioTurnoDTO(ResultSet resultSet) throws SQLException
		{
			int idCita = resultSet.getInt("idCita");
			int idServicio = resultSet.getInt("idServicio");
			int idProfesional = resultSet.getInt("idprofesional");

			return new ServicioTurnoDTO(idCita, idServicio, idProfesional);
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
