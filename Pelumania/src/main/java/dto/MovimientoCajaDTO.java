package dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimientoCajaDTO {
	
	private int idCaja;
	private int idSucursal;
	private String categoria;
	private LocalDate fecha;
	private String descripcion;
	private String tipoMovimiento;
	private String tipoCambio;
	private int idPromocion;
	private BigDecimal precioLocal;
	private BigDecimal precioDolar;
	private int idProfesional;
	private int idCita;
	private int idCliente;
	
	public MovimientoCajaDTO(int idCaja, int idSucursal, String categoria, LocalDate fecha, String tipoMovimiento,
			String tipoCambio, BigDecimal precioLocal, BigDecimal precioDolar, int idProfesional, int idCita,
			int idCliente) {
		super();
		this.idCaja = idCaja;
		this.idSucursal = idSucursal;
		this.categoria = categoria;
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.tipoCambio = tipoCambio;
		this.precioLocal = precioLocal;
		this.precioDolar = precioDolar;
		this.idProfesional = idProfesional;
		this.idCita = idCita;
		this.idCliente = idCliente;
	}

	public int getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(int idCaja) {
		this.idCaja = idCaja;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
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

	public int getIdProfesional() {
		return idProfesional;
	}

	public void setIdProfesional(int idProfesional) {
		this.idProfesional = idProfesional;
	}

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(int idPromocion) {
		this.idPromocion = idPromocion;
	}
}
