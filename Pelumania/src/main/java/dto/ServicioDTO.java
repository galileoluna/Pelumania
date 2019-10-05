package dto;

import java.math.BigDecimal;
import java.time.LocalTime;

public class ServicioDTO {
	private int idServicio;
	private String nombre;
	private BigDecimal precioLocal;
	private BigDecimal precioDolar;
	private LocalTime duracion;
	private int puntos;
	private String estado;


	public ServicioDTO(
			int idservicio,
			String nombre,
			BigDecimal preciolocal,
			BigDecimal preciodolar,
			LocalTime duracion,
			int puntos,
			String estado) {


		this.idServicio = idservicio;
		this.nombre = nombre;
		this.precioLocal = preciolocal;
		this.precioDolar = preciodolar;
		this.duracion = duracion;
		this.puntos = puntos;
		this.estado = estado;
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

	public LocalTime getDuracion() {
		return duracion;
	}

	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

}
