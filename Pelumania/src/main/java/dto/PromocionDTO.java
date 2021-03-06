package dto;

import java.sql.Date;

public class PromocionDTO {
	public int idPromocion;
	public String descripcion;
	public Date fechaInicio;
	public Date fechaFin;
	public Double descuento;
	public Integer puntos;
	public String estado;
	
	public PromocionDTO(int id, String descripcion, Date fechaIn, Date fechaFin, Double desc ,Integer puntos,String estado) {
		if(validar(descripcion,fechaIn,fechaFin,desc,puntos,estado)) {
			this.idPromocion=id;
			this.descripcion=descripcion;
			this.fechaInicio=fechaIn;
			this.fechaFin=fechaFin;
			this.descuento=desc;
			this.puntos=puntos;
			this.estado=estado;
		}
		
	}
	
	private boolean validar( String descripcion, Date fechaIn, Date fechaFin, Double desc ,Integer puntos,String estado) {
		if(descripcion==null || descripcion.equals("") || fechaIn==null || fechaFin==null || estado.equals("") || (desc==null && puntos==null)) {
			return false;
		}
		return true;
	}

	public int getIdPromocion() {
		return this.idPromocion;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public Date getFechaInicio() {
		return this.fechaInicio;
	}
	
	public Date getFechaFin() {
		return this.fechaFin;
	}
	
	public Double getDescuento() {
		return this.descuento;
	}
	
	public Integer getPuntos() {
		return this.puntos;
	}
	
	public void setIdPromocion(int id) {
		this.idPromocion=id;
	}
	
	public void setDescripcion(String desc) {
		this.descripcion=desc;
	}
	
	public void setFechaInicio(Date fecha) {
		this.fechaInicio=fecha;
	}
	
	public void setFechaFin (Date fecha) {
		this.fechaFin=fecha;
	}
	
	public void setDescuento(Double descuento) {
		this.descuento=descuento;
	}
	
	public void setPuntos(int puntos) {
		this.puntos=puntos;
	}

	public String getEstado() {
		return this.estado;
	}
	
	public void setEstado(String estado) {
		this.estado=estado;
	}
}
