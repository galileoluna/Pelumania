package dto;

public class UsuarioDTO {
	int idUsuario;
	String nombre;
	String apellido;
	String nombreUsuario;
	String contrasenia;
	String mail;
	String estado;
	int idRol;
	int idSucursal;
	
	public UsuarioDTO (int id,String nombre, String apellido, String nombreUsuario, String contra, String email, String estado, int rol, int sucursal ) {
		this.idUsuario=id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.nombreUsuario=nombreUsuario;
		this.contrasenia=contra;
		this.mail=email;
		this.estado=estado;
		this.idRol=rol;
		this.idSucursal=sucursal;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public int getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

}
