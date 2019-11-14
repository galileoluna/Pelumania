package persistencia.dao.mariadb;

import persistencia.dao.interfaz.CategoriaMovimientoCajaDAO;
import persistencia.dao.interfaz.CitaDAO;
import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.HorarioDAO;
import persistencia.dao.interfaz.MailDAO;
import persistencia.dao.interfaz.MovimientoCajaDAO;
import persistencia.dao.interfaz.ProductoDAO;
import persistencia.dao.interfaz.PromocionDAO;
import persistencia.dao.interfaz.ServicioDAO;
import persistencia.dao.interfaz.ServicioTurnoDAO;
import persistencia.dao.interfaz.SucursalDAO;
import persistencia.dao.interfaz.UsuarioDAO;

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

	@Override
	public MovimientoCajaDAO createMovimientoCajaDAO() {
		return new MovimientoCajaDAOSQL();
	}
	
	@Override
	public CategoriaMovimientoCajaDAO createCategoriaMovimientoCajaDAO() {
		return new CategoriaMovimientoCajaDAOSQL();
	}

	@Override
	public ServicioTurnoDAO createServicioTurnoDAO() {
		return new ServicioTurnoDAOSQL();
	}

	@Override
	public UsuarioDAO createUsuarioDAO() {
		return new UsuarioDAOSQL();
	}
	@Override
	public ProductoDAO createProductoDAO() {
		return new ProductoDAOSQL();
	}

	@Override
	public MailDAO createMailDAO() {
		// TODO Auto-generated method stub
		return new MailDAOSQL();
	}

}
