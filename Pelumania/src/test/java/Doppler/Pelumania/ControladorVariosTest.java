package Doppler.Pelumania;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.HorarioDTO;
import dto.ProfesionalDTO;
import dto.PromocionDTO;
import dto.ServicioTurnoDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;
import presentacion.controlador.ControladorAltaHorario;
import presentacion.controlador.ControladorCaja;
import presentacion.controlador.ControladorEditarPromo;
import presentacion.controlador.ControladorEditarSucursal;


public class ControladorVariosTest {
	Sistema sistema;
	ControladorCaja controladorCaja;
	ControladorEditarSucursal controladorSucursal;
	ControladorEditarPromo controladorEditarPromo;
	ControladorAltaHorario controladorHorario;
	List<ServicioTurnoDTO> servicios=new ArrayList<ServicioTurnoDTO>();
	ProfesionalDTO prof;
	HorarioDTO horario,horario3,horario2,horario4;
	


	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
		for(int i=1;i<=10;i++) {
			ServicioTurnoDTO servTurno=new ServicioTurnoDTO(i, i+1);
			servicios.add(servTurno);
		}
		controladorCaja=ControladorCaja.getInstance(sistema);
		prof= new ProfesionalDTO(1, "nico", "Cirillo", 1, 2, "Activo");
		sistema.agregarProfesional(prof);
		Time entrada=new Time(Integer.parseInt("14"),Integer.parseInt("20"),00);
		horario= new HorarioDTO(1, "Lunes", entrada,entrada, 1);
		sistema.agregarHorario(horario);
		horario2= new HorarioDTO(2, "Martes", entrada,entrada, 1);
		sistema.agregarHorario(horario2);
		horario3= new HorarioDTO(3, "Miercoles", entrada,entrada, 1);
		sistema.agregarHorario(horario3);
		horario4=new HorarioDTO(4,"Miercoles",entrada,entrada,1);
		sistema.agregarHorario(horario4);
		Date utilDate2 =new Date(10,10, 10);

	}
	
	@After
	public void after() {
		servicios.clear();
		sistema.borrarSanti(prof);
		sistema.borrarHorario(horario);
		sistema.borrarHorario(horario2);
		sistema.borrarHorario(horario3);
		sistema.borrarHorario(horario4);

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
	
	@Test
	public void validarHorarioTrueTest() {
		controladorHorario=ControladorAltaHorario.getInstance(sistema, "nico", "Cirillo", prof.getIdProfesional());

		assertTrue(controladorHorario.validar(horario.getIdProfesional(),"Lunes"));
		assertTrue(controladorHorario.validar(horario2.getIdProfesional(),"Martes"));
		
	}
	
	@Test
	public void validarHorarioFalseTest() {
		controladorHorario=ControladorAltaHorario.getInstance(sistema, "nico", "Cirillo", prof.getIdProfesional());

		assertEquals(false,controladorHorario.validar(horario3.getIdProfesional(),"Miercoles"));
		
	}
	
	@Test
	public void validarEditPromoTest() {
		List<PromocionDTO>promo = new ArrayList<PromocionDTO>();
		controladorEditarPromo= ControladorEditarPromo.getInstance(sistema, promo);
		Date utilDate2 =new Date(10,10, 10);
		
		assertTrue(controladorEditarPromo.validar("hola", utilDate2, utilDate2, 22.00, null, "d"));
		assertTrue(controladorEditarPromo.validar("hola", utilDate2, utilDate2, null, 11, "d"));
		assertEquals(false,controladorEditarPromo.validar("hola", utilDate2, null, 22.00, null, "d"));
		assertEquals(false,controladorEditarPromo.validar("", utilDate2, null, 22.00, null, "d"));
	}
	
}
