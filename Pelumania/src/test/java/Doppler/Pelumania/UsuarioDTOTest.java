package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dto.UsuarioDTO;

class UsuarioDTOTest {
	UsuarioDTO usuario;
	
	
	
	@Test
	void test() {
		usuario= new UsuarioDTO(1, "Nicolas", "Cirillo", "ncirillo", "ncirillo", "nico@gmail.com", "Activo", 1, 1);
		usuario.setApellido("Gomez");
		usuario.setNombre("Franco");
		usuario.setContrasenia("1234");
		usuario.setEstado("Inactivo");
		usuario.setMail("a@a.com");
		usuario.setNombreUsuario("fgomez");
		usuario.setIdRol(2);
		usuario.setIdSucursal(2);
		usuario.setIdUsuario(2);
		
		assertEquals(usuario.getApellido(),"Gomez");
		assertEquals(usuario.getNombre(),"Franco");
		
		assertEquals(usuario.getNombreUsuario(),"fgomez");
		assertEquals(usuario.getContrasenia(),"1234");
		assertEquals(usuario.getEstado(),"Inactivo");
		assertEquals(usuario.getMail(),"a@a.com");
		assertEquals(usuario.getIdRol(),2);
		assertEquals(usuario.getSucursal(),"Bella Vista");
		assertEquals(usuario.getIdSucursal(),2);
		assertEquals(usuario.getIdUsuario(),2);
		usuario.getSucuById("San Miguel");
		usuario.getRolById("Contador");
		assertEquals(usuario.hashCode(),917277283);	
		assertEquals(usuario.getRol(),"Contador");
		
	
		
		
	}

}
