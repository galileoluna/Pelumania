package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;

import org.junit.jupiter.api.Test;

import dto.HorarioDTO;

class HorarioDTOtest {
	HorarioDTO nuevoHorario;

	@Test
	void test() {
		Time entrada=new Time(Integer.parseInt("10"),Integer.parseInt("00"),00);
		Time salida=new Time(Integer.parseInt("18"),Integer.parseInt("00"),00);
		nuevoHorario=new HorarioDTO(5,"martes",entrada,salida,1);
		
		
		nuevoHorario.setDia("lunes");
		nuevoHorario.setDiasLaborales(5);
		nuevoHorario.setHoraEntrada(entrada);
		nuevoHorario.setHoraSalida(salida);
		nuevoHorario.setIdProfesional(2);
		
		assertEquals(nuevoHorario.getDia(),"lunes");
		assertEquals(nuevoHorario.getidDiasLaborales(),5);
		assertEquals(nuevoHorario.getHoraEntrada(),entrada);
		assertEquals(nuevoHorario.getHoraSalida(),salida);
		assertEquals(nuevoHorario.getIdProfesional(),2);
	}

}
