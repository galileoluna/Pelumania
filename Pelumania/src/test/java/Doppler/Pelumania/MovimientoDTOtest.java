package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import dto.MovimientoCajaDTO;

class MovimientoDTOtest {
	MovimientoCajaDTO movimiento;

	@Test
	void test() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		Timestamp timestamp = Timestamp.from(Instant.now());
		movimiento=new MovimientoCajaDTO(1,1,1,timestamp,"Compra Shampo",1,peso,dolar,1,1,1,1);
		movimiento.setCategoria(2);
		movimiento.setDescripcion("Compra Sedal");
		movimiento.setFecha(timestamp);
		movimiento.setIdCaja(2);
		movimiento.setIdCategoria(2);
		movimiento.setIdCita(2);
		movimiento.setIdCliente(2);
		movimiento.setIdProfesional(2);
		movimiento.setIdPromocion(2);
		movimiento.setIdServicio(2);
		movimiento.setIdSucursal(2);
		movimiento.setPrecioDolar(peso);
		movimiento.setPrecioLocal(dolar);
		movimiento.setTipoCambio("Peso");
		
		
		
		
		
		assertEquals(movimiento.getCategoria(),2);
		assertEquals(movimiento.getDescripcion(),"Compra Sedal");
		assertEquals(movimiento.getFecha(),timestamp);
		assertEquals(movimiento.getIdCaja(),2);
		assertEquals(movimiento.getIdCliente(),2);
		assertEquals(movimiento.getIdProfesional(),2);
		assertEquals(movimiento.getIdPromocion(),2);
		assertEquals(movimiento.getIdSucursal(),2);
		assertEquals(movimiento.getIdServicio(),2);
		assertEquals(movimiento.getIdSucursal(),2);
		assertEquals(movimiento.getIdPromocion(),2);
		assertEquals(movimiento.getIdCategoria(),2);
		assertEquals(movimiento.getIdCita(),2);
		assertEquals(movimiento.getPrecioDolar(),peso);
		assertEquals(movimiento.getPrecioLocal(),dolar);
		assertEquals(movimiento.getTipoCambio(),"Peso");
		
		
	}

}
