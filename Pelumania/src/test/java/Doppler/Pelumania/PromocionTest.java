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
	
		Date fechaIn=new Date(0, 0, 0);
		Date fechaFin=new Date(1, 1, 1);
		promo=new PromocionDTO(1,"Promo 2x1",fechaIn,fechaFin,0.5,500,"Vigente");
		sistema.insertarPromocion(promo);
		assertEquals(sistema.obtenerPrmociones().get(0).getDescripcion(),"Promo 2x1");
		for(int i=0; i < sistema.obtenerPrmociones().size();i++) {
		//	System.out.print(sistema.obtenerPrmociones().size());
			System.out.println(sistema.obtenerPrmociones().get(i).getDescripcion());
			
			sistema.deleteRealPromocion(sistema.obtenerPrmociones().get(i));
		}
		sistema.deleteRealPromocion(promo);
		assertEquals(sistema.obtenerPrmociones().size(),0);
		
		promo=new PromocionDTO(1,"Promo 2x1",fechaIn,fechaFin,0.5,500,"Vigente");
		sistema.insertarPromocion(promo);
		promo=new PromocionDTO(1,"Promo 3x1",fechaIn,fechaFin,0.5,500,"Vigente");
		sistema.editarPromocion(promo);
		assertEquals(sistema.obtenerPrmociones().get(0).getDescripcion(),"Promo 3x1");
	}
	

}

