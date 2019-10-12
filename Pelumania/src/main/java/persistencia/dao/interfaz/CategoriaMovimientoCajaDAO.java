package persistencia.dao.interfaz;

import java.util.List;

import dto.CategoriaMovimientoCajaDTO;

public interface CategoriaMovimientoCajaDAO {

	public boolean insert(CategoriaMovimientoCajaDTO categoria);

	public boolean update(CategoriaMovimientoCajaDTO categoria_a_actualizar);
	
	public boolean delete(CategoriaMovimientoCajaDTO categoria_a_eliminar);
	
	public boolean deleteRealCategoria(CategoriaMovimientoCajaDTO categoria_a_eliminar);
	
	public List<CategoriaMovimientoCajaDTO> readAll();

	public CategoriaMovimientoCajaDTO readOne(int idCategoria);
}
