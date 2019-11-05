package persistencia.dao.interfaz;

import java.util.List;
import dto.UsuarioDTO;
import persistencia.dao.mariadb.UsuarioDAOSQL;

import java.util.HashMap;

public interface UsuarioDAO {
	
	public List<UsuarioDTO> readOne(String usuario, String contra);

	public List<UsuarioDTO> readAll();

	public List<UsuarioDTO> readOneById(int id);

	public boolean delete(UsuarioDTO usuario_a_eliminar);

	public boolean update(UsuarioDTO usuario_a_modificar);

	public HashMap<String, Integer> readRol();


	boolean insert(UsuarioDTO user);
	


}
