package persistencia.dao.interfaz;

import java.util.List;

import dto.SucursalDTO;


public interface SucursalDAO {
	
	public boolean insert(SucursalDTO sucursal);

	public boolean update(SucursalDTO sucursal_a_actualizar);
	
	public boolean delete(SucursalDTO sucursal_a_eliminar);
	
	public boolean deleteRealSucursal(SucursalDTO sucursal_a_eliminar);
	
	public List<SucursalDTO> readAll();

	public SucursalDTO readOne(int idSucursal);
}
