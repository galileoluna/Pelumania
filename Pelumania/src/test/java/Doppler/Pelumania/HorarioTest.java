package Doppler.Pelumania;

import static org.junit.Assert.*;

import java.sql.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.HorarioDTO;
import dto.ProfesionalDTO;
import dto.SucursalDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

public class HorarioTest {

	Sistema sistema;
	HorarioDTO nuevoHorario;
	ProfesionalDTO profesional;
	SucursalDTO sucursal;
	
	
	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
		profesional=new ProfesionalDTO(0,"juan","perez",0,0,"Activo");
		sucursal=new SucursalDTO(0,"bsas","nnose 123",123,"Activo");
		
		sistema.agregarSucursal(sucursal);
		sistema.agregarProfesional(profesional);
		
			
		for(int i=0; i<sistema.obtenerHorario(0).size();i++) {
			sistema.borrarHorario(sistema.obtenerHorario(0).get(0));
		}
	} 
	@After
	public void after() {
		for(int i=0; i<sistema.obtenerHorario(0).size();i++) {
			sistema.borrarHorario(sistema.obtenerHorario(0).get(0));		
		}
		sistema.borrarSanti(profesional);
		sistema.borrarSucursal(sucursal);
	}
	
	@Test 
	public void testAgregarHorarioCorrectoVerSize() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		sistema.agregarHorario(nuevoHorario);
		
		assertEquals(sistema.obtenerHorario(0).size(),1);
	}
	

	@Test 
	public void testAgregarHorarioCorrectoVerSizeCon3() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		sistema.agregarHorario(nuevoHorario);
		
		entrada=new Time(Integer.parseInt("11"),Integer.parseInt("00"),00);
		salida=new Time(Integer.parseInt("19"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(1,"martes",entrada,salida,2);
		
		sistema.agregarHorario(nuevoHorario);
		
		entrada=new Time(Integer.parseInt("12"),Integer.parseInt("00"),00);
		salida=new Time(Integer.parseInt("20"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(2,"martes",entrada,salida,3);
		
		sistema.agregarHorario(nuevoHorario);
		
		assertEquals(sistema.obtenerHorario(0).size(),3);
	}
	
	@Test 
	public void testAgregarHorarioCorrectoVerDia() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		sistema.agregarHorario(nuevoHorario);
		
		assertEquals(sistema.obtenerHorario(0).get(0).getDia(),"martes");
	}
	
	@Test 
	public void testAgregarHorarioCorrectoVerIdProf() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		sistema.agregarHorario(nuevoHorario);
		
		assertEquals(sistema.obtenerHorario(0).get(0).getIdProfesional(),1);
	}
	
	@Test 
	public void testAgregarHorarioCorrectoVerHoraEntrada() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		sistema.agregarHorario(nuevoHorario);
		
		if(sistema.obtenerHorario(0).get(0).getHoraEntrada().compareTo(entrada)==0) 
			assertTrue(true);
		else assertTrue(false);
	}
	
	@Test 
	public void testAgregarHorarioCorrectoVerHoraSalida() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		sistema.agregarHorario(nuevoHorario);
		
		if(sistema.obtenerHorario(0).get(0).getHoraSalida().compareTo(salida)==0) 
			assertTrue(true);
		else assertTrue(false);
	}

	@Test 
	public void testAgregarHorarioCorrectoBorrar() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		sistema.agregarHorario(nuevoHorario);
		sistema.borrarHorario(nuevoHorario);
		
		assertEquals(sistema.obtenerHorario(0).size(),0);
	}

	@Test 
	public void testAgregarHorarioCorrectoBorrar1enVarios() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		sistema.agregarHorario(nuevoHorario);
		
		entrada=new Time(Integer.parseInt("11"),Integer.parseInt("00"),00);
		salida=new Time(Integer.parseInt("19"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(1,"martes",entrada,salida,2);
		
		sistema.agregarHorario(nuevoHorario);
		
		entrada=new Time(Integer.parseInt("12"),Integer.parseInt("00"),00);
		salida=new Time(Integer.parseInt("20"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(2,"martes",entrada,salida,3);
		
		sistema.agregarHorario(nuevoHorario);
	
		sistema.borrarHorario(nuevoHorario);
		
		assertEquals(sistema.obtenerHorario(0).size(),2);
	}
	
	@Test 
	public void testAgregarHorarioCorrectoBorrarVarios() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		sistema.agregarHorario(nuevoHorario);
		
		entrada=new Time(Integer.parseInt("11"),Integer.parseInt("00"),00);
		salida=new Time(Integer.parseInt("19"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(1,"martes",entrada,salida,2);
		
		sistema.agregarHorario(nuevoHorario);
		
		entrada=new Time(Integer.parseInt("12"),Integer.parseInt("00"),00);
		salida=new Time(Integer.parseInt("20"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(2,"martes",entrada,salida,3);
		
		sistema.agregarHorario(nuevoHorario);
	
		sistema.borrarHorario(nuevoHorario);
		sistema.borrarHorario(sistema.obtenerHorario(0).get(0));
		sistema.borrarHorario(sistema.obtenerHorario(0).get(0));
		
		assertEquals(sistema.obtenerHorario(0).size(),0);
	}
	
	@Test 
	public void testAgregarHorarioModifCorrectoAcorrecto() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		sistema.agregarHorario(nuevoHorario);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,5);
		sistema.actualizarHorario(nuevoHorario);
		
		assertEquals(sistema.obtenerHorario(0).size(),0);
	}
	
	@Test 
	public void testAgregarHorarioModifHorarioEntrada() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		sistema.agregarHorario(nuevoHorario);
		
		entrada=new Time(Integer.parseInt("12"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,5);
		sistema.actualizarHorario(nuevoHorario);
		
		if(sistema.obtenerHorario(0).get(0).getHoraEntrada().compareTo(entrada)==0) 
			assertTrue(true);
		else assertTrue(false);
	}

	@Test 
	public void testAgregarHorarioModifHorarioSalida() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		sistema.agregarHorario(nuevoHorario);
		
		salida=new Time(Integer.parseInt("20"),Integer.parseInt("50"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,5);
		sistema.actualizarHorario(nuevoHorario);
		
		if(sistema.obtenerHorario(0).get(0).getHoraSalida().compareTo(salida)==0) 
			assertTrue(true);
		else assertTrue(false);
	}
	
	@Test 
	public void testAgregarHorarioModifHorarioSalidaInvalido() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		sistema.agregarHorario(nuevoHorario);
		
		Time salidaIncorrecto=new Time(Integer.parseInt("50"),Integer.parseInt("50"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salidaIncorrecto,5);
		sistema.actualizarHorario(nuevoHorario);
		
		if(sistema.obtenerHorario(0).get(0).getHoraSalida().compareTo(salidaIncorrecto)==0) 
			assertTrue(false);
		else assertTrue(true);
	}

}
