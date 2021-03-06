package persistencia.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dto.UsuarioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.UsuarioDAO;

public class UsuarioDAOSQL implements UsuarioDAO{
	private static final String insert = "INSERT INTO Usuario(Nombre,Apellido,nombreUsuario,Contrasenia,Mail,EstadoUsuario,idRol,idSucursal ) VALUES( ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String readOne = "SELECT * from Usuario where nombreUsuario=? AND Contrasenia=?";
	private static final String readAll = "SELECT * FROM Usuario WHERE idSucursal=?";
	private static final String readAll2 = "SELECT * FROM Usuario";
	private static final String readOneById = "SELECT * FROM Usuario WHERE idUsuario = ? ";
	private static final String update = "UPDATE Usuario SET  Nombre=?, Apellido=? , nombreUsuario=?, Contrasenia=?, Mail=?, EstadoUsuario=?, idRol=?, idSucursal=? WHERE idUsuario=?";
	private static final String delete = "UPDATE Usuario SET EstadoUsuario='Inactivo' WHERE idUsuario = ?";
	private static final String readRol = "SELECT * FROM Rol";
	private static final String readByUsername = "SELECT * from Usuario where nombreUsuario=?";
	private static final String updatePass="UPDATE Usuario set Contrasenia=? WHERE idUsuario=?";

	
	   @Override
	    public boolean insert(UsuarioDTO user){
	    	PreparedStatement statement;
	    	Connection conexion = Conexion.getConexion().getSQLConexion();
	    	boolean isInsertExitoso = false;
	    	try
	    	{
	    		statement = conexion.prepareStatement(insert);

	    		statement.setString (1, user.getNombre());
	    		statement.setString (2, user.getApellido());
	    		statement.setString (3, 	user.getNombreUsuario());
	    		statement.setString (4, 	user.getContrasenia());
	    		statement.setString(5,		user.getMail());
	    		statement.setString(6,		user.getEstado());
	    		statement.setInt(7,		user.getIdRol());
	    		statement.setInt(8,		user.getIdSucursal());
	    
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
	public List<UsuarioDTO> readOne(String user, String pass) {
		List<UsuarioDTO> usuario=new ArrayList<UsuarioDTO>();
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Conexion conexion = Conexion.getConexion();
		try
		{
			statement = conexion.getSQLConexion().prepareStatement(readOne);
			statement.setString(1, user);
			statement.setString(2, pass);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				usuario.add(getUsuarioDTO(resultSet));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return usuario;
	}



	private UsuarioDTO getUsuarioDTO(ResultSet resultSet) throws SQLException{
		int id=resultSet.getInt("idUsuario");
		String nombre=resultSet.getString("Nombre");
		String apellido=resultSet.getString("Apellido");
		String user=resultSet.getString("nombreUsuario");
		String pass=resultSet.getString("Contrasenia");
		String mail=resultSet.getString("Mail");
		String estado=resultSet.getString("EstadoUsuario");
		int rol=resultSet.getInt("idRol");
		int sucursal=resultSet.getInt("idSucursal");
		
				
		return new UsuarioDTO (id,nombre,apellido,user,pass,mail,estado,rol,sucursal);
	}



	@Override
	public List<UsuarioDTO> readAll(int sucu) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<UsuarioDTO> usuario = new ArrayList<UsuarioDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			statement.setInt(1, sucu);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				usuario.add(getUsuarioDTO(resultSet));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	@Override
	public List<UsuarioDTO> readAll2() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<UsuarioDTO> usuario = new ArrayList<UsuarioDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll2);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				usuario.add(getUsuarioDTO(resultSet));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	@Override
	public HashMap<String,Integer> readRol() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		HashMap<String,Integer> rol = new HashMap<String,Integer>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readRol);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				rol.put(resultSet.getString("Cargo"),resultSet.getInt("idRol"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return rol;
	}


	@Override
	public List<UsuarioDTO> readOneById(int id) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<UsuarioDTO> usuario = new ArrayList<UsuarioDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readOneById);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				usuario.add(getUsuarioDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return usuario;
	}
	



	@Override
	public boolean delete(UsuarioDTO usuario_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, usuario_a_eliminar.getIdUsuario());
			if(statement.executeUpdate() > 0){
				conexion.commit();
				isdeleteExitoso = true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}



	@Override
	public boolean update(UsuarioDTO usuario_a_modificar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			
			statement.setString(1, usuario_a_modificar.getNombre());
			statement.setString(2, usuario_a_modificar.getApellido());
			statement.setString(3, usuario_a_modificar.getNombreUsuario());
			statement.setString(4, usuario_a_modificar.getContrasenia());
			statement.setString(5, usuario_a_modificar.getMail());
			statement.setString(6, usuario_a_modificar.getEstado());
			statement.setInt(7, usuario_a_modificar.getIdRol());
			statement.setInt(8, usuario_a_modificar.getIdSucursal());
			statement.setInt(9, usuario_a_modificar.getIdUsuario());
			
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

	@Override
	public UsuarioDTO readByUsername(String user) {
			UsuarioDTO usuario = null;
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			Conexion conexion = Conexion.getConexion();
			try
			{
				statement = conexion.getSQLConexion().prepareStatement(readByUsername);
				statement.setString(1, user);
				resultSet = statement.executeQuery();
				while(resultSet.next())
				{
					usuario = getUsuarioDTO(resultSet);
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return usuario;
		}
	
	@Override
	public boolean updatePass(int id, String pass) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(updatePass);
			
			statement.setString(1, pass);
			statement.setInt(2, id);		
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
	
}
