package persistencia.dao.mariadb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import dto.UsuarioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.UsuarioDAO;

public class UsuarioDAOSQL implements UsuarioDAO{
	private static final String readOne="SELECT * from usuario where nombreUsuario=? AND Contrasenia=?";
	
	
	
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
	

}
