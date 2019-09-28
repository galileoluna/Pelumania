package persistencia.dao.mariadb;

import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ProfesionalDAO;

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

}
