package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import dto.PromocionDTO;

class PromocionDTOtest {

	PromocionDTO promocion;
//int id, String descripcion, Date fechaIn, Date fechaFin, Double desc ,Integer puntos,String estado
	@Test
	void testPromocionContructor() {
		Double desc=new Double("10.5");
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		
		assertEquals(1,promocion.getIdPromocion());
	}
	
@Test
	void testPromocionContructorFallaEnValidar() {
		Double desc=new Double("10.5");
		Date fechaFin=null;
		promocion=new PromocionDTO(1,"",fechaFin,fechaFin,desc,10,"activo");
		boolean valida;
		
		if(promocion==null)valida=false;
		else valida=true;
		
		assertTrue(valida);
	}
	
	@Test
	void testPromocionContructorDescrip() {
		Double desc=new Double("10.5");
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		
		assertEquals("buenas",promocion.getDescripcion());
	}
	
	@Test
	void testPromocionContructorFechaIni() {
		Double desc=new Double("10.5");
		Date fechaIni=new Date(1,5,3);
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaIni,fechaFin,desc,10,"activo");
		
		assertEquals(fechaIni,promocion.getFechaInicio());
	}
	
	@Test
	void testPromocionContructorFechaFin() {
		Double desc=new Double("10.5");
		Date fechaIni=new Date(1,5,3);
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		
		assertEquals(fechaFin,promocion.getFechaFin());
	}
	
	@Test
	void testPromocionContructorSetFechaIni() {
		Double desc=new Double("10.5");
		Date fechaIni=new Date(1,5,3);
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaIni,fechaFin,desc,10,"activo");
		promocion.setFechaInicio(fechaFin);
		
		assertEquals(fechaFin,promocion.getFechaInicio());
	}
	
	@Test
	void testPromocionContructorVerFechaFin() {
		Double desc=new Double("10.5");
		Date fechaIni=new Date(1,5,3);
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		promocion.setFechaFin(fechaIni);
		
		assertEquals(fechaIni,promocion.getFechaFin());
	}
	
	@Test
	void testPromocionContructorDescuento() {
		Double desc=new Double("10.5");
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		
		assertEquals(promocion.getDescuento().compareTo(desc),0);
	}
	
	@Test
	void testPromocionContructorSetDescuento() {
		Double desc=new Double("10.5");
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		desc=new Double("15");
		promocion.setDescuento(desc);
		
		assertEquals(promocion.getDescuento().compareTo(desc),0);
	}
	
	@Test
	void testPromocionContructorPuntos() {
		Double desc=new Double("10.5");
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		
		assertEquals(promocion.getPuntos(),10);
	}
	
	@Test
	void testPromocionContructorSetPuntos() {
		Double desc=new Double("10.5");
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		promocion.setPuntos(50);
		
		assertEquals(promocion.getPuntos(),50);
	}
	
	@Test
	void testPromocionContructorSetId() {
		Double desc=new Double("10.5");
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		promocion.setIdPromocion(100);
		
		assertEquals(promocion.getIdPromocion(),100);
	}
	
	@Test
	void testPromocionContructorSetDescrip() {
		Double desc=new Double("10.5");
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		promocion.setDescripcion("hola");
		
		assertEquals("hola",promocion.getDescripcion());
	}
	
	@Test
	void testPromocionContructorGetEstado() {
		Double desc=new Double("10.5");
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
	
		
		assertEquals(promocion.getEstado(),"activo");
	}
	
	@Test
	void testPromocionContructorSetEstado() {
		Double desc=new Double("10.5");
		Date fechaFin=new Date(1,1,1);
		promocion=new PromocionDTO(1,"buenas",fechaFin,fechaFin,desc,10,"activo");
		promocion.setEstado("inactivo");
		
		assertEquals(promocion.getEstado(),"inactivo");
	}

}
