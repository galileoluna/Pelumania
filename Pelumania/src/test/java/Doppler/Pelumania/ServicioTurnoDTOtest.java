package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import dto.ServicioTurnoDTO;

class ServicioTurnoDTOtest {
	ServicioTurnoDTO servicio;
	ServicioTurnoDTO servicio2;
	ServicioTurnoDTO servicio3;

	@Test
	void test() {
		LocalTime horaInicio=LocalTime.now();
		 LocalTime horaFin=LocalTime.now();
		servicio= new ServicioTurnoDTO(1,1,1,horaInicio,horaFin);
		servicio2= new ServicioTurnoDTO(1,1,1,horaInicio,horaFin);
		servicio3= new ServicioTurnoDTO(1,1,1,horaInicio,horaFin);
		servicio.setHoraFin(horaFin);
		servicio.setHoraInicio(horaInicio);
		servicio.setIdCita(2);
		servicio.setIdProfesional(2);
		servicio.setIdServicio(2);
		assertEquals(servicio.getHoraFin(),horaFin);
		assertEquals(servicio.getHoraInicio(),horaInicio);
		assertEquals(servicio.getIdCita(),2);
		assertEquals(servicio.getIdProfesional(),2);
		assertEquals(servicio.getIdServicio(),2);
		assertFalse(servicio.equals(servicio2));
		assertTrue(servicio2.equals(servicio3));
		assertEquals(servicio.hashCode(),1023);
	
	}

}
