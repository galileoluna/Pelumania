package dto;

public class CategoriaMovimientoCajaDTO {

	private int idCategoria;
	private String nombre;
	private String estado;
	
	public CategoriaMovimientoCajaDTO(int idCategoria, String nombre, String estado) {
		super();
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.estado = estado;
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

	
}
