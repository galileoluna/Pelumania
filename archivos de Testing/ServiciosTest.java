package ObjetosTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalTime;

import org.junit.Test;


import org.junit.After;
import org.junit.Before;

import dto.ServicioDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

public class ServiciosTest {

	Sistema sistema;
	ServicioDTO nuevoServicio;
	
	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
		
		for(int i=0; i<sistema.obtenerServicios().size();i++) {
		//	System.out.print(sistema.obtenerClientes().size());
		//	System.out.println(sistema.obtenerClientes().get(0).getNombre());
			sistema.deleteRealServicio(sistema.obtenerServicios().get(0));
		}
	}
	
	@After
	public void after() {
		for(int i=0; i<sistema.obtenerServicios().size();i++) {
		//	System.out.print(sistema.obtenerClientes().size());
		//	System.out.println(sistema.obtenerClientes().get(0).getNombre());
			sistema.deleteRealServicio(sistema.obtenerServicios().get(0));
		}
	} 
	
	@Test
	public void testAgregarVerNombre() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);

		assertEquals(sistema.obtenerServicios().get(0).getNombre(),"corte de pelo");
	}

	@Test
	public void testAgregarVerSizeCon1() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.of(4, 30);
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);

		assertEquals(sistema.obtenerServicios().size(),1);
	}
	
	@Test
	public void testAgregarVerSizeCon3() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		nuevoServicio=new ServicioDTO(2,"lavado",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		nuevoServicio=new ServicioDTO(3,"alisado",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		
		assertEquals(sistema.obtenerServicios().size(),3);
	}
	
	@Test
	public void testAgregarServicioConPuntosEnNegativo() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		nuevoServicio=new ServicioDTO(2,"alisado",precioPeso,precioDolar,hora,-10,"activo");
		sistema.agregarServicio(nuevoServicio);
		
		
		assertEquals(sistema.obtenerServicios().size(),1);
	}
	
	@Test
	public void testAgregarServicioConPuntosEnCero() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		nuevoServicio=new ServicioDTO(2,"alisado",precioPeso,precioDolar,hora,0,"activo");
		sistema.agregarServicio(nuevoServicio);
		
		
		assertEquals(sistema.obtenerServicios().size(),2);
	}
	
	@Test
	public void testAgregarServicioConPuntosEnPositivo() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		nuevoServicio=new ServicioDTO(2,"alisado",precioPeso,precioDolar,hora,1,"activo");
		sistema.agregarServicio(nuevoServicio);
		
		
		assertEquals(sistema.obtenerServicios().size(),2);
	}
	
	@Test
	public void testAgregarServicioYEliminar() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		nuevoServicio=new ServicioDTO(2,"alisado",precioPeso,precioDolar,hora,1,"activo");
		sistema.agregarServicio(nuevoServicio);
		sistema.deleteRealServicio(sistema.obtenerServicios().get(1));
		
		assertEquals(sistema.obtenerServicios().size(),1);
	}
	
	@Test
	public void testAgregarServicioYEliminarDos() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		nuevoServicio=new ServicioDTO(2,"alisado",precioPeso,precioDolar,hora,1,"activo");
		sistema.agregarServicio(nuevoServicio);
		sistema.deleteRealServicio(sistema.obtenerServicios().get(0));
		sistema.deleteRealServicio(sistema.obtenerServicios().get(0));
		
		assertEquals(sistema.obtenerServicios().size(),0);
	}
	
	@Test
	public void testAgregarServicioYModificarVerPuntos() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,100,"activo");
		sistema.editarServicio(nuevoServicio);
		
		
		assertEquals(sistema.obtenerServicios().get(0).getPuntos(),100);
	}
	
	@Test
	public void testAgregarServicioYModificarVerPrecioDolar() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		
		precioDolar=new BigDecimal(10);
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.editarServicio(nuevoServicio);
		
		
		if(sistema.obtenerServicios().get(0).getPrecioDolar().compareTo(precioDolar)==0) 
			assertTrue(true);
		else assertTrue(false);
	}
	
	@Test
	public void testAgregarServicioYModificarVerPrecioPeso() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		
		precioPeso=new BigDecimal(10);
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.editarServicio(nuevoServicio);
		
		
		if(sistema.obtenerServicios().get(0).getPrecioLocal().compareTo(precioPeso)==0) 
			assertTrue(true);
		else assertTrue(false);
	}
	
	@Test
	public void testAgregarServicioYModificarVerHora() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		
		LocalTime hora1=LocalTime.of(4, 30);
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora1,10,"activo");
		sistema.editarServicio(nuevoServicio);
		
		if(sistema.obtenerServicios().get(0).getDuracion().compareTo(hora1)==0) 
			assertTrue(true);
		else assertTrue(false);
		
	}
	
	@Test
	public void testAgregarServicioYModificarVerNombre() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		nuevoServicio=new ServicioDTO(1,"corte para hombres",precioPeso,precioDolar,hora,10,"activo");
		sistema.editarServicio(nuevoServicio);
		 
		assertEquals(sistema.obtenerServicios().get(0).getNombre(),"corte para hombres");

	}
	
	@Test
	public void testAgregarServicioYModificarEstado() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		sistema.borrarServicio(nuevoServicio);
		
		
		assertEquals(sistema.obtenerServicios().get(0).getEstado(),"Inactivo");
	}
	
	@Test
	public void testAgregarServicioYModificarEstadoActivo() {
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		sistema.borrarServicio(nuevoServicio);
		
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.editarServicio(nuevoServicio);
		
		
		assertEquals(sistema.obtenerServicios().get(0).getEstado(),"activo");
	}
	
}
