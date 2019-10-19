package Doppler.Pelumania;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import javax.swing.JComboBox;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dto.SucursalDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;
import presentacion.controlador.ControladorAltaProfesional;


public class ControladorAltaProfesionalTest {
	Sistema sistema;
	ControladorAltaProfesional controlador;
	SucursalDTO sucursal;


	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
		sucursal= new SucursalDTO(1, "pelu1", "avenida", 444, "activa");
		sistema.agregarSucursal(sucursal);
		sucursal= new SucursalDTO(2, "pelu2", "avenida", 444, "activa");
		sistema.agregarSucursal(sucursal);
		sucursal= new SucursalDTO(3, "pelu3", "avenida", 444, "activa");
		sistema.agregarSucursal(sucursal);

	}
	
	@After
	public void after() {
		sistema.borrarSucursalReal(sistema.obtenerSucursales().get(2));
		sistema.borrarSucursalReal(sistema.obtenerSucursales().get(1));
		sistema.borrarSucursalReal(sistema.obtenerSucursales().get(0));
				
	} 
	
	@Test
	public void getIdCombo() {
		controlador= ControladorAltaProfesional.getInstance(sistema);
		int id=controlador.getIdCombo("pelu1");
		int id2=controlador.getIdCombo("pelu2");
		
		assertEquals(id,1);
		assertEquals(id2,2);
	}
	
	@Test
	public void getIdComboNull() {
		controlador= ControladorAltaProfesional.getInstance(sistema);
		int id=controlador.getIdCombo("pelu5");
		int id2=controlador.getIdCombo("pelu6");
		
		assertEquals(id,-1);
		assertEquals(id2,-1);
	}
	
	@Test
	public void validarTrue() {
		controlador= ControladorAltaProfesional.getInstance(sistema);
		
		
		assertEquals(controlador.validar("nico","cirillo",1,2),true);
		assertEquals(controlador.validar("nico","gomez",2,3),true);
	}
	
	@Test
	public void validarFalse() {
		controlador= ControladorAltaProfesional.getInstance(sistema);
		
		
		assertEquals(controlador.validar("","",1,2),false);
		assertEquals(controlador.validar("nico","gomez",null,null),false);
	}
	
	@Test
	public void getSucursalesTest() {
		controlador= ControladorAltaProfesional.getInstance(sistema);
		HashMap<String,Integer> sucursales=controlador.getSucursales();
		
		assertEquals(sucursales.size(), 3);
		
		sucursales.remove("pelu2");
		assertEquals(sucursales.size(), 2);
		
	}
	
	@Test
	public void llenarComboTest() {
		controlador= ControladorAltaProfesional.getInstance(sistema);
		HashMap<String,Integer> sucursales=controlador.getSucursales();
		JComboBox combo= new JComboBox();
		
		controlador.llenarCombo(combo,sucursales);
		combo.removeItemAt(0);
		assertEquals(combo.getItemCount(),3);
	}
	
	
	
	

}
