package modelo;

import java.util.List;
import dto.ClienteDTO;
import dto.ServicioDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ServicioDAO;
import persistencia.dao.interfaz.ClienteDAO;


public class Sistema 
{
	private ClienteDAO cliente;
	private ServicioDAO servicio;
	
	public Sistema(DAOAbstractFactory metodo_persistencia)
	{
		this.cliente = metodo_persistencia.createClienteDAO();
		this.servicio = metodo_persistencia.createServicioDAO();
	}
	
	public void agregarCliente(ClienteDTO nuevoCliente)
	{
		this.cliente.insert(nuevoCliente);
	}

	public void borrarCliente(ClienteDTO cliente_a_eliminar) 
	{
		this.cliente.delete(cliente_a_eliminar);
	}
	
	public List<ClienteDTO> obtenerClientes()
	{
		return this.cliente.readAll();		
	}
	
	public void agregarServicio(ServicioDTO nuevoServicio) {
		this.servicio.insert(nuevoServicio);
	}
	
	public void borrarServicio(ServicioDTO servicio_a_eliminar) {
		this.servicio.delete(servicio_a_eliminar);
	}
	
	public List<ServicioDTO> obtenerServicios(){
		return this.servicio.readAll();
	}
	
}
