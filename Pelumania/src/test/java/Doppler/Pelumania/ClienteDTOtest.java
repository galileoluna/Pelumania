package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import dto.ClienteDTO;

class ClienteDTOtest {
	
	ClienteDTO cliente;

	
	

    @Test
	public void testValidarGetID() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		assertEquals(cliente.getIdCliente(),1);
	}
	@Test
	public void testValidarGetApellido() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		assertEquals(cliente.getApellido(),"apellido");
	}
	@Test
	public void testValidarGetNombre() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		assertEquals(cliente.getNombre(),"juan");
	}@Test
	public void testValidarGetTelefono() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		assertEquals(cliente.getTelefono(),"112345");
	}
	@Test
	public void testValidarGetMail() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		assertEquals(cliente.getMail(),"asd@sddd.com");
	}
	@Test
	public void testValidarGetPuntos() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		assertEquals(cliente.getPuntos(),1);
	}
	@Test
	public void testValidarGetEstado() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		assertEquals(cliente.getEstadoCliente(),"activo");
	}
	@Test
	public void testValidarGetDeudaDolar() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		assertEquals(cliente.getDeudaDolar().compareTo(deuda),0);
	}
	@Test
	public void testValidarGetDeudaPesos() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		assertEquals(cliente.getDeudaPesos().compareTo(deuda),0);
	}
	@Test
	public void testValidarSetDeudaDolar() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		cliente.setDeudaDolar(new BigDecimal(4));
		assertEquals(cliente.getDeudaDolar().compareTo(new BigDecimal(4)),0);
	}
	@Test
	public void testValidarSetDeudaPesos() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		cliente.setDeudaPesos(new BigDecimal(4));
		assertEquals(cliente.getDeudaPesos().compareTo(new BigDecimal(4)),0);
	}
	@Test
	public void testValidarSetID() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		cliente.setIdCliente(5);
		assertEquals(cliente.getIdCliente(),5);
	}
	@Test
	public void testValidarSetNombre() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		cliente.setNombre("pedro");
		assertEquals(cliente.getNombre(),"pedro");
	}
	@Test
	public void testValidarSetApellido() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		cliente.setApellido("moreno");
		assertEquals(cliente.getApellido(),"moreno");
	}
	@Test
	public void testValidarSetTelefono() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		cliente.setTelefono("1137896016");
		assertEquals(cliente.getTelefono(),"1137896016");
	}
	@Test
	public void testValidarSetMail() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		cliente.setMail("santy@gmail.com");
		assertEquals(cliente.getMail(),"santy@gmail.com");
	}
	@Test
	public void testValidarSetEstado() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		cliente.setEstadoCliente("inactivo");
		assertEquals(cliente.getEstadoCliente(),"inactivo");
	}
	@Test
	public void testValidarSetPuntos() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		cliente.setPuntos(15);
		assertEquals(cliente.getPuntos(),15);
	}
	@Test
	public void testValidarToString() {
		BigDecimal deuda=new BigDecimal(5);
		cliente=new ClienteDTO(1,"juan","apellido","112345","asd@sddd.com",1,"activo",deuda,deuda);
		
		assertTrue(cliente.toString().equals("juan apellido"));
	}
}