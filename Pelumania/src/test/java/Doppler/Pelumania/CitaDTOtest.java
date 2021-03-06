package Doppler.Pelumania;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import dto.CitaDTO;

class CitaDTOtest {
	CitaDTO cita;
	
	@Test
	public void testValidarCreacion() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		cita.setTelefono("1234");
		cita.setMail("a@a.co");
		cita.setHoraInicio(inicio);
		cita.setHoraFin(fin);
		cita.setIdProfesional(2);
		assertEquals(cita.getEstado(),"activa");
		assertEquals(cita.getTelefono(),"1234");
		assertEquals(cita.getMail(),"a@a.co");
		assertEquals(cita.getHoraInicio(),inicio);
		assertEquals(cita.getHoraFin(),fin);
		
		
		
	}
	
	@Test
	public void testValidarVerNombre() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		
		assertEquals(cita.getNombre(),"juan");
	}
	
	@Test
	public void testValidarVerApellido() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		
		assertEquals(cita.getApellido(),"montero");
		
	}
	
	@Test
	public void testValidarVerId() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		
		assertEquals(cita.getIdCita(),1);
	}
	
	@Test
	public void testValidarVerIdUsuario() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		
		assertEquals(cita.getIdUsuario(),2);
	}
	
	@Test
	public void testValidarVerIdSucursal() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		
		assertEquals(cita.getIdSucursal(),1);
	}
	
	@Test
	public void testValidarSetId() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		cita.setIdCita(10);
		
		assertEquals(cita.getIdCita(),10);
	}
	
	@Test
	public void testValidarSetNombre() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		cita.setNombre("pedro");
		
		assertEquals(cita.getNombre(),"pedro");
	}
	
	@Test
	public void testValidarSetApellido() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		cita.setApellido("mamani");
		
		assertEquals(cita.getApellido(),"mamani");
	}
	
	@Test
	public void testValidarSetIdUsuario() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		cita.setIdUsuario(10);
		
		assertEquals(cita.getIdUsuario(),10);
	}
	
	@Test
	public void testValidarSetIdSucursal() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		cita.setIdSucursal(5);
		
		assertEquals(cita.getIdSucursal(),5);
	}
	
	@Test
	public void testValidarGetIdCliente() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		cita.setIdSucursal(5);
		
		assertEquals(cita.getIdCliente(),3);
	}
	
	@Test
	public void testValidarSetIdCliente() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);

		cita.setIdCliente(10);
		assertEquals(cita.getIdCliente(),10);
	}
    @Test
	public void testValidarContructor2() {
		Time tiempo=new Time(1,1,1);
		Date dia=new Date(1,2,1);
				
		cita=new CitaDTO(1,"juan","roberto",tiempo,dia,"1","activo");
				
		assertEquals(cita.getHoraTurno().compareTo(tiempo),0);
	}
	
	@Test
	public void testValidarGetEstado() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		
		assertEquals(cita.getEstado(),"activa");
	}

	@Test
	public void testValidarSetEstado() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);cita.setEstado("inactivo");
		
		assertEquals(cita.getEstado(),"inactivo");
	}
	
	@Test
	public void testValidarSetPrf() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		cita.setEstado("inactivo");
		
		assertEquals(cita.getEstado(),"inactivo");
	}	
	
	@Test
	public void testValidarVerPeso() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		
		assertEquals(cita.getPrecioLocal().compareTo(peso),0);
	}
	
	@Test
	public void testValidarVerDolar() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		
		assertEquals(cita.getPrecioDolar().compareTo(dolar),0);
	}
	
	@Test
	public void testValidarSetPeso() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		cita.setPrecioLocal(new BigDecimal(50));
		
		assertEquals(cita.getPrecioLocal().compareTo(new BigDecimal(50)),0);
	}
	
	@Test
	public void testValidarSetDolar() {
		BigDecimal peso=new BigDecimal(1);
		BigDecimal dolar=new BigDecimal(60);
		LocalTime inicio=LocalTime.now();
		LocalTime fin=LocalTime.now();
		LocalDate fecha=LocalDate.now();
		cita=new CitaDTO(1,2,3,"juan","montero","12345","a@a.com", "activa", peso,dolar,inicio,fin,fecha,1, 0);
		cita.setPrecioDolar(new BigDecimal(50));
		
		assertEquals(cita.getPrecioDolar().compareTo(new BigDecimal(50)),0);
	}
	
}

