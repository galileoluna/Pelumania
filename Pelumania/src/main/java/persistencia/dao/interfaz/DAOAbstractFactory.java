package persistencia.dao.interfaz;

public interface DAOAbstractFactory {

	public ClienteDAO createClienteDAO();

	public ProfesionalDAO createProfesionalDAO();
	
	public ServicioDAO createServicioDAO();
}
