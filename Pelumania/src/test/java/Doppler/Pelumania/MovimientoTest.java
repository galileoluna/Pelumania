package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;

import dto.MovimientoCajaDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

class MovimientoTest {
	
	MovimientoCajaDTO movimiento;
	Sistema sistema;

	@Test
	void test() {
		sistema=new Sistema(new DAOSQLFactory());
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		Timestamp timestamp = Timestamp.from(Instant.now());
		movimiento=new MovimientoCajaDTO(1,1,1,timestamp,"Compra Shampo",1,peso,dolar,1,1,1,1);
		
		
	}

}
