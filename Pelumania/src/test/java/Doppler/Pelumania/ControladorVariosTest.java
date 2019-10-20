package Doppler.Pelumania;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.ServicioTurnoDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;
import presentacion.controlador.ControladorAgregarSucursal;
import presentacion.controlador.ControladorCaja;
import presentacion.controlador.ControladorPromocion;


public class ControladorVariosTest {
	Sistema sistema;
	ControladorCaja controladorCaja;
	ControladorAgregarSucursal controladorSucursal;
	ControladorPromocion controladorServPromo;
	List<ServicioTurnoDTO> servicios=new ArrayList<ServicioTurnoDTO>();
	


	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
		for(int i=1;i<=10;i++) {
			ServicioTurnoDTO servTurno=new ServicioTurnoDTO(i, i+1);
			servicios.add(servTurno);
		}
		controladorCaja=ControladorCaja.getInstance(sistema);
	}
	
	@After
	public void after() {
		servicios.clear();
		
	} 
	
	@Test
	public void setServicioCitaTest() {
		controladorCaja.setServiciosCita(servicios);
		
		assertEquals(10,servicios.size());
		
	}
	
	@Test
	public void getServicioCitaTest() {
		controladorCaja.setServiciosCita(servicios);
		List<ServicioTurnoDTO>getLista=controladorCaja.getServiciosCita();
		getLista.remove(1);
		
		assertEquals(9,getLista.size());
	}
	
	
}
