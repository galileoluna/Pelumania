package persistencia.dao.mariadb;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

	import dto.ProfesionalDTO;
	import persistencia.conexion.Conexion;
	import persistencia.dao.interfaz.ProfesionalDAO;

	public class ProfesionalDAOSQL implements ProfesionalDAO{

	private static final String insert = "INSERT INTO Profesional(IdProfesional, nombre, apellido, idSucursalOrigen, idSucursalTransferencia) VALUES(?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM Profesional WHERE IdProfesional = ?";
	private static final String readall = "SELECT * FROM Profesional";
	private static final String readone = "SELECT * FROM Profesional WHERE IdProfesional = ?";
	private static final String update = "UPDATE Profesional SET nombre=? , apellido=? , idSucursalOrigen=? , idSucursalTransferencia=? WHERE IdProfesional = ?";
			
	public boolean insert(ProfesionalDTO profesional){
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		
		try{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, profesional.getIdProfesional());
			statement.setString(2, profesional.getNombre());
			statement.setString(3, profesional.getApellido());
			statement.setInt(4,profesional.getIdSucursalOrigen());
			statement.setInt(5,profesional.getIdSucursalTransferencia());
			if(statement.executeUpdate() > 0){
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}			
		return isInsertExitoso;
	}

	public boolean delete(ProfesionalDTO profesional_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, profesional_a_eliminar.getIdProfesional());
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
		
	public List<ProfesionalDTO> readAll(){	
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ProfesionalDTO> personas = new ArrayList<ProfesionalDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				personas.add(getProfesionalDTO(resultSet));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}
	
	public List<ProfesionalDTO> readOne(int profesional_a_editar)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<ProfesionalDTO> profesional = new ArrayList<ProfesionalDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readone);
			statement.setInt(1, profesional_a_editar);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				profesional.add(getProfesionalDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return profesional;
	}
	
	public boolean update (ProfesionalDTO profesional_a_editar) {
		
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			
			statement.setString(1, profesional_a_editar.getNombre());
			statement.setString(2, profesional_a_editar.getApellido());
			statement.setInt(3, profesional_a_editar.getIdSucursalOrigen());
			statement.setInt(4, profesional_a_editar.getIdSucursalTransferencia());
			statement.setInt(5, profesional_a_editar.getIdProfesional());
			System.out.println(profesional_a_editar.getApellido()+"-"+profesional_a_editar.getNombre()+"-"+profesional_a_editar.getIdSucursalOrigen()+"-"+profesional_a_editar.getIdSucursalTransferencia()+"-"+profesional_a_editar.getIdProfesional()+"-"+(chequeoUpdate > 0));
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
	
	private ProfesionalDTO getProfesionalDTO(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("idProfesional");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("apellido");
		int idSucursalOrigen = resultSet.getInt("idSucursalOrigen");
		int idSucursalTransferencia =resultSet.getInt("idSucursalTransferencia");
		
		return new ProfesionalDTO(id, nombre, apellido ,idSucursalOrigen, idSucursalTransferencia );
	}

}

