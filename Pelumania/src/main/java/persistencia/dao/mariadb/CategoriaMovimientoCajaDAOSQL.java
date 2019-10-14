package persistencia.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dto.CategoriaMovimientoCajaDTO;
import dto.SucursalDTO;
import persistencia.conexion.Conexion; 
import persistencia.dao.interfaz.CategoriaMovimientoCajaDAO;

public class CategoriaMovimientoCajaDAOSQL implements CategoriaMovimientoCajaDAO {
	
	private static final String insert = "INSERT INTO CategoriaCaja( idCategoriaCaja, Nombre, Estado) VALUES(?, ?, ?)";
	private static final String delete = "UPDATE CategoriaCaja SET Estado=? WHERE idCategoriaCaja = ?";
	private static final String deleteRealCategoria ="delete from CategoriaCaja where idCategoriaCaja=?";
	private static final String readall = "SELECT * FROM CategoriaCaja";
	private static final String update = "UPDATE CategoriaCaja SET Nombre=?, Estado=? WHERE idCategoriaCaja = ?";
	private static final String ESTADO_INACTIVO = "inactivo";
	private static final String readOne = "SELECT * FROM CategoriaCaja WHERE idCategoriaCaja = ?";
	private static final String readOneByName = "SELECT * FROM CategoriaCaja WHERE Nombre = ?";
    
    @Override
    public boolean insert(CategoriaMovimientoCajaDTO categoria){
    	PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	boolean isInsertExitoso = false;
    	try
    	{
    		statement = conexion.prepareStatement(insert);
    		statement.setInt	(1, categoria.getIdCategoria());
    		statement.setString (2, categoria.getNombre());
    		statement.setString (3, categoria.getEstado());

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
	public boolean update(CategoriaMovimientoCajaDTO categoria_a_actualizar) {
	    	PreparedStatement statement;
	    	int chequeoUpdate = 0;
	    	Conexion conexion = Conexion.getConexion();
	    	try 
	    	{
	    		statement = conexion.getSQLConexion().prepareStatement(update);
	    		
	    		statement.setString(1, categoria_a_actualizar.getNombre());
	    		statement.setString (2, categoria_a_actualizar.getEstado());
	    		statement.setInt(3, categoria_a_actualizar.getIdCategoria());

	    		
	    		chequeoUpdate = statement.executeUpdate();
	    		conexion.getSQLConexion().commit();
	    		if(chequeoUpdate > 0)
	    				return true;
	    	} 
	    	catch (SQLException e) 
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    	return false;
	}

	@Override
	public boolean delete(CategoriaMovimientoCajaDTO categoria_a_eliminar) {	
    	PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	boolean isdeleteExitoso = false;
    	try 
    	{
    		statement = conexion.prepareStatement(delete);
    		statement.setString(1, ESTADO_INACTIVO);
    		statement.setInt(2, categoria_a_eliminar.getIdCategoria());
    		
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
	public boolean deleteRealCategoria(CategoriaMovimientoCajaDTO categoria_a_eliminar) {
		PreparedStatement statement;
    	Connection conexion = Conexion.getConexion().getSQLConexion();
    	boolean isdeleteExitoso = false;
    	try 
    	{
    		statement = conexion.prepareStatement(deleteRealCategoria);
    		statement.setInt(1, categoria_a_eliminar.getIdCategoria());
    		
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
	public List<CategoriaMovimientoCajaDTO> readAll() {
	    	PreparedStatement statement;
	    	ResultSet resultSet; //Guarda el resultado de la query
	    	ArrayList<CategoriaMovimientoCajaDTO> categorias = new ArrayList<CategoriaMovimientoCajaDTO>();
	    	Conexion conexion = Conexion.getConexion();
	    	try 
	    	{
	    		statement = conexion.getSQLConexion().prepareStatement(readall);
	    		resultSet = statement.executeQuery();
	    		while(resultSet.next())
	    		{
	    			categorias.add(getCategoriaMovimientoCajaDTO(resultSet));
	    		}
	    	} 
	    	catch (SQLException e) 
	    	{
	    		e.printStackTrace();
	    	}
	    	return categorias;
	}

	private CategoriaMovimientoCajaDTO getCategoriaMovimientoCajaDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idCategoriaCaja");
    	String nombre = resultSet.getString("Nombre");
    	String estado = resultSet.getString("Estado");
    	
    	return new CategoriaMovimientoCajaDTO(id, nombre, estado);
    }
	
	public CategoriaMovimientoCajaDTO readOne(int idCategoriaCaja) {
		PreparedStatement statement;
    	ResultSet resultSet; //Guarda el resultado de la query
    	List<CategoriaMovimientoCajaDTO> categoria = new ArrayList<CategoriaMovimientoCajaDTO>();
    	Conexion conexion = Conexion.getConexion();
    	try 
    	{
    		statement = conexion.getSQLConexion().prepareStatement(readOne);
    		statement.setInt(1, idCategoriaCaja);
    		resultSet = statement.executeQuery();
    		while(resultSet.next())
    		{
    			categoria.add(getCategoriaMovimientoCajaDTO(resultSet));
    		}
    	} 
    	catch (SQLException e) 
    	{
    		e.printStackTrace();
    	}
    	
    	return categoria.get(0);
	}

	public CategoriaMovimientoCajaDTO readOneByName(String name ) {
		PreparedStatement statement;
    	ResultSet resultSet; //Guarda el resultado de la query
    	List<CategoriaMovimientoCajaDTO> categoria = new ArrayList<CategoriaMovimientoCajaDTO>();
    	Conexion conexion = Conexion.getConexion();
    	try 
    	{
    		statement = conexion.getSQLConexion().prepareStatement(readOneByName);
    		statement.setString(1, name);
    		resultSet = statement.executeQuery();
    		while(resultSet.next())
    		{
    			categoria.add(getCategoriaMovimientoCajaDTO(resultSet));
    		}
    	} 
    	catch (SQLException e) 
    	{
    		e.printStackTrace();
    	}
    	
    	return categoria.get(0);
	}
	
}