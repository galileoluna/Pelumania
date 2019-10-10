package modelo;

import java.util.List;

import dto.CitaDTO;
import dto.ClienteDTO;
import dto.HorarioDTO;
import dto.MovimientoCajaDTO;
import dto.ProfesionalDTO;
import dto.PromocionDTO;
import dto.ServicioDTO;
import dto.SucursalDTO;
import persistencia.dao.interfaz.CitaDAO;
import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.HorarioDAO;
import persistencia.dao.interfaz.MovimientoCajaDAO;
import persistencia.dao.interfaz.ProfesionalDAO;
import persistencia.dao.interfaz.PromocionDAO;
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
	private PromocionDAO promocion;
	private MovimientoCajaDAO caja;

	public Sistema(DAOAbstractFactory metodo_persistencia)
	{
		this.cliente = metodo_persistencia.createClienteDAO();
		this.servicio = metodo_persistencia.createServicioDAO();
		this.profesional=metodo_persistencia.createProfesionalDAO();
		this.horario=metodo_persistencia.createHorarioDAO();
		this.sucursal = metodo_persistencia.createSucursalDAO();
		this.cita = metodo_persistencia.createCitaDAO();
		this.promocion=metodo_persistencia.createPromocionDAO();
		this.caja = metodo_persistencia.createMovimientoCajaDAO();
	}
// COMIENZA CLIENTE
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
//FIN CLIENTE
	
//COMIENZA SERVICIO	
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
	
	public void deleteRealServicio(ServicioDTO servicio_a_eliminar) {
		this.servicio.deleteRealServicio(servicio_a_eliminar);
	}
//FIN SERVICIO

	// ARRANCA LO QUE ES PROFESIONAL
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
	
	public boolean agregarServicioProfesional( Integer idServ ,Integer idProf) {
		return this.profesional.insertServProf(idServ, idProf);
	}
	
	public boolean deleteServProfesional(Integer idServ ,Integer idProf) {
		return this.profesional.deleteServProf(idServ, idProf);
	}
	// FIN PROFESIONAL
	
	// ARRANCA HORARIOS ASOCIADOS A PROFESIONAL
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
	// FIN HORARIO
    //arranca sucursal
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
	
	public void borrarSucursalReal(SucursalDTO sucursal_a_eliminar) {
		this.sucursal.deleteRealSucursal(sucursal_a_eliminar);
	}
	
	public SucursalDTO getSucursalById(int idSucursal) {
		return this.sucursal.readOne(idSucursal);
	}
	//finaliza sucursal

	public void deleteReal(ClienteDTO cliente_a_eliminar) {
		this.cliente.deleteReal(cliente_a_eliminar);

	}

	public boolean agregarCita(CitaDTO nuevaCita)
	{
		return this.cita.insert(nuevaCita);
	}

	public boolean agregarCitaSinCliente(CitaDTO nuevaCita)
	{
		return this.cita.insertSinRegistrar(nuevaCita);
	}
	
	public boolean borrarcita(CitaDTO cita_a_eliminar)
	{
		return this.cita.delete(cita_a_eliminar);
	}
	
	public boolean deleteReal(CitaDTO cita_a_eliminar) {
		return this.cita.deleteReal(cita_a_eliminar);
	}

	public boolean cancelarCita(CitaDTO cita_a_cancelar) {
		return this.cita.cancelar(cita_a_cancelar);
	}
	public void editarCita(CitaDTO cita_a_editar)
	{
		this.cita.update(cita_a_editar);
	}

	public List<CitaDTO> obtenerCitas()
	{
		return this.cita.readAll();
	}
	
	// Funcion que obtiene la lista que se va a visualizar con los turnos ocupados
	//Recibe un string con el dia a buscar 
	public List<CitaDTO> obtenerTablaCita( String dia ){
		return this.cita.readCitaPorDia(dia);
	}
	
	// ARRANCA TODO LO QUE SON PROMOCIONES
	public List<PromocionDTO> obtenerPrmociones(){
		return this.promocion.readAll();
	}
	
	public List<PromocionDTO> obtenerUnaPrmociones(int id_promocion){
		return this.promocion.readOne(id_promocion);
	}
	
	public boolean insertarPromocion(PromocionDTO promo) {
		return this.promocion.insert(promo);
	}
	
	public boolean insertarServPromo(int id_promocion, int id_servicio) {
		return this.promocion.insertServProm(id_promocion, id_servicio);
	}
	
	public boolean borrarPromocion(PromocionDTO promo) {
		return this.promocion.delete(promo);
	}
	
	public boolean borrarServProm (int id_promocion, int id_servicio) {
		return this.promocion.deleteServProm(id_promocion, id_servicio);
	}
	
	public boolean editarPromocion (PromocionDTO promo) {
		return this.promocion.update(promo);
	}
	
	public List<String> obtenerServPromo(int id_promo){
		return this.promocion.readAllServProm(id_promo);
	}
	// FIN PROMOCIONES

	// COMIENZA CAJA
	public boolean insertarMovimientoCaja (MovimientoCajaDTO caja_a_insertar) {
		return this.caja.insert(caja_a_insertar);
	}
	//FIN CAJA

}
