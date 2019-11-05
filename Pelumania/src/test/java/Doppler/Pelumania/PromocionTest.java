package Doppler.Pelumania;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import dto.PromocionDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

class PromocionTest {

	Sistema sistema;
	PromocionDTO promo;
	/*
	
	@Before
	public void before() {
		sistema=new Sistema(new DAOSQLFactory());
		
		for(int i=0; i < sistema.obtenerPrmociones().size();i++) {
		//	System.out.print(sistema.obtenerClientes().size());
		//	System.out.println(sistema.obtenerClientes().get(0).getNombre());
			sistema.deleteRealPromocion(sistema.obtenerPrmociones().get(0));

		}
	}
	@After
	public void after() {
		for(int i=0; i<sistema.obtenerClientes().size();i++) {
		//	System.out.print(sistema.obtenerClientes().size());
		//	System.out.println(sistema.obtenerClientes().get(0).getNombre());
			sistema.deleteRealPromocion(sistema.obtenerPrmociones().get(0));
		}
	}*/
	
	
	@Test
	void PromocionDAO() {
		sistema=new Sistema(new DAOSQLFactory());
		for(int i=0;i<sistema.obtenerPrmociones().size();i++) {
			sistema.deleteRealPromocion(sistema.obtenerPrmociones().get(0));
		}
	
		Date fechaIn=new Date(0, 0, 0);
		Date fechaFin=new Date(1, 1, 1);
		promo=new PromocionDTO(1,"Promo 2x1",fechaIn,fechaFin,0.5,500,"Vigente");
		sistema.insertarPromocion(promo);
		sistema.editarPromocion(promo);
		sistema.borrarPromocion(promo);
		assertEquals(sistema.obtenerPrmociones().get(0).getEstado(),"Inactivo");
	
		sistema.deleteRealPromocion(promo);
		assertEquals(sistema.obtenerPrmociones().size(),4);
	
	}
	

}

