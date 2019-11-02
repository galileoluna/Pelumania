package persistencia.dao.interfaz;

import java.sql.Timestamp;
import java.util.List;

import dto.MovimientoCajaDTO;

public interface MovimientoCajaDAO {

	public boolean insertEgreso(MovimientoCajaDTO movimiento_a_insertar);

	public boolean deleteReal(MovimientoCajaDTO movimiento_a_eliminar);

	public boolean insertIngresoServicioSinPromocion(MovimientoCajaDTO movimiento);

	public boolean insertIngresoProducto(MovimientoCajaDTO movimiento_a_insertar);

	public List<MovimientoCajaDTO> readDay(String desde, String hasta);
	
	public List<MovimientoCajaDTO> readDayIngresosProfesional(String fecha, String fecha2, int profesional);

	public List<MovimientoCajaDTO> readDayIngresosCliente(String fecha, String fecha2, int cliente);

	public List<MovimientoCajaDTO> readDayIngresosServicio(String fecha, String fecha2, int servicio);

	public List<MovimientoCajaDTO> readDaySucursal(String fecha, String fecha2, int sucursal);
	
	public List<MovimientoCajaDTO> rankingVentas(String desde,String hasta);

}


