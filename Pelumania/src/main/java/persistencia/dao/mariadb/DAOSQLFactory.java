package persistencia.dao.mariadb;

import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.HorarioDAO;
import persistencia.dao.interfaz.ProfesionalDAO;
import persistencia.dao.interfaz.ServicioDAO;
import persistencia.dao.interfaz.SucursalDAO;

public class DAOSQLFactory implements DAOAbstractFactory {
		
		/* (non-Javadoc)
		 * @see persistencia.dao.interfaz.DAOAbstractFactory#createPersonaDAO()
		 */

		public ProfesionalDAOSQL createProfesionalDAO()
		{
			return new ProfesionalDAOSQL();
		}
		
		public ClienteDAO createClienteDAO() 
		{
			return new ClienteDAOSQL();
		}

		public ServicioDAO createServicioDAO() {
			return new ServicioDAOSQL();
		}
		
		public HorarioDAO createHorarioDAO() {
			return new HorarioDAOSQL();
		}

		@Override
		public SucursalDAO createSucursalDAO() {
			return new SucursalDAOSQL();
		}

}
