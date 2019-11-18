package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dto.UsuarioDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;
import persistencia.dao.mariadb.UsuarioDAOSQL;

class UsuarioTest {
	UsuarioDTO usuario;
	Sistema sistema;
	@Test
	void test() {
		sistema=new Sistema(new DAOSQLFactory());
		usuario= new UsuarioDTO(1, "Nicolas", "Cirillo", "ncirillo", "ncirillo", "nico@gmail.com", "Activo", 1, 1);
	sistema.agregarUsuario(usuario);
	sistema.editarUsuario(usuario);

	assertEquals(sistema.obtenerUsuarios1().size(),3);
	sistema.borrarUsuario(usuario);
	assertEquals(sistema.obtenerUsuarios1().get(0).getEstado(),"Activo");
	assertEquals(sistema.matcheo("ncirillo", "ncirillo").get(0).getApellido(),"Cirillo");
		
	}

}
