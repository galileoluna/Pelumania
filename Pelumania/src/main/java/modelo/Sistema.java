package modelo;

import java.util.List;
import dto.ClienteDTO;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.ProfesionalDAO;
import persistencia.dao.interfaz.ServicioDAO;
import persistencia.dao.interfaz.ClienteDAO;


public class Sistema 
{
	private ClienteDAO cliente;
	private ServicioDAO servicio;
	private ProfesionalDAO profesional;
	
	public Sistema(DAOAbstractFactory metodo_persistencia)
	{
		this.cliente = metodo_persistencia.createClienteDAO();
		this.servicio = metodo_persistencia.createServicioDAO();
		this.profesional=metodo_persistencia.createProfesionalDAO();
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
	
	public void agregarProfesional(ProfesionalDTO nuevoProfesional) {
		this.profesional.insert(nuevoProfesional);
	}
	
	public void borrarProfesional(ProfesionalDTO profesional_a_eliminar) {
		this.profesional.delete(profesional_a_eliminar);
	}
	
	public List<ProfesionalDTO> obtenerProfesional(){
		return this.profesional.readAll();
	}
	
	public List<ProfesionalDTO> editarProfesional(int id){
		return this.profesional.readOne(id);
	}
	
	public void actualizarProfesional(ProfesionalDTO profesional_a_editar) {
		System.out.println("pase en sistema");
		this.profesional.update(profesional_a_editar);
	}
	
}
