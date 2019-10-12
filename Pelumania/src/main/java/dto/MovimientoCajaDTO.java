package dto;

import java.math.BigDecimal;
import java.time.Instant;

//Se modela con una misma entidad al ingreso y egreso
//dependiendo de los campos con los que ingrese sera uno o el otro
//el egreso va a atener varios en nulo y viceversa 

//idCaja --> numero de transaccion 
// tipoMovimiento --> egreso,ingreso
// categoria -->      producto, servicio,viaticos, sueldo 
// tipoCambio -->	  puntos, efectivo, tarj 
// descripcion --> (solo para egreso )

public class MovimientoCajaDTO {

	private int idCaja;
	private int idSucursal;
	private String categoria;
	private Instant fecha;
	private String descripcion;
	private String tipoMovimiento;
	private String tipoCambio;
	private int idPromocion;
	private BigDecimal precioLocal;
	private BigDecimal precioDolar;
	private int idProfesional;
	private int idCita;
	private int idCliente;
	
	
	//constructor para un egreso
	public MovimientoCajaDTO(int idCaja, int idSucursal,Instant fecha, String tipoMovimiento, String categoria,
			String tipoCambio, BigDecimal precioLocal, BigDecimal precioDolar, String descripcion) {
		
		this.idCaja = idCaja;
		this.idSucursal = idSucursal;
		this.categoria = categoria;
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.tipoCambio = tipoCambio;
		this.precioLocal = precioLocal;
		this.precioDolar = precioDolar;
		this.descripcion = descripcion;
	}

//constructor para un ingreso
	
	public MovimientoCajaDTO(int idCaja, int idSucursal, String categoria, Instant fecha, String tipoMovimiento,
			String tipoCambio, int idPromocion, BigDecimal precioLocal, BigDecimal precioDolar, int idProfesional,
			int idCita, int idCliente) {
		super();
		this.idCaja = idCaja;
		this.idSucursal = idSucursal;
		this.categoria = categoria;
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.tipoCambio = tipoCambio;
		this.idPromocion = idPromocion;
		this.precioLocal = precioLocal;
		this.precioDolar = precioDolar;
		this.idProfesional = idProfesional;
		this.idCita = idCita;
		this.idCliente = idCliente;
	}



	public int getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(int idMovimiento) {
		this.idCaja = idMovimiento;
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

	public Instant getFecha() {
		return fecha;
	}

	public void setFecha(Instant fecha) {
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
