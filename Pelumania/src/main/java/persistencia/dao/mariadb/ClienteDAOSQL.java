package persistencia.dao.mariadb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ClienteDAO;
import util.Validador;

public class ClienteDAOSQL implements ClienteDAO
{

	private static final String insert = "INSERT INTO Cliente( idCliente, Nombre, Apellido, Telefono, Mail, Puntos, EstadoCliente, Deuda) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "UPDATE  Cliente SET EstadoCliente=? WHERE idCliente = ?";
	private static final String readall = "SELECT * FROM Cliente";
	private static final String update = "UPDATE  Cliente SET Nombre=? , Apellido=? , Telefono=? , Mail=? , Puntos=? , EstadoCliente=?, Deuda=? WHERE idCliente=?";
	private static final String deleteReal = "DELETE FROM Cliente WHERE idCliente = ?";
	private static final String ESTADO_INACTIVO = "inactivo";
	private static final String readById = "SELECT * FROM Cliente WHERE idCliente = ?";

	@Override
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

	@Override
	public boolean delete(ClienteDTO cliente_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		if (esClienteValido(cliente_a_eliminar)) {
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, ESTADO_INACTIVO);
			statement.setString(2, Integer.toString(cliente_a_eliminar.getIdCliente()));
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
	 }
		return isdeleteExitoso;
	}

	@Override
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

	@Override
	public boolean update(ClienteDTO cliente_a_editar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		if (esClienteValido(cliente_a_editar)) {
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(update);

			statement.setString (1, cliente_a_editar.getNombre());
			statement.setString (2, cliente_a_editar.getApellido());
			statement.setString (3, cliente_a_editar.getTelefono());
			statement.setString (4, cliente_a_editar.getMail());
			statement.setInt    (5, cliente_a_editar.getPuntos());
			statement.setString (6, cliente_a_editar.getEstadoCliente());
			statement.setBigDecimal (7, cliente_a_editar.getDeuda());
			statement.setInt	(8, cliente_a_editar.getIdCliente());

			chequeoUpdate = statement.executeUpdate();
			conexion.getSQLConexion().commit();

			if(chequeoUpdate > 0) {
				return true;
			}
		}
		catch (SQLException e)
		{
			System.out.println("false");
			e.printStackTrace();
		}
	 }
		return false;
	}
	
	@Override
	public List<ClienteDTO> obtenerClienteBuscado(String variable, String value) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ClienteDTO> cliente = new ArrayList<ClienteDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			if(variable.equals("Todos")) {
				statement = conexion.getSQLConexion().prepareStatement(readall);
			}else {
				statement = conexion.getSQLConexion().prepareStatement(readall+" WHERE "+variable+ " LIKE '"+value+"'");
			}
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				cliente.add(getClienteDTO(resultSet));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	public boolean deleteReal(ClienteDTO cliente_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try
		{
			statement = conexion.prepareStatement(deleteReal);
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
	
	private boolean esClienteValido(ClienteDTO cliente){
		if (Validador.esNombreConEspaciosValido(cliente.getNombre()) 
			&& Validador.esNombreConEspaciosValido(cliente.getApellido())
			&& Validador.esMail(cliente.getMail()) 
			&& Validador.esEstadoClienteValido(cliente.getEstadoCliente()) 
			&& Validador.esPrecioValido(String.valueOf(cliente.getDeuda()))) 
		{
			return true;
		
		} else {
			
		return false;
	  } 
	}

	@Override
	public ClienteDTO obtenerClienteById(int id)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ClienteDTO cliente = null;
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				cliente= getClienteDTO(resultSet);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return cliente;
	}
	

}
