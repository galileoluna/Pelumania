package Doppler.Pelumania;


import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.ProfesionalDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

public class ProfesionalTest {
	Sistema sistema;
	ProfesionalDTO profesional;
	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
		
		for(int i=0; i<sistema.obtenerProfesional().size();i++) {
			System.out.print(sistema.obtenerProfesional().size());
			System.out.println(sistema.obtenerProfesional().get(0).getNombre());
			sistema.borrarProfesional(sistema.obtenerProfesional().get(0));

		}
	}
	@After
	 public void after() {
		for(int i=0; i<sistema.obtenerProfesional().size();i++) {
			System.out.print(sistema.obtenerProfesional().size());
			System.out.println(sistema.obtenerProfesional().get(0).getNombre());
			sistema.borrarProfesional(sistema.obtenerProfesional().get(0));

		}
	}

	@Test
	public void testValidarNombreProfesional() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(1, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	assertEquals(sistema.obtenerProfesional().get(0).getNombre(),"Flavio");
	}
	@Test
	public void testValidarApellidoProfesional() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(2, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	assertEquals(sistema.obtenerProfesional().get(0).getApellido(),"Mendoza");
	}
	@Test
	public void testValidarSucursalOrigen() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(3, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	assertEquals(sistema.obtenerProfesional().get(0).getIdSucursalOrigen(),1);
	}
	@Test
	public void testValidarSucursalDestino() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(5, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	assertEquals(sistema.obtenerProfesional().get(0).getIdSucursalTransferencia(),2);
	}
	@Test
	public void testValidarEstado() {
	int cantProfExistentes=sistema.obtenerProfesional().size();	
	ProfesionalDTO nuevo=	new ProfesionalDTO(12, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);

	assertEquals(sistema.obtenerProfesional().get(cantProfExistentes-1).getEstado(),"ACTIVO");
	}
	@Test
	public void testEliminarProfesional() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(11, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	ProfesionalDTO nuevo2=	new ProfesionalDTO(20, "Marcelo", "Polino" ,2, 3, "ACTIVO" );
	sistema.agregarProfesional(nuevo2);
	sistema.borrarProfesional(nuevo2);
	assertEquals(sistema.obtenerProfesional().size(),1);
	}
	
	@Test
	public void testValidarEstadoBorrado() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(8, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	sistema.borrarProfesional(nuevo);
	assertEquals(sistema.obtenerProfesional().get(0).getEstado(),"Inactivo");
	}
	
	@Test
	public void testValidarUpdate() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(9, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	ProfesionalDTO nuevo1=	new ProfesionalDTO(17, "Flavio", "Mendoza" ,1, 3, "ENFERMO" );
	sistema.actualizarProfesional(nuevo1);
	assertEquals(sistema.obtenerProfesional().get(0).getEstado(),"ENFERMO");
	assertEquals(sistema.obtenerProfesional().get(0).getIdSucursalTransferencia(),3);
	}

	@Test
	public void testRedOne() {
	ProfesionalDTO nuevo=new ProfesionalDTO(100, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	ProfesionalDTO nuevo1=new ProfesionalDTO(200, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	sistema.agregarProfesional(nuevo1);
	int id=nuevo1.getIdProfesional();
	sistema.editarProfesional(id);
	assertEquals(sistema.obtenerProfesional().get(0).getIdProfesional(),nuevo1);
	}

	
	
	@Test
	public void testValidarUpdateporId() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(101, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);

	}
	@Test
	public void test() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(102, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	sistema.borrarProfesional(nuevo);
	assertEquals(sistema.obtenerProfesional().get(0).getEstado(),"Inactivo");
	}
	
	@Test
	public void setEstado() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(103, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	nuevo.setIdProfesional(2);
	assertEquals(nuevo.getIdProfesional(),2);
	}
	
	@Test
	public void setNombre() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(105, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	nuevo.setNombre("Franco");
	assertEquals(nuevo.getNombre(),"Franco");
	}
	@Test
	public void setApeliido() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(106, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	nuevo.setApellido("Franco");
	assertEquals(nuevo.getApellido(),"Franco");
	}
	
	
	@Test
	public void setESTADO() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(107, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	nuevo.setEstado("Franco");
	assertEquals(nuevo.getEstado(),"Franco");
	}
	
	@Test
	public void setIdSucursal() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(110, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	nuevo.setIdSucursalTransferencia(3);

	assertEquals(nuevo.getIdSucursalTransferencia(),3);
	nuevo.setIdSucursalOrigen(4);
	assertEquals(nuevo.getIdSucursalOrigen(),4);
	}
	
	
	//Size
	@Test
	public void testValidarSize() {
		
	int cantProfExistentes=sistema.obtenerProfesional().size();	
	ProfesionalDTO nuevo=	new ProfesionalDTO(209, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	ProfesionalDTO nuevo1=	new ProfesionalDTO(303, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	sistema.agregarProfesional(nuevo1);
	assertEquals(sistema.obtenerProfesional().size(),2+cantProfExistentes);
	}
	
	
	//Excepciones
	
	
	@Test(expected = SQLException.class)
	public void errorMismoID() {
		
	ProfesionalDTO nuevo=	new ProfesionalDTO(400, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	ProfesionalDTO nuevo1=	new ProfesionalDTO(350, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	sistema.agregarProfesional(nuevo1);
	}
	@Test(expected = SQLException.class)
	public void malAgregado() {
	
	ProfesionalDTO nuevo1 = new ProfesionalDTO(-1, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo1);
	
	}
	@Test(expected = SQLException.class)
	public void male() {
	
	ProfesionalDTO nuevo1 = new ProfesionalDTO(1, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo1);
	sistema.borrarProfesional(nuevo1);
	sistema.editarProfesional(nuevo1.idProfesional);
	
	}
	@Test(expected = SQLException.class)
	public void updateInvalido() {
	ProfesionalDTO nuevo=	new ProfesionalDTO(1, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );	
	ProfesionalDTO nuevo1 = new ProfesionalDTO(-1, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo);
	sistema.actualizarProfesional(nuevo1);
	
	}
	@Test(expected = SQLException.class)
	public void leerVacio() {
	sistema.obtenerProfesional();
	}
	@Test(expected = SQLException.class)
	public void eliminarVacio() {
	ProfesionalDTO nuevo1 = new ProfesionalDTO(-1, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo1);
	sistema.borrarProfesional(nuevo1);
	sistema.borrarProfesional(nuevo1);
	}
	@Test(expected = SQLException.class)
	public void eliminardeleteRealProfesional() {
	ProfesionalDTO nuevo1 = new ProfesionalDTO(1, "Flavio", "Mendoza" ,1, 2, "ACTIVO" );
	sistema.agregarProfesional(nuevo1);
	sistema.borrarProfesional(nuevo1);
	sistema.borrarProfesional(nuevo1);
	}
	@Test(expected = SQLException.class)
	public void errorEditar() {
	sistema.editarProfesional(-12);
	System.out.println(sistema.editarProfesional(-12));
	}
	@Test(expected = SQLException.class)
	public void errorEditarCu() {
	sistema.editarProfesional(-12);
	}
	
}