package dto;

public class CategoriaMovimientoCajaDTO {

	private int idCategoria;
	private String nombre;
	private String estado;
	private String tipoMovimiento;
	
	public CategoriaMovimientoCajaDTO(int idCategoria, String nombre, String estado, String tipoMovimiento) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.estado = estado;
		this.tipoMovimiento = tipoMovimiento;
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

}
