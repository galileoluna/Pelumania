package dto;

import java.math.BigDecimal;

public class ClienteDTO 
{
	
	private int idCliente;
	private String nombre;
	private String apellido;
	private String telefono;
	private String mail;
	private int puntos;
	private String estadoCliente;
	private BigDecimal deuda;

	public ClienteDTO(int idCliente, String nombre, String apellido, String telefono, String mail, int puntos, String estadoCliente, BigDecimal deuda)
	{
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.mail = mail;
		this.puntos = puntos;
		this.estadoCliente = estadoCliente;
		this.deuda = deuda;
			
		
	}
	
	public int getIdCliente() 
	{
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) 
	{
		this.idCliente = idCliente;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public String getEstadoCliente() {
		return estadoCliente;
	}

	public void setEstadoCliente(String estadoCliente) {
		this.estadoCliente = estadoCliente;
	}

	public BigDecimal getDeuda() {
		return deuda;
	}

	public void setDeuda(BigDecimal deuda) {
		this.deuda = deuda;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Override
	public String toString() {
		return this.nombre+" "+apellido;
	}
}
