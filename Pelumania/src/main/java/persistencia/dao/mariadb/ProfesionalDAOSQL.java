package persistencia.dao.mariadb;

	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dto.ProfesionalDTO;
import dto.ServicioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ProfesionalDAO;

	public class ProfesionalDAOSQL implements ProfesionalDAO{

	private static final String insert = "INSERT INTO Profesional(IdProfesional, nombre, apellido, idSucursalOrigen, idSucursalTransferencia, estado) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String delete = "UPDATE Profesional SET estado='Inactivo' WHERE IdProfesional = ?";
	private static final String deleteSanti = "DELETE FROM Profesional WHERE IdProfesional = ?";
	private static final String readall = "SELECT * FROM Profesional";
	private static final String readone = "SELECT * FROM Profesional WHERE IdProfesional = ?";
	private static final String update = "UPDATE Profesional SET nombre=? , apellido=? , idSucursalOrigen=? , idSucursalTransferencia=?, estado = ? WHERE IdProfesional = ?";
	private static final String getById = "SELECT * FROM Profesional WHERE idProfesional = ?";
	private static final String readBuscador = "SELECT * FROM Profesional WHERE ? LIKE ?";
	
	private static final String getProfesionalByHorario = "SELECT * FROM DiasLaborales WHERE ? > HoraEntrada AND ? < HoraSalida AND DIA = ?";
	
	private static final String serviciosDelProfesional = "SELECT * FROM ServicioProfesional WHERE idProfesional = ?";
	
	
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
			statement.setString(6, profesional.getEstado());
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
	
	public boolean deleteSanti(ProfesionalDTO profesional_a_eliminar)
	{
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(deleteSanti);
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
			statement.setString(5, profesional_a_editar.getEstado());
			statement.setInt(6, profesional_a_editar.getIdProfesional());
			
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
	
	public boolean insertServProf(int id_servicio, int id_profesional) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		
		try{
			statement = conexion.prepareStatement("INSERT INTO servicioprofesional (idServicio,idProfesional) VALUES (?,?) ");
			statement.setInt(1, id_servicio );
			statement.setInt(2, id_profesional);
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
	
	@Override
	public boolean deleteServProf(int idServ, int idPof) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean deleteExitoso = false;
		try {
			statement = conexion.prepareStatement("DELETE FROM servicioprofesional WHERE idServicio = ? AND idProfesional = ?");
			statement.setInt(1, idServ);
			statement.setInt(2, idPof);
			if(statement.executeUpdate() > 0){
				conexion.commit();
				deleteExitoso=true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return deleteExitoso;
	}

	public String obtenerNombreSucursal(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		String sucursal;
		try
		{
			statement = conexion.getSQLConexion().prepareStatement("Select NombreSucursal FROM sucursal WHERE idSucursal = ?");

			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()){
				sucursal = resultSet.getString("NombreSucursal");
				return sucursal;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> obtenerServEnTabla(int id){
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		List<String> servicio = new ArrayList<String>();
		try 
		{
			// que buena query 
			statement = conexion.getSQLConexion().prepareStatement("SELECT s.Nombre FROM servicioprofesional sp JOIN profesional p USING(idProfesional) JOIN servicio s USING (idServicio) WHERE p.IdProfesional=?");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while (resultSet.next()){
				servicio.add(resultSet.getString("s.Nombre"));
			}
			return servicio;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public List<ProfesionalDTO> obtenerProfBuscado(String variable, String value) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ProfesionalDTO> personas = new ArrayList<ProfesionalDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			if(variable.equals("Todos")) {
				statement = conexion.getSQLConexion().prepareStatement(readall);
			}else {
				statement = conexion.getSQLConexion().prepareStatement(readall+" WHERE "+variable+ " LIKE '"+value+"'");
			}
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
	
	public List<ProfesionalDTO> getProfesionalByHorario(LocalTime InicioTurno, String diaDeLaSemana){
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<ProfesionalDTO> profesionales = new ArrayList<ProfesionalDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(getProfesionalByHorario);
			statement.setTime(1, Time.valueOf(InicioTurno));
			statement.setTime(2, Time.valueOf(InicioTurno));
			statement.setString(3, diaDeLaSemana);
			resultSet = statement.executeQuery();
			while (resultSet.next()){
				profesionales.add(getProfesionalById(resultSet.getInt("idProfesional")));
			}
			return profesionales;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return profesionales;
	}

	public List<ServicioDTO> getServiciosDelProfesional (int id)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Connection conexion = Conexion.getConexion().getSQLConexion();
		List<Integer> IdServicios = new ArrayList<Integer>();

		try 
		{
			statement = conexion.prepareStatement(serviciosDelProfesional);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				IdServicios.add(resultSet.getInt("IdServicio"));
			}
	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
		for (Integer i : IdServicios) {
			servicios.add(ServicioDAOSQL.obtenerPorId(i));
		}
		
		return servicios;
	}
	
	public ProfesionalDTO getById(int idProfesional)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Connection conexion = Conexion.getConexion().getSQLConexion();
		List<ProfesionalDTO> profesionales = new ArrayList<ProfesionalDTO>();
		try 
		{
			statement = conexion.prepareStatement(getById);
			statement.setInt(1, idProfesional);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				profesionales.add(getProfesionalDTO(resultSet));
			}
	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return profesionales.get(0);
	}
	
	private ProfesionalDTO getProfesionalById(int idProfesional) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		Connection conexion = Conexion.getConexion().getSQLConexion();
		List<ProfesionalDTO> profesionales = new ArrayList<ProfesionalDTO>();
		try 
		{
			statement = conexion.prepareStatement(getById);
			statement.setInt(1, idProfesional);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				profesionales.add(getProfesionalDTO(resultSet));
			}
	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return profesionales.get(0);
	}
	
	private ProfesionalDTO getProfesionalDTO(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("idProfesional");
		String nombre = resultSet.getString("Nombre");
		String apellido = resultSet.getString("apellido");
		int idSucursalOrigen = resultSet.getInt("idSucursalOrigen");
		int idSucursalTransferencia =resultSet.getInt("idSucursalTransferencia");
		String estado=resultSet.getString("Estado");
		
		return new ProfesionalDTO(id, nombre, apellido ,idSucursalOrigen, idSucursalTransferencia, estado );
	}
	
}

