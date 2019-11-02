package dto;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import modelo.Sistema;
import persistencia.dao.mariadb.SucursalDAOSQL;
import persistencia.dao.mariadb.UsuarioDAOSQL;

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

	public String getRol() {
		UsuarioDAOSQL usuarios= new UsuarioDAOSQL();
		HashMap<String, Integer> roles= usuarios.readRol();
		int idPosta= getIdRol();
		for (Entry<String, Integer> entry : roles.entrySet()) {
		   if(entry.getValue()== idPosta) {
			   return entry.getKey();
		   }
		}
		return "No se encontro el id";
	}
	
	public String getSucursal() {
		SucursalDAOSQL sucursal= new SucursalDAOSQL();
		List<SucursalDTO> sucursales= sucursal.readAll();
		int idPosta= getIdSucursal();
		for(SucursalDTO s: sucursales) {
			if(s.getIdSucursal() == idPosta) {
				return s.getNombreSucursal();
			}
		}
		
		return "No se encontro el id";
	}

}
