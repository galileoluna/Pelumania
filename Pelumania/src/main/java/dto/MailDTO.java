package dto;

import java.time.LocalDate;

public class MailDTO {

	private LocalDate fecha;
	private int idMail;
	private int idCita;
	
	public MailDTO(int idMail, int idCita, LocalDate fecha) {
		this.fecha = fecha;
		this.idMail = idMail;
		this.idCita = idCita;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}

	public int getIdMail() {
		return idMail;
	}

	public int getIdCita() {
		return idCita;
	}

}
