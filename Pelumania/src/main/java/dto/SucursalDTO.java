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

	@Override
	public String toString() {
		return this.nombreSucursal;
	}
	
//	@Override
//	public boolean equals(Object otro) {
//		return this.getIdSucursal() == ((SucursalDTO)otro).getIdSucursal();
////			&& this.getNombreSucursal().equals(((SucursalDTO)otro).getNombreSucursal());
//	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((estadoSucursal == null) ? 0 : estadoSucursal.hashCode());
		result = prime * result + idSucursal;
		result = prime * result + ((nombreSucursal == null) ? 0 : nombreSucursal.hashCode());
		result = prime * result + numero;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SucursalDTO other = (SucursalDTO) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (estadoSucursal == null) {
			if (other.estadoSucursal != null)
				return false;
		} else if (!estadoSucursal.equals(other.estadoSucursal))
			return false;
		if (idSucursal != other.idSucursal)
			return false;
		if (nombreSucursal == null) {
			if (other.nombreSucursal != null)
				return false;
		} else if (!nombreSucursal.equals(other.nombreSucursal))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}
	
}
