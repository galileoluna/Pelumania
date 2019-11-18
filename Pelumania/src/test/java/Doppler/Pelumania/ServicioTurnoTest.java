package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import dto.ServicioTurnoDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

class ServicioTurnoTest {
	ServicioTurnoDTO servicio;
	Sistema sistema;
	@Test
	void test() {
		sistema=new Sistema(new DAOSQLFactory());
		LocalTime horaInicio=LocalTime.now();
		 LocalTime horaFin=LocalTime.now();
		servicio= new ServicioTurnoDTO(1,1,1,horaInicio,horaFin);
		
		sistema.insertServicioTurno(servicio);
		sistema.deleteServicioTurno(servicio);
		sistema.editarServicio(servicio);
	}

}
