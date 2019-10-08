package persistencia.dao.mariadb;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.ProfesionalDTO;
import dto.PromocionDTO;
import persistencia.dao.interfaz.PromocionDAO;

public class PromocionDAOSQL implements PromocionDAO{
	private static final String readAll="SELECT * FROM serviciopromocion";
	private static final String readOne="SELECT s.nombre, p.* FROM serviciopromocion JOIN servicio s USING (idServicio) JOIN promocion p USING (idPromocion) WHERE p.idPromocion=?";
	private static final String insert="INSERT INTO promocion set (Descripcion,FechaInicio,FechaFin,Descuento,Puntos) VALUES (?,?,?,?,?)";
	private static final String delete="DELETE FROM promocion WHERE idPromocion = ?";
	private static final String insertServProm="INSERT INTO servicioprofesional SET (idPromocion, idServicio) VALUES (?,?)";
	private static final String deleteServProm="DELETE FROM servicioprofesional WHERE idPromocion = ?, idServicio=?";

	@Override
	public boolean insert(PromocionDTO promocion) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(PromocionDTO promocion_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(PromocionDTO promocion_a_eliminar) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PromocionDTO> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromocionDTO> readOne(int id_promocion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertServProm(int id_promocion, int id_servicio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteServProm(int id_promocion, int id_servicio) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private PromocionDTO getPromocionlDTO(ResultSet resultSet) throws SQLException{
		int id = resultSet.getInt("idPromocion");
		String descripcion = resultSet.getString("Descripcion");
		Date fechaInc = resultSet.getDate("FechaInicio");
		Date fechaFin = resultSet.getDate("FechaFin");
		Double descuento =resultSet.getDouble("Descuento");
		int puntos=resultSet.getInt("Puntos");
		
		return new PromocionDTO(id, descripcion,fechaInc ,fechaFin, descuento, puntos );
	}


}
