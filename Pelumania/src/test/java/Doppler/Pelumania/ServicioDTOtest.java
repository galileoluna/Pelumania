package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import dto.ServicioDTO;

class ServicioDTOtest {
	ServicioDTO nuevoServicio;

	@Test
	void test() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		
		BigDecimal precioPeso2=new BigDecimal(70);
		BigDecimal precioDolar2=new BigDecimal(2);
		
		nuevoServicio.setDuracion(hora);
		nuevoServicio.setEstado("Inactivo");
		nuevoServicio.setIdServicio(2);
		nuevoServicio.setNombre("Brashing");
		nuevoServicio.setPrecioDolar(precioDolar2);
		nuevoServicio.setPrecioLocal(precioPeso2);
		nuevoServicio.setPuntos(100);
		
		assertEquals(nuevoServicio.getDuracion(),hora);
		assertEquals(nuevoServicio.getEstado(),"Inactivo");
		assertEquals(nuevoServicio.getIdServicio(),2);
		assertEquals(nuevoServicio.getNombre(),"Brashing");
		
		assertEquals(nuevoServicio.getPrecioDolar(),precioDolar2);
		assertEquals(nuevoServicio.getPrecioLocal(),precioPeso2);
		assertEquals(nuevoServicio.getPuntos(),100);
		
	}
}
