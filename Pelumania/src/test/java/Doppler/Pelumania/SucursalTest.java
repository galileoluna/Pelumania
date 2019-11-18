package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dto.SucursalDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

class SucursalTest {
	Sistema sistema;
	SucursalDTO sucursal;
	
	@Test
	void test() {
		sistema=new Sistema(new DAOSQLFactory());
		sucursal=new SucursalDTO(1,"san miguel","spain",1010,"activo");
		sistema.agregarSucursal(sucursal);
		sistema.editarSucursal(sucursal);
		sistema.borrarSucursal(sucursal);
		sistema.obtenerSucursales();
		sistema.getProfesionalByIdSucursal(1);
		
	}

}
