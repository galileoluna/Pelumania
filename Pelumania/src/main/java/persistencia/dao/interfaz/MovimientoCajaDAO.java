package persistencia.dao.interfaz;

import java.util.List;

import dto.MovimientoCajaDTO;

public interface MovimientoCajaDAO {

	public boolean insertEgreso(MovimientoCajaDTO movimiento_a_insertar);

	public boolean delete(MovimientoCajaDTO movimiento_a_eliminar);
	
	public List<MovimientoCajaDTO> readAll();
	
	public boolean deleteReal(MovimientoCajaDTO movimiento_a_eliminar);

	public boolean insertIngresoServicioSinPromocion(MovimientoCajaDTO movimiento);

}
