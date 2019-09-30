package dto;

import java.math.BigDecimal;

public class ServicioDTO {
	private int idServicio;
	private String nombre;
	private BigDecimal precioLocal;
	private BigDecimal precioDolar;
	private int puntos;
	
	public ServicioDTO(
			int idservicio,
			String nombre,
			BigDecimal preciolocal,
			BigDecimal preciodolar,
			int puntos) {
		
		this.idServicio = idservicio;
		this.nombre = nombre;
		this.precioLocal = preciolocal;
		this.precioDolar = preciodolar;
		this.puntos = puntos;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecioLocal() {
		return precioLocal;
	}

	public void setPrecioLocal(BigDecimal precioLocal) {
		this.precioLocal = precioLocal;
	}

	public BigDecimal getPrecioDolar() {
		return precioDolar;
	}

	public void setPrecioDolar(BigDecimal precioDolar) {
		this.precioDolar = precioDolar;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}


}
