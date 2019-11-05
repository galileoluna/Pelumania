package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dto.SucursalDTO;

class SucursalDTOtest {
	SucursalDTO sucursal;

//int idSucursal, String nombreSucursal, String direccion, int numero, String estadoSucursal
	@Test
	void testSucursalConstructorIdSucursal() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		
		assertEquals(sucursal.hashCode(),1121248442);
		assertEquals(sucursal.getIdSucursal(),1);
	}
	
	@Test
	void testSucursalConstructorNombreSucursal() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		
		assertEquals(sucursal.getNombreSucursal(),"san miguel");
		assertEquals(sucursal.toString(),"san miguel");

	}
	
	@Test
	void testSucursalConstructorDireccion() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		
		assertEquals(sucursal.getDireccion(),"spain");
	}
	
	@Test
	void testSucursalConstructorAltura() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		
		assertEquals(sucursal.getNumero(),1010);
	}
	
	@Test
	void testSucursalConstructorEstado() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		
		assertEquals(sucursal.getEstadoSucursal(),"activo");
	}
	@Test
	void testSucursalConstructorSetIdSucursal() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		
		sucursal.setIdSucursal(10);
		assertEquals(sucursal.getIdSucursal(),10);
	}
	
	@Test
	void testSucursalConstructorSetNombreSucursal() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		sucursal.setNombreSucursal("jcp");
		
		assertEquals(sucursal.getNombreSucursal(),"jcp");
	}
	
	@Test
	void testSucursalConstructorSetDireccion() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		sucursal.setDireccion("espana");
		
		assertEquals(sucursal.getDireccion(),"espana");
	}
	
	@Test
	void testSucursalConstructorSetAltura() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		sucursal.setNumero(1011);
		
		assertEquals(sucursal.getNumero(),1011);
	}
	
	@Test
	void testSucursalConstructorSetEstado() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		sucursal.setEstadoSucursal("inactivo");
		
		assertEquals(sucursal.getEstadoSucursal(),"inactivo");
	}
	@Test
	void testEquals() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		SucursalDTO sucursal1=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		sucursal.setEstadoSucursal("inactivo");
		
		assertFalse(sucursal.equals(sucursal1));
	}
	@Test
	void testNEquals() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		SucursalDTO sucursal1=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		sucursal.setEstadoSucursal("inactivo");
		
		assertTrue(sucursal.equals(sucursal));
	}
	@Test
	void testNullEquals() {
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
	
		
		assertFalse(sucursal.equals(null));
	}
	
	
}
