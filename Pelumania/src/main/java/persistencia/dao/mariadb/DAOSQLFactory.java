package persistencia.dao.mariadb;

import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;

public class DAOSQLFactory implements DAOAbstractFactory {
		/* (non-Javadoc)
		 * @see persistencia.dao.interfaz.DAOAbstractFactory#createPersonaDAO()
		 */
		public ClienteDAO createClienteDAO() 
		{
					return new ClienteDAOSQL();
		}

}
