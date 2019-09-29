package modelo;

import java.util.List;
import dto.ClienteDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ClienteDAO;


public class Sistema 
{
	private ClienteDAO cliente;	
	
	public Sistema(DAOAbstractFactory metodo_persistencia)
	{
		this.cliente = metodo_persistencia.createClienteDAO();
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
	
}
