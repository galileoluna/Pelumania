package dto;

import java.math.BigDecimal;

import persistencia.dao.mariadb.ProductoDAOSQL;

public class ProductoDTO {
	private int idProducto;
	private String nombre;
	private BigDecimal precioLocal;
	private BigDecimal precioDolar;
	private int puntos;
	private String estado;
	
	public ProductoDTO(int id, String nombre, BigDecimal precioLocal, BigDecimal precioDolar, int puntos,
			String estado) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.precioLocal = precioLocal;
		this.precioDolar = precioDolar;
		this.puntos = puntos;
		this.estado = estado;
	}


	
	public int getIdProducto() {
		ProductoDAOSQL pro= new ProductoDAOSQL();
		return pro.obtenerID(nombre);
	}

	public void setidProducto(int idProducto) {
		this.idProducto = idProducto;
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
