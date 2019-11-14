package persistencia.dao.mariadb;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dto.CitaDTO;
import dto.MailDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.MailDAO;

public class MailDAOSQL implements MailDAO {
	
	private static final String insert = "INSERT INTO Mail ( idMail, IdCita , Fecha ) VALUES ( ?, ?, ? )";
	private static final String update = "UPDATE Mail SET IdCita=?, Fecha=?, WHERE idMail=?";
	private static final String readall = "SELECT * FROM Mail";
	private static final String readAllOneDay = "SELECT * FROM Mail WHERE Fecha =  ?";
	

	@Override
	public boolean insert(MailDTO mail) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, mail.getIdMail());
			statement.setInt(2, mail.getIdCita());
			statement.setDate(3, Date.valueOf(mail.getFecha()));
			
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
	public boolean delete(MailDTO horario_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MailDTO> readAll(int id) {
			
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			ArrayList<MailDTO> Productos = new ArrayList<MailDTO>();
			Conexion conexion = Conexion.getConexion();
			try {
				statement = conexion.getSQLConexion().prepareStatement(readall);
				resultSet = statement.executeQuery();
				while(resultSet.next()){
					Productos.add(getMailDTO(resultSet));
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			return Productos;
	}
	
	
	private MailDTO getMailDTO(ResultSet resultSet) throws SQLException {
				
			int idMail = resultSet.getInt("idMail");
			int idCita  = resultSet.getInt("IdCita");
			LocalDate fecha = resultSet.getDate("Fecha").toLocalDate();
		
			return new MailDTO(idMail, idCita, fecha);
		}
	
	public List<MailDTO> readAllOneDay (LocalDate dia) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<MailDTO> mailPorDia= new ArrayList<MailDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAllOneDay);
			statement.setDate(1, Date.valueOf(dia));
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				mailPorDia.add(getMailDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return mailPorDia;
	}
	
	@Override
	public List<MailDTO> readOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
