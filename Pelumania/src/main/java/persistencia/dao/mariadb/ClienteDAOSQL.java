package persistencia.dao.mariadb;

import java.util.List;

import dto.ClienteDTO;
import persistencia.dao.interfaz.ClienteDAO;

public class ClienteDAOSQL implements ClienteDAO{
	
	private static final String insert = "";
	private static final String delete = "";
	private static final String readall = "";

	public boolean insert(ClienteDTO cliente) {

		return false;
	}

	public boolean delete(ClienteDTO cliente_a_eliminar) {

		return false;
	}

	public List<ClienteDTO> readAll() {

		return null;
	}

}
