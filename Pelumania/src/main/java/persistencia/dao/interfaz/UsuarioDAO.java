package persistencia.dao.interfaz;

import java.util.List;
import dto.UsuarioDTO;

public interface UsuarioDAO {
	
	public List<UsuarioDTO> readOne(String usuario, String contra);

}
