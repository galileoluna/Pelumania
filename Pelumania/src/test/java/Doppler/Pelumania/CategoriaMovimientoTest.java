package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dto.CategoriaMovimientoCajaDTO;

class CategoriaMovimientoTest {
	CategoriaMovimientoCajaDTO cat;

	@Test
	void test() {
		cat= new CategoriaMovimientoCajaDTO(1,"Pelos","Activo","Bueno");
		
		cat.setEstado("Inactivo");
		cat.setIdCategoria(2);
		cat.setNombre("Peluca");
		cat.setTipoMovimiento("Ingreso");
		
		assertEquals(cat.getEstado(),"Inactivo");
		assertEquals(cat.getIdCategoria(),2);
		assertEquals(cat.getNombre(),"Peluca");
		assertEquals(cat.getTipoMovimiento(),"Ingreso");
		assertEquals(cat.esIngreso(),true);
		
	}

}
