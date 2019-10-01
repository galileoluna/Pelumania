package dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistencia.conexion.Conexion;

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

	public String getSucursal(int id) {
		return getStringSucursal(id);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getStringSucursal(int id) {
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		String sucursal;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("Select NombreSucursal FROM sucursal WHERE idSucursal = ?");
	
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()){
				sucursal = resultSet.getString("NombreSucursal");
				return sucursal;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
