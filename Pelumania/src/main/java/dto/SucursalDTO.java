package dto;

public class SucursalDTO {
	int idSucursal;
	String nombreSucursal;
	String direccion;
	int numero;
	String estadoSucursal;
	
	public SucursalDTO(int idSucursal, String nombreSucursal, String direccion, int numero, String estadoSucursal) {
		super();
		this.idSucursal = idSucursal;
		this.nombreSucursal = nombreSucursal;
		this.direccion = direccion;
		this.numero = numero;
		this.estadoSucursal = estadoSucursal;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getNombreSucursal() {
		return nombreSucursal;
	}

	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getEstadoSucursal() {
		return estadoSucursal;
	}

	public void setEstadoSucursal(String estadoSucursal) {
		this.estadoSucursal = estadoSucursal;
	}

	
}
