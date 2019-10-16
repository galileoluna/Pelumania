package dto;

import java.time.LocalTime;

public class ServicioTurnoDTO {
	private Integer idCita;
	private Integer idServicio;
	private Integer idProfesional; 
	private LocalTime horaInicio;
	private LocalTime horaFin;
	
	
	public ServicioTurnoDTO(Integer idcita, Integer idservicio, Integer idprofesional, 
			LocalTime horainicio, LocalTime horafin) {
		this.idCita = idcita;
		this.idServicio = idservicio;
		this.idProfesional = idprofesional; 
		this.horaInicio = horainicio;
		this.horaFin = horafin;
	}
	public ServicioTurnoDTO(Integer idservicio, Integer idprofesional) {
		this.idCita = -1; 
		this.idServicio = idservicio;
		this.idProfesional = idprofesional;
	}
	public Integer getIdCita() {
		return idCita;
	}
	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}
	public Integer getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}
	public Integer getIdProfesional() {
		return idProfesional;
	}
	public void setIdProfesional(Integer idProfesional) {
		this.idProfesional = idProfesional;
	}
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public LocalTime getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
	
}

