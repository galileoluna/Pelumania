package ObjetosTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.ClienteDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

public class ClienteTest {
	Sistema sistema;
	ClienteDTO nuevoCliente;
	
	
	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
		
		for(int i=0; i<sistema.obtenerClientes().size();i++) {
		//	System.out.print(sistema.obtenerClientes().size());
		//	System.out.println(sistema.obtenerClientes().get(0).getNombre());
			sistema.deleteReal(sistema.obtenerClientes().get(0));

		}
	}
	@After
	public void after() {
		for(int i=0; i<sistema.obtenerClientes().size();i++) {
		//	System.out.print(sistema.obtenerClientes().size());
		//	System.out.println(sistema.obtenerClientes().get(0).getNombre());
			sistema.deleteReal(sistema.obtenerClientes().get(0));

		}
	}
	
	@Test
	public void testAgregarClienteValidarApellido() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		sistema.agregarCliente(nuevoCliente);

		assertEquals(sistema.obtenerClientes().get(0).getApellido(),"apellido");
	}
	
	@Test
	public void testAgregarClienteValidoNombre() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(1,"pedro","nn","145","sss@sddd.com",1,"activo",deuda);
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().get(0).getNombre(),"pedro");
	}
	@Test
	public void testAgregarClienteValidoId2Datos() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(1,"pedro","nn","145","sss@sddd.com",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		nuevoCliente=new ClienteDTO(2,"toto","ferro","14125","ssds@sddd.com",50,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		
	
		assertEquals(sistema.obtenerClientes().get(1).getNombre(),"toto");
	}
	
	@Test
	public void testCantDeDatos3() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(1,"pedro","nn","145","sss@sddd.com",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		nuevoCliente=new ClienteDTO(2,"toto","ferro","14125","ssds@sddd.com",50,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		nuevoCliente=new ClienteDTO(3,"juan","luna","1412325","ssds@sddd.com",50,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
	
	
		assertEquals(sistema.obtenerClientes().size(),3);
	}

	@Test
	public void testCantDeDatos6() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(1,"pedro","nn","145","sss@sddd.com",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		nuevoCliente=new ClienteDTO(2,"toto","ferro","14125","ssds@sddd.com",50,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		nuevoCliente=new ClienteDTO(3,"juan","luna","1412325","ssds@sddd.com",50,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		nuevoCliente=new ClienteDTO(4,"lucas","nn","145","sss@sddd.com",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		nuevoCliente=new ClienteDTO(5,"mati","ferro","14125","ssds@sddd.com",50,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		nuevoCliente=new ClienteDTO(6,"maxi","luna","1412325","ssds@sddd.com",50,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
	
		assertEquals(sistema.obtenerClientes().size(),6);
	}
	@Test
	public void testinsertarYborrar() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(1,"pedro","nn","145","sss@sddd.com",1,"activo",deuda);
		sistema.agregarCliente(nuevoCliente);
		assertEquals(sistema.obtenerClientes().size(),1);
		
		sistema.deleteReal(nuevoCliente);
		assertEquals(sistema.obtenerClientes().size(),0);
	}

	@Test
	public void testinsertarYborrarConMasDatos() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(1,"pedro","nn","145","sss@sddd.com",1,"activo",deuda);
		sistema.agregarCliente(nuevoCliente);
		nuevoCliente=new ClienteDTO(2,"juan","nn","145","sss@sddd.com",1,"activo",deuda);
		sistema.agregarCliente(nuevoCliente);
		assertEquals(sistema.obtenerClientes().size(),2);
		
		sistema.deleteReal(nuevoCliente);
		assertEquals(sistema.obtenerClientes().size(),1);
	}
	
	@Test
	public void testAgregarClienteInvalido() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"pedro","nn","145","sss@sddd.com",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		nuevoCliente=new ClienteDTO(0,"pedro","nn","145","sss@sddd.com",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),1);	
	}
	
	@Test
	public void testAgregarClienteInvalidoNombreVacio() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"","nn","145","sss@sddd.com",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),0);	
	}
	
	@Test
	public void testAgregarClienteInvalidoApellidoVacio() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","","145","sss@sddd.com",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),0);	
	}
	
	@Test
	public void testAgregarClienteValidoTelefonoVacio() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","","sss@sddd.com",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),1);	
	}
	
	@Test
	public void testAgregarClienteValidoMailVacio() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","12344","",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),1);	
	}
	
	@Test
	public void testAgregarClienteValidoMailIncorrecto() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","12344","asd",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),0);	
	}
	
	@Test
	public void testAgregarClienteInvalidoSinMailSinTel() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","","",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),0);	
	}
	
	@Test
	public void testAgregarClienteActivo() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","123534","",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
				
		assertEquals(sistema.obtenerClientes().get(0).getEstadoCliente(),"activo");	
	}
	@Test
	public void testAgregarClienteInactivo() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","123534","",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		sistema.obtenerClientes().get(0).setEstadoCliente("Inactivo");
				
		assertEquals(sistema.obtenerClientes().get(0).getEstadoCliente(),"inactivo");	
	}
	
	@Test
	public void testAgregarClienteModifApellido() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","123534","",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		sistema.obtenerClientes().get(0).setApellido("ruben");
				
		assertEquals(sistema.obtenerClientes().get(0).getApellido(),"ruben");	
	}
	
	@Test
	public void testAgregarClienteModifDeuda() {
		BigDecimal deuda=new BigDecimal(0);
		BigDecimal deudaNueva=new BigDecimal(10);
		
		nuevoCliente=new ClienteDTO(0,"juan","moreno","123534","",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		sistema.obtenerClientes().get(0).setDeuda(deudaNueva);
				
		assertEquals(sistema.obtenerClientes().get(0).getDeuda(),deudaNueva);	
	}
	
	@Test
	public void testAgregarClienteValidoBorrarVerCantidad() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","122423","",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		sistema.borrarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),1);	
	}
	@Test
	public void testAgregarClienteValidoBorrarVerEstado() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","122423","",1,"activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		sistema.borrarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().get(0).getEstadoCliente(),"inactivo");	
	}
	
	@Test
	public void testAgregarClienteYModifMail_a_Invalido() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","123534","asd@hotmail.com",1,"Activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		sistema.obtenerClientes().get(0).setMail("12234");
		
		
		assertEquals(sistema.obtenerClientes().get(0).getMail(),"asd@hotmail.com");	
	}
	
	@Test
	public void testAgregarClienteYModifMail_a_valido() {
		BigDecimal deuda=new BigDecimal(0);
		nuevoCliente=new ClienteDTO(0,"juan","moreno","123534","",1,"Activo",deuda);		
		sistema.agregarCliente(nuevoCliente);
		sistema.obtenerClientes().get(0).setMail("asd@hotmail.com");
		
		
		assertEquals(sistema.obtenerClientes().get(0).getMail(),"asd@hotmail.com");	
	}
}
