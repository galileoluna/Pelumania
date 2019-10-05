package modelo;

import java.util.List;

import dto.CitaDTO;
import dto.ClienteDTO;
import dto.HorarioDTO;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.SucursalDTO;
import persistencia.dao.interfaz.CitaDAO;
import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.HorarioDAO;
import persistencia.dao.interfaz.ProfesionalDAO;
import persistencia.dao.interfaz.ServicioDAO;
import persistencia.dao.interfaz.SucursalDAO;


public class Sistema
{
	private ClienteDAO cliente;
	private ServicioDAO servicio;
	private ProfesionalDAO profesional;
	private HorarioDAO horario;
	private SucursalDAO sucursal;
	private CitaDAO cita;

	public Sistema(DAOAbstractFactory metodo_persistencia)
	{
		this.cliente = metodo_persistencia.createClienteDAO();
		this.servicio = metodo_persistencia.createServicioDAO();
		this.profesional=metodo_persistencia.createProfesionalDAO();
		this.horario=metodo_persistencia.createHorarioDAO();
		this.sucursal = metodo_persistencia.createSucursalDAO();
		this.cita = metodo_persistencia.createCitaDAO();
	}

	public void agregarCliente(ClienteDTO nuevoCliente)
	{
		this.cliente.insert(nuevoCliente);
	}

	public void borrarCliente(ClienteDTO cliente_a_eliminar)
	{
		this.cliente.delete(cliente_a_eliminar);
	}

	public void editarCliente(ClienteDTO cliente_a_editar)
	{
		this.cliente.update(cliente_a_editar);
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

	public void editarServicio(ServicioDTO servicio_a_actualizar) {
		this.servicio.update(servicio_a_actualizar);
	}

	public ServicioDTO getServicioById(int id) {
		return this.servicio.getById(id);
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

	public void borrarSanti(ProfesionalDTO profesional_a_eliminar) {
		this.profesional.deleteSanti(profesional_a_eliminar);
	}

	public List<ProfesionalDTO> obtenerProfesional(){
		return this.profesional.readAll();
	}

	public List<ProfesionalDTO> editarProfesional(int id){
		return this.profesional.readOne(id);
	}

	public void actualizarProfesional(ProfesionalDTO profesional_a_editar) {
		this.profesional.update(profesional_a_editar);
	}

	public List<HorarioDTO> obtenerHorario(int id){
		return this.horario.readOne(id);
	}

	public List<HorarioDTO> obtenerUnHorarios(int id){
		return this.horario.readAll(id);
	}
	public void borrarHorario(HorarioDTO horario_a_eliminar) {
		this.horario.delete(horario_a_eliminar);
	}

	public void agregarHorario(HorarioDTO horario) {
		this.horario.insert(horario);
	}

	public void actualizarHorario(HorarioDTO horario_a_actualizar) {
		this.horario.update(horario_a_actualizar);
	}


	public void agregarSucursal(SucursalDTO sucursal_a_agregar) {
		this.sucursal.insert(sucursal_a_agregar);
	}

	public void borrarSucursal(SucursalDTO sucursal_a_eliminar) {
		this.sucursal.delete(sucursal_a_eliminar);
	}

	public void editarSucursal(SucursalDTO sucursal_a_editar) {
		this.sucursal.update(sucursal_a_editar);
	}

	public List<SucursalDTO> obtenerSucursales(){
		return this.sucursal.readAll();
	}

	public void deleteReal(ClienteDTO cliente_a_eliminar) {
		this.cliente.deleteReal(cliente_a_eliminar);

	}

	public void agregarCita(CitaDTO nuevaCita)
	{
		this.cita.insert(nuevaCita);
	}

	public void borrarcita(CitaDTO cita_a_eliminar)
	{
		this.cita.delete(cita_a_eliminar);
	}

	public void editarCita(CitaDTO cita_a_editar)
	{
		this.cita.update(cita_a_editar);
	}

	public List<CitaDTO> obtenerCitas()
	{
		return this.cita.readAll();
	}



}
