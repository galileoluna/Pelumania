package persistencia.dao.mariadb;

import persistencia.dao.interfaz.CitaDAO;
import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.HorarioDAO;
import persistencia.dao.interfaz.PromocionDAO;
import persistencia.dao.interfaz.ServicioDAO;
import persistencia.dao.interfaz.SucursalDAO;

public class DAOSQLFactory implements DAOAbstractFactory {

	/* (non-Javadoc)
	 * @see persistencia.dao.interfaz.DAOAbstractFactory#createPersonaDAO()
	 */

	@Override
	public ProfesionalDAOSQL createProfesionalDAO()
	{
		return new ProfesionalDAOSQL();
	}

	@Override
	public ClienteDAO createClienteDAO()
	{
		return new ClienteDAOSQL();
	}

	@Override
	public ServicioDAO createServicioDAO() {
		return new ServicioDAOSQL();
	}

	@Override
	public HorarioDAO createHorarioDAO() {
		return new HorarioDAOSQL();
	}

	@Override
	public SucursalDAO createSucursalDAO() {
		return new SucursalDAOSQL();
	}

	@Override
	public CitaDAO createCitaDAO() {
		return new CitaDAOSQL();
	}
	
	@Override
	public PromocionDAO createPromocionDAO() {
		return new PromocionDAOSQL();
	}

}
