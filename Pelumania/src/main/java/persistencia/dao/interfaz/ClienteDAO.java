package persistencia.dao.interfaz;

import java.util.List;

import dto.ClienteDTO;

public interface ClienteDAO {
	
	public boolean insert(ClienteDTO cliente);

	public boolean delete(ClienteDTO cliente_a_eliminar);
	
	public boolean update(ClienteDTO cliente_a_eliminar);

	public void editar(ClienteDTO cliente_a_editar);

	public List<ClienteDTO> readAll();


}
