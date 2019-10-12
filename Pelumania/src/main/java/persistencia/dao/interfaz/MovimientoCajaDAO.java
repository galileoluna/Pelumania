package persistencia.dao.interfaz;

import java.util.List;

import dto.MovimientoCajaDTO;
import dto.SucursalDTO;

public interface MovimientoCajaDAO {

	public boolean insertEgreso(MovimientoCajaDTO sucursal);

	public boolean update(MovimientoCajaDTO sucursal_a_actualizar);
	
	public boolean delete(MovimientoCajaDTO sucursal_a_eliminar);
	
	public List<MovimientoCajaDTO> readAll();
	
	public boolean deleteReal(MovimientoCajaDTO promocion_a_eliminar);

}
