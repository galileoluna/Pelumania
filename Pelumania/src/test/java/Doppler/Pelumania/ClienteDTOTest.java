package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import dto.ClienteDTO;

class ClienteDTOTest {
	
	ClienteDTO cliente;

	
	

    @Test
	public void testValidarGetID() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		assertEquals(cliente.getIdCliente(),1);
	}
	@Test
	public void testValidarGetApellido() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		assertEquals(cliente.getApellido(),"apellido");
	}
	@Test
	public void testValidarGetNombre() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		assertEquals(cliente.getNombre(),"juan");
	}@Test
	public void testValidarGetTelefono() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		assertEquals(cliente.getTelefono(),"112345");
	}
	@Test
	public void testValidarGetMail() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		assertEquals(cliente.getMail(),"asd@sddd.com");
	}
	@Test
	public void testValidarGetPuntos() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		assertEquals(cliente.getPuntos(),1);
	}
	@Test
	public void testValidarGetEstado() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		assertEquals(cliente.getEstadoCliente(),"activo");
	}
	@Test
	public void testValidarGetDeuda() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		assertEquals(cliente.getDeuda().compareTo(deuda),0);
	}
	@Test
	public void testValidarSetID() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		cliente.setIdCliente(5);
		assertEquals(cliente.getIdCliente(),5);
	}
	@Test
	public void testValidarSetNombre() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		cliente.setNombre("pedro");
		assertEquals(cliente.getNombre(),"pedro");
	}
	@Test
	public void testValidarSetApellido() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		cliente.setApellido("moreno");
		assertEquals(cliente.getApellido(),"moreno");
	}
	@Test
	public void testValidarSetTelefono() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		cliente.setTelefono("1137896016");
		assertEquals(cliente.getTelefono(),"1137896016");
	}
	@Test
	public void testValidarSetMail() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		cliente.setTelefono("santy@gmail.com");
		assertEquals(cliente.getTelefono(),"santy@gmail.com");
	}
	@Test
	public void testValidarSetEstado() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		cliente.setEstadoCliente("inactivo");
		assertEquals(cliente.getEstadoCliente(),"inactivo");
	}
	@Test
	public void testValidarSetPuntos() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda);
		
		cliente.setPuntos(15);
		assertEquals(cliente.getPuntos(),15);
	}