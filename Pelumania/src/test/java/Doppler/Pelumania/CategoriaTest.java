package Doppler.Pelumania;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import dto.CategoriaMovimientoCajaDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

class CategoriaTest {
	CategoriaMovimientoCajaDTO cat;
	Sistema sistema;




	@Test
	void testDAOCategoria() {
		sistema=new Sistema(new DAOSQLFactory());
		
		cat= new CategoriaMovimientoCajaDTO(1,"Pelos","Activo","Bueno");
		sistema.insertarCategoriaMovimientoCaja(cat);
		
	
		assertEquals(sistema.obtenerCategoriasMovimientoCaja().get(0).getNombre(),"Pelos");
	
		
		for (int i=0;i<sistema.obtenerCategoriasMovimientoCaja().size();i++) {
			System.out.print(sistema.obtenerCategoriasMovimientoCaja().get(0).getNombre());
			sistema.deleteRealCategoria(sistema.obtenerCategoriasMovimientoCaja().get(i));
		}
		cat= new CategoriaMovimientoCajaDTO(1,"Pelos","Activo","Bueno");
		sistema.insertarCategoriaMovimientoCaja(cat);
		sistema.deleteRealCategoria(cat);
		assertEquals(sistema.obtenerCategoriasMovimientoCaja().size(),0);
		cat= new CategoriaMovimientoCajaDTO(1,"Pelos","Activo","Bueno");
		sistema.insertarCategoriaMovimientoCaja(cat);
		cat= new CategoriaMovimientoCajaDTO(1,"elos","Inctivo","Bueno");
		sistema.updateCategoriaMovimientoCaja(cat);
		assertEquals(sistema.obtenerCategoriasMovimientoCaja().get(0).getNombre(),"elos");
		assertEquals(sistema.obtenerCategoriasMovimientoCaja().get(0).getEstado(),"Inctivo");
		for (int i=0;i<sistema.obtenerCategoriasMovimientoCaja().size();i++) {
			System.out.print(sistema.obtenerCategoriasMovimientoCaja().get(0).getNombre());
			sistema.deleteRealCategoria(sistema.obtenerCategoriasMovimientoCaja().get(i));
		}
		cat= new CategoriaMovimientoCajaDTO(1,"Pelos","Activo","Bueno");
		sistema.insertarCategoriaMovimientoCaja(cat);
		sistema.deleteCategoriaMovimientoCaja(cat);
		assertEquals(sistema.obtenerCategoriasMovimientoCaja().get(0).getEstado(),"Inactivo");
		for (int i=0;i<sistema.obtenerCategoriasMovimientoCaja().size();i++) {
			System.out.print(sistema.obtenerCategoriasMovimientoCaja().get(0).getNombre());
			sistema.deleteRealCategoria(sistema.obtenerCategoriasMovimientoCaja().get(i));
		}
		cat= new CategoriaMovimientoCajaDTO(1,"Pelos","Activo","Bueno");
		sistema.insertarCategoriaMovimientoCaja(cat);
		assertEquals(sistema.getCategoriaMovimientoCajaById(1).getNombre(),"Pelos");
		assertEquals(sistema.getIdCategoriaMovimientoCajaByName("Pelos").getIdCategoria(),1);
	}
	

}
