package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.ProfesionalDTO;
import dto.SucursalDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;
import presentacion.controlador.ControladorAltaProfesional;
import presentacion.controlador.ControladorModificarProfesional;


public class ControladorModificarProfesionalTest {
	Sistema sistema;
	ControladorModificarProfesional controlador;
	SucursalDTO sucursal;
	List<ProfesionalDTO>prof=new ArrayList<ProfesionalDTO>();


	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
		sucursal= new SucursalDTO(1, "pelu1", "avenida", 444, "activa");
		sistema.agregarSucursal(sucursal);
		sucursal= new SucursalDTO(2, "pelu2", "avenida", 444, "activa");
		sistema.agregarSucursal(sucursal);
		sucursal= new SucursalDTO(3, "pelu3", "avenida", 444, "activa");
		sistema.agregarSucursal(sucursal);
		prof=sistema.obtenerProfesional();

	}
	
	@After
	public void after() {
		sistema.borrarSucursalReal(sistema.obtenerSucursales().get(2));
		sistema.borrarSucursalReal(sistema.obtenerSucursales().get(1));
		sistema.borrarSucursalReal(sistema.obtenerSucursales().get(0));
				
	} 
	
	@Test
	public void getIdCombo() {
		controlador= ControladorModificarProfesional.getInstance(sistema, prof, 1);
		int id=controlador.getIdCombo("pelu1");
		int id2=controlador.getIdCombo("pelu2");
		
		assertEquals(id,1);
		assertEquals(id2,2);
	}
	
	@Test
	public void getIdComboNull() {
		controlador= ControladorModificarProfesional.getInstance(sistema, prof, 1);
		int id=controlador.getIdCombo("pelu5");
		int id2=controlador.getIdCombo("pelu6");
		
		assertEquals(id,-1);
		assertEquals(id2,-1);
	}
	
	@Test
	public void validarTrue() {
		controlador= ControladorModificarProfesional.getInstance(sistema, prof, 1);
		
		
		assertEquals(controlador.validar("nico","cirillo",1,2),true);
		assertEquals(controlador.validar("nico","gomez",2,3),true);
	}
	
	@Test
	public void validarFalse() {
		controlador= ControladorModificarProfesional.getInstance(sistema, prof, 1);
		
		
		assertEquals(controlador.validar("","",1,2),false);
		assertEquals(controlador.validar("nico","gomez",null,null),false);
	}
	
	@Test
	public void getSucursalesTest() {
		controlador= ControladorModificarProfesional.getInstance(sistema, prof, 1);
		HashMap<String,Integer> sucursales=controlador.getSucursales();
		
		assertEquals(sucursales.size(), 3);
		
		sucursales.remove("pelu2");
		assertEquals(sucursales.size(), 2);
		
	}
	
	@Test
	public void llenarComboTest() {
		controlador= ControladorModificarProfesional.getInstance(sistema, prof, 1);
		HashMap<String,Integer> sucursales=controlador.getSucursales();
		JComboBox combo= new JComboBox();
		
		controlador.llenarCombo(combo,sucursales);
		combo.removeItemAt(0);
		assertEquals(combo.getItemCount(),3);
	}
}
