package persistencia.dao.interfaz;

import java.util.List;

import dto.ServicioDTO;

public interface ServicioDAO {
	
	public boolean insert(ServicioDTO servicio);

	public boolean update(ServicioDTO servicio_a_actualizar);
	
	public boolean delete(ServicioDTO servicio_a_eliminar);
	
	public ServicioDTO getById(int id);
	
	public List<ServicioDTO> readAll();

}

