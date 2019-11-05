package modelo;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import dto.CategoriaMovimientoCajaDTO;
import dto.CitaDTO;
import dto.ClienteDTO;
import dto.HorarioDTO;
import dto.MovimientoCajaDTO;
import dto.ProfesionalDTO;
import dto.PromocionDTO;
import dto.ServicioDTO;
import dto.ServicioTurnoDTO;
import dto.SucursalDTO;
import dto.UsuarioDTO;
import persistencia.dao.interfaz.CategoriaMovimientoCajaDAO;
import persistencia.dao.interfaz.CitaDAO;
import persistencia.dao.interfaz.ClienteDAO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.HorarioDAO;
import persistencia.dao.interfaz.MovimientoCajaDAO;
import persistencia.dao.interfaz.ProfesionalDAO;
import persistencia.dao.interfaz.PromocionDAO;
import persistencia.dao.interfaz.ServicioDAO;
import persistencia.dao.interfaz.ServicioTurnoDAO;
import persistencia.dao.interfaz.SucursalDAO;
import persistencia.dao.interfaz.UsuarioDAO;


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
	private CategoriaMovimientoCajaDAO categoriaMovimientoCaja;
	private ServicioTurnoDAO servicioTurno;
	private UsuarioDAO usuario;

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
		this.categoriaMovimientoCaja = metodo_persistencia.createCategoriaMovimientoCajaDAO();
		this.servicioTurno = metodo_persistencia.createServicioTurnoDAO();
		this.usuario=metodo_persistencia.createUsuarioDAO();
	}
	
	//Comienza Usuario
	public void agregarUsuario(UsuarioDTO nuevoUsuario)
	{
		this.usuario.insert(nuevoUsuario);
	}

	public void borrarUsuario(UsuarioDTO Usuario_a_eliminar)
	{
		this.usuario.delete(Usuario_a_eliminar);
	}

	public void editarUsuario(UsuarioDTO Usuario_a_editar)
	{
		this.usuario.update(Usuario_a_editar);
	}

	public List<UsuarioDTO> obtenerUsuarios1()
	{
		return this.usuario.readAll();
	}
	public List<UsuarioDTO> matcheo(String usuario, String pass)
	{
		return this.usuario.readOne(usuario,pass);
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
	
	public List<ClienteDTO> obtenerClienteConBuscador(String variable, String value) {
		return this.cliente.obtenerClienteBuscado(variable,value);

	}
	
	public ClienteDTO obtenerClienteById(int id) {
		return this.cliente.obtenerClienteById(id);
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
	
	public List<ServicioDTO> obtenerServicioConBuscador(String variable, String value) {
		return this.servicio.obtenerServBuscado(variable,value);

	}
	
	public ServicioDTO getServicioMax() {
		return this.servicio.getServicioMax();
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
	
	public ProfesionalDTO getProfesionalById(Integer idProfesional) {
		return this.profesional.getById(idProfesional);
	}
	
	public boolean agregarServicioProfesional( Integer idServ ,Integer idProf) {
		return this.profesional.insertServProf(idServ, idProf);
	}
	
	public boolean deleteServProfesional(Integer idServ ,Integer idProf) {
		return this.profesional.deleteServProf(idServ, idProf);
	}
	
	public List<String> obtenerServEnTabla(int id){
		return this.profesional.obtenerServEnTabla(id);
	}
	
	public List<ServicioDTO> getServiciosDelProfesional(int idProfesional){
		return this.profesional.getServiciosDelProfesional(idProfesional);
	}
	public List<ProfesionalDTO> obtenerProfesionalConBuscador(String variable, String value) {
		return this.profesional.obtenerProfBuscado(variable,value);
	}
	
	public List<ProfesionalDTO> getProfesionalByHorario(LocalTime inicioTurno, String diaDeLaSemana) {
		return this.profesional.getProfesionalByHorario(inicioTurno, diaDeLaSemana);
	}
	
	public List<ProfesionalDTO> getProfesionalByIdSucursal(int idSucursal){
		return this.profesional.getProfesionalByIdSucursal(idSucursal);
	}
	
	public List<ProfesionalDTO> getProfesionalesByIdServicio(int idServicio){
		return this.profesional.getProfesionalesByIdServicio(idServicio);
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

	public boolean cancelarCita(CitaDTO cita_a_cancelar) 
	{
		return this.cita.cancelar(cita_a_cancelar);
	}
	
	public boolean reprogramarCita(CitaDTO cita_a_reprogramar) {
		return this.cita.reprogramar(cita_a_reprogramar);
	}
	public boolean finalizarCita(CitaDTO cita_a_finalizar) 
	{
		return this.cita.finalizar(cita_a_finalizar);
	}
	
	public boolean editarCita(CitaDTO cita_a_editar)
	{
		return this.cita.update(cita_a_editar);
	}

	public List<CitaDTO> obtenerCitas()
	{
		return this.cita.readAll();
	}

	public List<CitaDTO> obtenerCitasActivas()
	{
		return this.cita.readAllActivas();
	}
	
	public List<CitaDTO> getCitasPorDia(String dia){
		return this.cita.getCitasPorDia(dia);
	}
	
	public CitaDTO getCitaMax() {
		return this.cita.getCitaMax();
	}
	
	public List<CitaDTO> getCitasPorRangoHorario(LocalTime desde, LocalTime hasta, LocalDate dia){
		return this.cita.getCitasByRangoHorario(desde, hasta, dia);
	}
	
	//Arranca ServicioTurno
	
	public boolean insertServicioTurno(ServicioTurnoDTO servicioTurno_a_insertar) {
		return this.servicioTurno.insert(servicioTurno_a_insertar);
	}
	
	public boolean deleteServicioTurno(ServicioTurnoDTO servicioTurno_a_eliminar) {
		return this.servicioTurno.delete(servicioTurno_a_eliminar);
	}
	
	public List<ServicioTurnoDTO> getServicioTurnoByIdCita(int idCita) {
		return this.servicioTurno.getByIdCita(idCita);
	}
	
	public List<Integer> getCitasByIdServicio(int idServicio){
		return this.servicioTurno.getCitasByIdServicio(idServicio);
	}
	
	public Integer profesionalOcupado(Integer idProfesional, LocalTime horaInicio,
			LocalTime horaFin, LocalDate fecha) {
		return this.servicioTurno.profesionalOcupado(idProfesional, 
				horaInicio, horaFin, fecha);
	}
	
	public Integer profesionalTrabaja(Integer idProfesional, LocalTime horaInicio,
			LocalTime horaFin, String diaDeLaSemana) {
		return this.servicioTurno.profesionalEnSucursal(idProfesional, horaInicio,
				horaFin, diaDeLaSemana);
	}
	
	//termina ServicioTurno
	
	// Funcion que obtiene la lista que se va a visualizar con los turnos ocupados
	//Recibe un string con el dia a buscar 
	public List<CitaDTO> obtenerTablaCita( String dia ){
		System.out.println(dia+" estoy en sistema ");
		return this.cita.readCitaPorDia(dia);
	}
	
	public CitaDTO getCitaById(int idCita) {
		return this.cita.getById(idCita);
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
	
	public List<PromocionDTO> obtenerPromoVigente(Date fInicio,Date fFin){
		return this.promocion.readPromoVigente(fInicio,fFin);
		
	}
	public boolean deleteRealPromocion (PromocionDTO promo) {
		return this.promocion.deleteRealPromocion(promo);
	}
	
	public PromocionDTO getPromocionById(Integer idPromocion) {
		return this.promocion.getById(idPromocion);
	}
	// FIN PROMOCIONES

	// COMIENZA CAJA
	public boolean insertarEgreso (MovimientoCajaDTO caja_a_insertar) {
		return this.caja.insertEgreso(caja_a_insertar);
	}
	
	public boolean insertarIngresoServicio(MovimientoCajaDTO movimiento_a_insertar) {
		return this.caja.insertIngresoServicioSinPromocion(movimiento_a_insertar);
	}
	
	public boolean insertarIngresoProducto(MovimientoCajaDTO movimiento_a_insertar) {
		return this.caja.insertIngresoProducto(movimiento_a_insertar);
	}
	
	public List<MovimientoCajaDTO> obtenerMovimientosCaja(String desde, String hasta){
		return this.caja.readDay(desde, hasta);
	}
	
	public List<MovimientoCajaDTO> obtenerMovimientosCajaSucursal(String desde, String hasta,int sucursal){
		return this.caja.readDaySucursal(desde, hasta, sucursal);
	}
	
	public List<MovimientoCajaDTO> obtenerMovimientosCajaIngresosCliente(String desde, String hasta,int cliente){
		return this.caja.readDayIngresosCliente(desde, hasta, cliente);
	}
	
	public List<MovimientoCajaDTO> obtenerMovimientosCajaIngresosProfesional(String desde, String hasta,int profesional){
		return this.caja.readDayIngresosProfesional(desde, hasta, profesional);
	}
	
	public List<MovimientoCajaDTO> obtenerMovimientosCajaIngresosServicio(String desde, String hasta,int servicio){
		return this.caja.readDayIngresosServicio(desde, hasta, servicio);
	}
	
	public List<MovimientoCajaDTO> obtenerMovimientoCajaRanking(String desde, String hasta){
		return this.caja.rankingVentas(desde, hasta);
	}
	//FIN CAJA
	
	// COMIENZA CATEGORIAS MOVIMIENTO CAJA
	public boolean insertarCategoriaMovimientoCaja (CategoriaMovimientoCajaDTO categoria_a_insertar) {
		return this.categoriaMovimientoCaja.insert(categoria_a_insertar);
	}
	public List<CategoriaMovimientoCajaDTO> obtenerCategoriasMovimientoCaja() {
		return this.categoriaMovimientoCaja.readAll();
	}
	public CategoriaMovimientoCajaDTO getCategoriaMovimientoCajaById(int idCategoria) {
		return this.categoriaMovimientoCaja.readOne(idCategoria);
	}
	
	public boolean deleteCategoriaMovimientoCaja (CategoriaMovimientoCajaDTO categoria_a_eliminar) {
		return this.categoriaMovimientoCaja.delete(categoria_a_eliminar);
	}
	public boolean deleteRealCategoria (CategoriaMovimientoCajaDTO categoria_a_eliminar) {
		return this.categoriaMovimientoCaja.deleteRealCategoria(categoria_a_eliminar);
	}
	
	public boolean updateCategoriaMovimientoCaja(CategoriaMovimientoCajaDTO categoria_a_editar) {
		return this.categoriaMovimientoCaja.update(categoria_a_editar);
	}
	public CategoriaMovimientoCajaDTO getIdCategoriaMovimientoCajaByName(String categoria) {
		return this.categoriaMovimientoCaja.readOneByName(categoria);
	}
	
	//Fin Categorias Movimiento Caja
	
	//Usuario
	public List<UsuarioDTO>IniciarSesion(String user,String pass){
		return this.usuario.readOne(user, pass);
	}
	
	public List<UsuarioDTO> obtenerUsuarios(){
		return this.usuario.readAll();
	}
	
	public List<UsuarioDTO> obtenerUnUsuario(int id) {
		return this.usuario.readOneById(id);
	}
	
	public void eliminarUsuario(UsuarioDTO usuario_a_eliminar) {
		this.usuario.delete(usuario_a_eliminar);
	}
	
	public void updateUsuario (UsuarioDTO usuario_a_modificar) {
		this.usuario.update(usuario_a_modificar);
	}
	
	public HashMap<String, Integer> readRol(){
		return this.usuario.readRol();
	}
}