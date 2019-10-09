package persistencia.dao.mariadb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProfesionalDTO;
import dto.PromocionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PromocionDAO;

public class PromocionDAOSQL implements PromocionDAO{
	private static final String readAll="SELECT * FROM promocion";
	private static final String readOne="SELECT s.nombre, p.* FROM serviciopromocion JOIN servicio s USING (idServicio) JOIN promocion p USING (idPromocion) WHERE p.idPromocion=?";
	private static final String insert="INSERT INTO promocion (Descripcion,FechaInicio,FechaFin,Descuento,Puntos) VALUES (?,?,?,?,?)";
	private static final String deleteReal="DELETE FROM promocion WHERE idPromocion = ?";
	private static final String delete="UPADATE promocion set estado='Inactivo' WHERE idPromocion=?";
	private static final String insertServProm="INSERT INTO servicioprofesional (idPromocion, idServicio) VALUES (?,?)";
	private static final String deleteServProm="DELETE FROM servicioprofesional WHERE idPromocion = ?, idServicio=?";
	private static final String update="UPADATE promocion set Descripcion=?,FechaInicio=?,FechaFin=?,Descuento=?,Promocion=? WHERE idPromocion=?";

	@Override
	public boolean insert(PromocionDTO promocion) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		
		try{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, promocion.getDescripcion());
			statement.setDate(2, promocion.getFechaInicio());
			statement.setDate(3, promocion.getFechaFin());
			statement.setDouble(4,promocion.getDescuento());
			statement.setInt(5,promocion.getPuntos());
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
	public boolean delete(PromocionDTO promocion_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, promocion_a_eliminar.getIdPromocion());
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
	public boolean update(PromocionDTO promocion_a_editar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			
			statement.setString(1, promocion_a_editar.getDescripcion());
			statement.setDate(2, promocion_a_editar.getFechaInicio());
			statement.setDate(3, promocion_a_editar.getFechaFin());
			statement.setDouble(4, promocion_a_editar.getDescuento());
			statement.setInt(5, promocion_a_editar.getPuntos());
			statement.setInt(6, promocion_a_editar.getIdPromocion());
			
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
	public List<PromocionDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PromocionDTO> promo = new ArrayList<PromocionDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				promo.add(getPromocionlDTO(resultSet));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return promo;
	}

	@Override
	public List<PromocionDTO> readOne(int id_promocion) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		List<PromocionDTO> promo = new ArrayList<PromocionDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readOne);
			statement.setInt(1, id_promocion);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				promo.add(getPromocionlDTO(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return promo;
	}

	@Override
	public boolean insertServProm(int id_promocion, int id_servicio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		
		try{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, id_promocion);
			statement.setInt(2, id_servicio);
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
	public boolean deleteServProm(int id_promocion, int id_servicio) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setInt(1,id_promocion);
			statement.setInt(2, id_servicio);
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
	
	private PromocionDTO getPromocionlDTO(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("idPromocion");
		String descripcion = resultSet.getString("Descripcion");
		Date fechaInc = resultSet.getDate("FechaInicio");
		Date fechaFin = resultSet.getDate("FechaFin");
		Double descuento =resultSet.getDouble("Descuento");
		int puntos=resultSet.getInt("Puntos");
		String estado=resultSet.getString("Estado");
		return new PromocionDTO(id, descripcion,fechaInc ,fechaFin, descuento, puntos,estado );
	}


}
