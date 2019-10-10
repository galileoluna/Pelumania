package dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.conexion.Conexion;
import persistencia.dao.mariadb.ProfesionalDAOSQL;

public class ProfesionalDTO {

	public int idProfesional;
	public String nombre;
	public String apellido;
	public int idSucursalOrigen;
	public int idSucursalTransferencia;
	public String estado;

	public ProfesionalDTO(int idProf,
			String nombre,
			String apellido,
			int idSucOr,
			int idSucTrans,
			String estado) {

		idProfesional=idProf;
		this.nombre=nombre;
		this.apellido=apellido;
		idSucursalOrigen=idSucOr;
		idSucursalTransferencia=idSucTrans;
		this.estado=estado;
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

	public String getEstado() {
		return estado;
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

	public void setEstado(String estado) {
		this.estado=estado;
	}

	public String getSucursal(int id) {
		ProfesionalDAOSQL prof= new ProfesionalDAOSQL();
		return prof.obtenerNombreSucursal(id);
		
	}

	@Override
	public String toString() {
		return this.getNombre()+" "+this.getApellido();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
