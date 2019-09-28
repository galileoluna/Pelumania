package dto;

public class ProfesionalDTO {
	
	public int idProfesional;
	public String nombre;
	public String apellido;
	public int idSucursalOrigen;
	public int idSucursalTransferencia;
	
	public ProfesionalDTO(int idProf, 
			String nombre, 
			String apellido, 
			int idSucOr,
			int idSucTrans) {
		
		idProfesional=idProf;
		this.nombre=nombre;
		this.apellido=apellido;
		idSucursalOrigen=idSucOr;
		idSucursalTransferencia=idSucTrans;
	}
	

	public int getIdProfesional() {
		return idProfesional;
	}


	public void setIdProfesional(int idProfesional) {
		this.idProfesional = idProfesional;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public int getIdSucursalOrigen() {
		return idSucursalOrigen;
	}


	public void setIdSucursalOrigen(int idSucursalOrigen) {
		this.idSucursalOrigen = idSucursalOrigen;
	}


	public int getIdSucursalTransferencia() {
		return idSucursalTransferencia;
	}


	public void setIdSucursalTransferencia(int idSucursalTransferencia) {
		this.idSucursalTransferencia = idSucursalTransferencia;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
