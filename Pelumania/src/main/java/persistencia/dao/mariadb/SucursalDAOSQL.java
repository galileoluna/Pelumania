package persistencia.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.SucursalDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.SucursalDAO;

public class SucursalDAOSQL implements SucursalDAO {
	
	private static final String insert = "INSERT INTO Sucursal( idSucursal, NombreSucursal, Direccion, Numero, EstadoSucursal) VALUES(?, ?, ?, ?, ?)";
	private static final String delete = "UPDATE Sucursal SET EstadoSucursal=? WHERE idSucursal = ?";
	private static final String readall = "SELECT * FROM Sucursal";
	private static final String readone = "SELECT * FROM Sucursal WHERE idSucursal= ? ";
	private static final String update = "UPDATE Sucursal SET NombreSucursal=? , Direccion=? , Numero=? , EstadoSucursal=?  WHERE idSucursal = ?";
	private static final String ESTADO_INACTIVO = "Activo";

@Override
public boolean insert(SucursalDTO sucursal){
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean isInsertExitoso = false;
	try
	{
		statement = conexion.prepareStatement(insert);
		statement.setInt	(1, sucursal.getIdSucursal());
		statement.setString (2, sucursal.getNombreSucursal());
		statement.setString (3, sucursal.getDireccion());
		statement.setString (4, sucursal.getEstadoSucursal());

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
public boolean delete(SucursalDTO sucursal_a_eliminar)
{
	PreparedStatement statement;
	Connection conexion = Conexion.getConexion().getSQLConexion();
	boolean isdeleteExitoso = false;
	try 
	{
		statement = conexion.prepareStatement(delete);
		statement.setString(1, ESTADO_INACTIVO);
		statement.setInt(2, sucursal_a_eliminar.getIdSucursal());
		
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
public List<SucursalDTO> readAll()
{
	PreparedStatement statement;
	ResultSet resultSet; //Guarda el resultado de la query
	ArrayList<SucursalDTO> sucursales = new ArrayList<SucursalDTO>();
	Conexion conexion = Conexion.getConexion();
	try 
	{
		statement = conexion.getSQLConexion().prepareStatement(readall);
		resultSet = statement.executeQuery();
		while(resultSet.next())
		{
			sucursales.add(getSucursalDTO(resultSet));
		}
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
	return sucursales;
}

private SucursalDTO getSucursalDTO(ResultSet resultSet) throws SQLException
{
	int id = resultSet.getInt("idSucursal");
	String nombre = resultSet.getString("NombreSucursal");
	String direccion = resultSet.getString("Direccion");
	int numero = resultSet.getInt("Numero");
	String estado= resultSet.getString("EstadoSucursal");
	
	return new SucursalDTO(id, nombre, direccion, numero, estado);
}

@Override
public boolean update(SucursalDTO sucursal_a_actualizar) {
	// TODO Auto-generated method stub
	return false;
	}

}