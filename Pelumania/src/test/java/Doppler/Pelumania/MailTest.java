package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dto.MailDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

class MailTest {

	@Test
	void test() {
		
		MailDTO mail=new MailDTO(0, 0, null);
		sistema=new Sistema(new DAOSQLFactory());
	
	}

}
