package persistencia.dao.interfaz;

import java.util.List;

import dto.MovimientoCajaDTO;

public interface MovimientoCajaDAO {

	public boolean insertEgreso(MovimientoCajaDTO movimiento_a_insertar);

	public boolean delete(MovimientoCajaDTO movimiento_a_eliminar);
		
	public boolean deleteReal(MovimientoCajaDTO movimiento_a_eliminar);

	public boolean insertIngresoServicioSinPromocion(MovimientoCajaDTO movimiento);

	public boolean insertIngresoProducto(MovimientoCajaDTO movimiento_a_insertar);

	public List<MovimientoCajaDTO> readAllEgresos();

	public List<MovimientoCajaDTO> readAllIngresos();

	public List<MovimientoCajaDTO> readDayEgresos(String fecha, String fecha2);
	
	public List<MovimientoCajaDTO> readDayIngresos(String fecha, String fecha2);
}
