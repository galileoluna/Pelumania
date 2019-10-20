package Doppler.Pelumania;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import dto.CitaDTO;
import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;

class CitaTest {
	CitaDTO cita;
	Sistema sistema;

	@Test
	void test() {
		sistema=new Sistema(new DAOSQLFactory());
	
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,"juan","montero","activa",peso,dolar,inicio,fin,fecha,1);
		
		
		sistema.agregarCita(cita);
		assertEquals(sistema.obtenerCitas().get(0).getApellido(),"montero");
		assertEquals(sistema.obtenerCitas().get(0).getNombre(),"juan");
		assertEquals(sistema.obtenerCitas().size(),1);
		
		
		sistema.deleteReal(cita);
		
		assertEquals(sistema.obtenerCitas().size(),0);
		
		cita=new CitaDTO(1,2,"juan","montero","activa",peso,dolar,inicio,fin,fecha,1);
		
		
		sistema.agregarCitaSinCliente(cita);
		
		assertEquals(sistema.obtenerCitas().get(0).getNombre(),"juan");
		
		sistema.cancelarCita(cita);
		
		
		for (int i=0;i<sistema.obtenerCategoriasMovimientoCaja().size();i++) {
			System.out.print(sistema.obtenerCitas().get(i).getEstado());
		}
		System.out.print(sistema.obtenerCitas().get(0).getEstado());
		assertEquals(sistema.obtenerCitas().get(0).getEstado(),"Cancelada");
		
		
		sistema.finalizarCita(cita);
		assertEquals(sistema.obtenerCitas().get(0).getEstado(),"Finalizada");
		
		assertEquals(sistema.obtenerCitasActivas().size(),0);
		assertEquals(sistema.getCitasPorDia("20190901").size(),0);
		
		cita.setApellido("Nelis");
		sistema.editarCita(cita);
		assertEquals(sistema.obtenerCitas().get(0).getApellido(),"Nelis");
		
		
		System.out.println(sistema.getCitaMax().getIdCita());
		
	}
	

}
