package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import dto.ProductoDTO;

class ProductoDTOtest {

	@Test
	void test() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		ProductoDTO producto = new ProductoDTO(1,"corte de pelo",precioPeso,precioDolar,10,"activo");
		
		producto.setEstado("Inactivo");
		producto.setidProducto(2);
		producto.setNombre("Brashing");
		producto.setPrecioDolar(precioDolar);
		producto.setPrecioLocal(precioPeso);
		producto.setPuntos(100);

		assertEquals(producto.getEstado(),"Inactivo");
		
		assertEquals(producto.getNombre(),"Brashing");
		
		assertEquals(producto.getPrecioDolar(),precioDolar);
		assertEquals(producto.getPrecioLocal(),precioPeso);
		assertEquals(producto.getPuntos(),100);
		assertEquals(producto.getIdProducto(),2);
	}

}
