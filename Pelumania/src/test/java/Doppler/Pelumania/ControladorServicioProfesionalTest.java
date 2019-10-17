package Doppler.Pelumania;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.ProfesionalDTO;
import dto.ServicioDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;
import presentacion.controlador.ControladorServicioProfesional;

public class ControladorServicioProfesionalTest {
	Sistema sistema;
	ControladorServicioProfesional controlador;
	ProfesionalDTO profesional;
	ServicioDTO nuevoServicio;
	
	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
		profesional=new ProfesionalDTO(1,"juan","perez",0,0,"Activo");
		sistema.agregarProfesional(profesional);
		profesional=new ProfesionalDTO(2,"Gonzalo","perez",0,0,"Activo");
		sistema.agregarProfesional(profesional);
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		nuevoServicio=new ServicioDTO(1,"corte de pelo",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		nuevoServicio=new ServicioDTO(2,"lavado",precioPeso,precioDolar,hora,10,"activo");
		sistema.agregarServicio(nuevoServicio);
		
	}
	
	@After
	public void after() {
		
		sistema.deleteRealServicio(sistema.obtenerServicios().get(1));
		sistema.borrarSanti(sistema.obtenerProfesional().get(1));
		sistema.deleteRealServicio(sistema.obtenerServicios().get(0));
		sistema.borrarSanti(sistema.obtenerProfesional().get(0));
	} 
	
	
	@Test
	public void  validarTrue() {
		controlador=ControladorServicioProfesional.getInstance(sistema, 1, "");
		sistema.agregarServicioProfesional(1,1);
		boolean validar= controlador.validar("corte");
		sistema.agregarServicioProfesional(2,1);
		boolean validar1= controlador.validar("corte");
		
		assertTrue(validar);
		assertTrue(validar1);
		
		sistema.deleteServProfesional(1, 1);
		sistema.deleteServProfesional(2, 1);
	}
	
	@Test
	public void  validarFalse() {
		controlador=ControladorServicioProfesional.getInstance(sistema, 1, "");
		sistema.agregarServicioProfesional(2,2);
		boolean validar= controlador.validar("corte");
		
		assertTrue(validar);
		sistema.deleteServProfesional(2, 2);
	}
	
	@Test
	public void llenarComboTest() {
		JComboBox combo= new JComboBox();
		List<String> lista=new ArrayList<String>();
		for(int i=0;i<10;i++) {
			lista.add("garego"+i);
		}
		controlador.llenarCombo(combo, lista);
		assertEquals(10, combo.getItemCount());
	}
	
	@Test
	public void llenarServTest() {
		List<Integer> id=new ArrayList<Integer>();
		List<String> ret =controlador.llenarServ(id, sistema);
		
		assertEquals(2,ret.size());
		
	}
	
	@Test
	public void llenarServMas1Test() {
		List<Integer> id=new ArrayList<Integer>();
		BigDecimal precioPeso=new BigDecimal(60);
		BigDecimal precioDolar=new BigDecimal(1);
		LocalTime hora=LocalTime.now();
		sistema.agregarServicio(new ServicioDTO(3,"enjuague",precioPeso,precioDolar,hora,10,"activo"));
		List<String> ret =controlador.llenarServ(id, sistema);
		
		assertEquals(3,ret.size());
		
		sistema.deleteRealServicio(sistema.obtenerServicios().get(2));
		
	}
	
}
