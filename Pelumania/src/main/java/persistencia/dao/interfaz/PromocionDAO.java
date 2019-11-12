package persistencia.dao.interfaz;

import java.sql.Date;
import java.util.List;

import dto.PromocionDTO;


public interface PromocionDAO {

	public boolean insert(PromocionDTO promocion);

	public boolean delete(PromocionDTO promocion_a_eliminar);

	public boolean update(PromocionDTO promocion_a_editar);

	public List<PromocionDTO> readAll();
	
	public List<PromocionDTO> readOne(int id_promocion);
	
	public boolean insertServProm(int id_promocion , int id_servicio);
	
	public boolean deleteServProm(int id_promocion , int id_servicio);
	
	public List<String> readAllServProm(int id_promo);

	public List<PromocionDTO> readPromoVigente(Date fInicio, Date fFin);

	public boolean deleteRealPromocion(PromocionDTO promocion_a_eliminar);

	public PromocionDTO getById(int idPromocion);

	List<Integer> readAllIDServProm(int id_promocion);


	//public boolean deleteReal(PromocionDTO promocion_a_eliminar);
}
