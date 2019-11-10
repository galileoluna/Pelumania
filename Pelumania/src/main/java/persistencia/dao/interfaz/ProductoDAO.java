package persistencia.dao.interfaz;

import java.util.List;

import dto.ProductoDTO;

public interface ProductoDAO {
	public boolean insert(ProductoDTO Producto);

	public boolean update(ProductoDTO Producto_a_actualizar);
	
	public boolean delete(ProductoDTO Producto_a_eliminar);
	
	public ProductoDTO getById(int id);
	
	public List<ProductoDTO> readAll();

	public boolean deleteRealProducto(ProductoDTO Producto_a_eliminar);



	List<ProductoDTO> obtenerProductoBuscado(String variable, String value);

	public ProductoDTO obtenerProductoById(int id);

	public ProductoDTO obtenerPorId(int id);
}
