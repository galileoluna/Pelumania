package dto;

public class ServicioTurnoDTO {
	private Integer idCita;
	private Integer idServicio;
	private Integer idProfesional; 
	
	
	public ServicioTurnoDTO(Integer idcita, Integer idservicio, Integer idprofesional) {
		this.idCita = idcita;
		this.idServicio = idservicio;
		this.idProfesional = idprofesional; 
	}
	public ServicioTurnoDTO(Integer idservicio, Integer idprofesional) {
		this.idCita = null; 
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
	
	
}

