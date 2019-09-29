package persistencia.dao.mariadb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ClienteDAO;
import dto.ClienteDTO;

public class ClienteDAOSQL implements ClienteDAO
{
	
	private static final String insert = "INSERT INTO Cliente( idCliente, Nombre, Apellido, Telefono, Mail, Puntos, EstadoCliente, Deuda) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM Cliente WHERE idCliente = ?";
	private static final String readall = "SELECT * FROM Cliente";
		
	public boolean insert(ClienteDTO cliente)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt	(1, cliente.getIdCliente());
			statement.setString (2, cliente.getNombre());
			statement.setString (3, cliente.getApellido());
			statement.setString (4, cliente.getTelefono());
			statement.setString (5, cliente.getMail());
			statement.setInt    (6, cliente.getPuntos());
			statement.setString (7, cliente.getEstadoCliente());
			statement.setBigDecimal (8, cliente.getDeuda());
			
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
	
	public boolean delete(ClienteDTO cliente_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(cliente_a_eliminar.getIdCliente()));
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
	
	public List<ClienteDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				clientes.add(getClienteDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return clientes;
	}
	
	private ClienteDTO getClienteDTO(ResultSet resultSet) throws SQLException
	{
		int id = resultSet.getInt("idCliente");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("Apellido");
		String telefono = resultSet.getString("Telefono");
		String mail = resultSet.getString("Mail");
		int puntos = resultSet.getInt("Puntos");
		String estado = resultSet.getString("EstadoCliente");
		BigDecimal deuda = resultSet.getBigDecimal("Deuda");
		
		return new ClienteDTO(id, nombre, apellido, telefono, mail, puntos, estado, deuda);
	}
}
