package dto;

import java.sql.Time;

public class HorarioDTO {
	public int idDiasLaborales;
	public String dia;
	public Time HoraEntrada;
	public Time HoraSalida;
	public int idProfesional;
	
	public HorarioDTO(int idDiasLaborales, String dia, Time HoraEntrada, Time HoraSalida, int idProfesional) {
		this.idDiasLaborales=idDiasLaborales;
		this.dia=dia;
		this.HoraEntrada=HoraEntrada;
		this.HoraSalida=HoraSalida;
		this.idProfesional=idProfesional;
	}
	
	public int getidDiasLaborales() {
		return this.idDiasLaborales;
	}
	
	public String getDia() {
		return this.dia;
	}
	
	public Time getHoraEntrada() {
		return this.HoraEntrada;
	}
	
	public Time getHoraSalida() {
		return this.HoraSalida;
	}
	
	public int getIdProfesional() {
		return this.idProfesional;
	}
	
	public void setDiasLaborales(int id) {
		this.idDiasLaborales=id;
	}
	
	public void setDia(String dia) {
		this.dia=dia;
	}
	
	public void setHoraEntrada(Time hora) {
		this.HoraEntrada=hora;
	}
	
	public void setHoraSalida(Time hora) {
		this.HoraSalida=hora;
	}
	
	public void setIdProfesional(int id) {
		this.idProfesional=id;
	}
}
