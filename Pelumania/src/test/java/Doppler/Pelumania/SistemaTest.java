package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import dto.ClienteDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

class SistemaTest {
	Sistema sistema;
	ClienteDTO nuevoCliente;
	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
	}
	
	@Test
	void sistemaCrearClienteBasicoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),1);
	}
	
	@Test
	void sistemaCrearClienteBasicoVerNombreTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().get(0).getNombre(),"juan");
	}
	
	@Test
	void sistemaCrearClienteBasicoVerApellidoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().get(0).getApellido(),"apellido");
	}
	
	@Test
	void sistemaCrearClienteBasicoVerTelefonoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().get(0).getTelefono(),"112345");
	}
	
	@Test
	void sistemaCrearClienteBasicoVerMailTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().get(0).getMail(),"asd@sddd.com");
	}
	
	@Test
	void sistemaCrearClienteBasicoVerPuntosTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().get(0).getPuntos(),0);
	}
	
	@Test
	void sistemaCrearClienteBasicoVerEstadoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().get(0).getEstadoCliente(),"activo");
	}
	
	@Test
	void sistemaCrearClienteBasicoVerDeudaTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().get(0).getDeuda().compareTo(deuda),0);
	}
	
	@Test
	void sistemaCrearDosClientesTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		sistema.agregarCliente(nuevoCliente);
		deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(2,"pedro","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),2);
	}
	
	@Test
	void sistemaCrearClienteVacioTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		assertEquals(sistema.obtenerClientes().size(),0);
	}
	
	@Test
	void sistemaCrearClienteDatoIncorrectoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);

		assertEquals(sistema.obtenerClientes().size(),0);
	}
	
	@Test
	void sistemaBorrarClienteBasicoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		sistema.borrarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),0);
	}
	
	@Test
	void sistemaBorrarClienteNoExisteTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.borrarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().size(),0);
	}
	
	@Test
	void sistemaEditarClienteBasicoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);		
		nuevoCliente=new ClienteDTO(1,"pedro","apellido","112345","asd@sddd.com",0,"activo",deuda);
		sistema.editarCliente(nuevoCliente);
		
		assertEquals(sistema.obtenerClientes().get(0).getNombre(),"pedro");
	}
	
	@Test
	void sistemaBuscarClienteConBuscadoBasicoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);		
		
		assertEquals(sistema.obtenerClienteConBuscador("nombre", "juan").get(0).getNombre(),"juan");
	}
	
	@Test
	void sistemaBuscarClienteConBuscadoEntreDosTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		ClienteDTO cliente=new ClienteDTO(1,"jose","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);
		sistema.agregarCliente(cliente);
		
		assertEquals(sistema.obtenerClienteConBuscador("nombre", "jose").get(0).getNombre(),"jose");
	}
	
	@Test
	void sistemaBuscarClienteConBuscadoBasicoApellidoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);		
		
		assertEquals(sistema.obtenerClienteConBuscador("nombre", "juan").get(0).getApellido(),"apellido");
	}
	
	@Test
	void sistemaBuscarClienteConBuscadoBasicoTelefonoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);		
		
		assertEquals(sistema.obtenerClienteConBuscador("nombre", "juan").get(0).getTelefono(),"112345");
	}
	
	@Test
	void sistemaBuscarClienteConBuscadoBasicoMailTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",0,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);		
		
		assertEquals(sistema.obtenerClienteConBuscador("nombre", "juan").get(0).getMail(),"asd@sddd.com");
	}
	
	@Test
	void sistemaBuscarClienteConBuscadoBasicoPuntosTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",10,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);		
		
		assertEquals(sistema.obtenerClienteConBuscador("nombre", "juan").get(0).getPuntos(),10);
	}
	
	@Test
	void sistemaBuscarClienteConBuscadoBasicoEstadoTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",10,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);		
		
		assertEquals(sistema.obtenerClienteConBuscador("nombre", "juan").get(0).getEstadoCliente(),"activo");
	}

	
	@Test
	void sistemaBuscarClienteConBuscadoBasicoDeudaTest() {
		BigDecimal deuda=new BigDecimal(5);
		nuevoCliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",10,"activo",deuda);
		
		sistema.agregarCliente(nuevoCliente);		
		
		assertEquals(sistema.obtenerClienteConBuscador("nombre", "juan").get(0).getDeuda().compareTo(deuda),0);
	}
}
