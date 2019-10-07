package dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDTO{

    private int idCita;
    private int idUsuario;
    private int idCliente;
    private String nombre;
    private String apellido;
    private String estado;
    private int idProfesional;
    private int idServicio;
    private BigDecimal precioLocal;
    private BigDecimal precioDolar;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fecha;
    private int idSucursal;
	private String nomProfesional;
	private String nomSucursal;

    /***
 * Constructor para una Cita con un Cliente registrado(Posee ID)
 * @param idcita (Id autoincremental de la cita)
 * @param idusuario (ID del usuario que carga el turno)
 * @param idcliente (ID del cliente asociado al turno)
 * @param estado (Estado del turno)
 * @param precioLocal (precio final en la moneda local)
 * @param precioDolar (precio final en dolares)
 * @param idsucursal (Sucursal asociada al turno)
 */
public CitaDTO (int idcita,
                int idusuario,                
                int idcliente,
                String nombre,
                String apellido,
                String estado,
                int idprofesional,
                int idservicio,
                BigDecimal precioLocal,
                BigDecimal precioDolar,
                LocalTime horaInicio,
                LocalTime horaFin,
                LocalDate fecha,
                int idsucursal
){
    this.idCita = idcita;
    this.idUsuario = idusuario;
    this.idCliente = idcliente;
    this.nombre = nombre;
    this.apellido = apellido;
    this.estado = estado;
    this.idProfesional = idprofesional;
    this.precioLocal = precioLocal;
    this.precioDolar = precioDolar;
    this.horaInicio = horaInicio;
    this.horaFin = horaFin;
    this.fecha = fecha;
    this.idSucursal = idsucursal;
}
/***
 * Constructor sin un profesional en especifico asignado
 * @param idcita (Id autoincremental de la cita)
 * @param idusuario (ID del usuario que carga el turno)
 * @param idcliente (ID del cliente asociado al turno)
 * @param estado (Estado del turno)
 * @param precioLocal (precio final en la moneda local)
 * @param precioDolar (precio final en dolares)
 * @param idsucursal (Sucursal asociada al turno)
 */
public CitaDTO (int idcita,
                int idusuario,                
                int idcliente,
                String nombre,
                String apellido,
                String estado,
                BigDecimal precioLocal,
                BigDecimal precioDolar,
                LocalTime horaInicio,
                LocalTime horaFin,
                LocalDate fecha,
                int idsucursal
){
    this.idCita = idcita;
    this.idUsuario = idusuario;
    this.idCliente = idcliente;
    this.estado = estado;
    this.precioLocal = precioLocal;
    this.precioDolar = precioDolar;
    this.horaInicio = horaInicio;
    this.horaFin = horaFin;
    this.fecha = fecha;
    this.idSucursal = idsucursal;
}

/***
 * Constructor sin un cliente registrado
 * @param idcita (Id autoincremental de la cita)
 * @param idusuario (ID del usuario que carga el turno)
 * @param nombre (Nombre del cliente)
 * @param apellido (Apellido del cliente)
 * @param estado (Estado del turno)
 * @param precioLocal (precio final en la moneda local)
 * @param precioDolar (precio final en dolares)
 * @param idsucursal (Sucursal asociada al turno)
 */
public CitaDTO (int idcita,
                int idusuario,                
                String nombre,
                String apellido,
                String estado,
                int idprofesional,
                BigDecimal precioLocal,
                BigDecimal precioDolar,
                LocalTime horaInicio,
                LocalTime horaFin,
                LocalDate fecha,
                int idsucursal
){
    this.idCita = idcita;
    this.idUsuario = idusuario;
    this.nombre = nombre;
    this.apellido = apellido;
    this.estado = estado;
    this.idProfesional = idprofesional;
    this.precioLocal = precioLocal;
    this.precioDolar = precioDolar;
    this.horaInicio = horaInicio;
    this.horaFin = horaFin;
    this.fecha = fecha;
    this.idSucursal = idsucursal;
}

//Contructor para visualizacion de turno por tabla
// se pueden agregar mas campos o sacarlos hasta que definamos bien que mostrar con esto creo que alcanza
public CitaDTO(int idCita2, String profesional, String cliente, BigDecimal precioLocal2, BigDecimal precioDolar2,
		LocalTime hora, LocalDate dia, String sucursal, String estado2) {
	this.idCita=idCita2;
	this.setNomProfesional(profesional);
	this.nombre=cliente;
	this.precioLocal=precioLocal2;
	this.precioDolar=precioDolar2;
	this.horaInicio=hora;
	this.fecha=dia;
	this.setNomSucursal(sucursal);
	this.estado=estado;
}
public int getIdCita(){
    return this.idCita;
}

public void setIdCita(int idcita){
    this.idCita = idcita;
}

public int getIdUsuario(){
    return this.idUsuario;
}

public String getNombre() {
	return this.nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApellido() {
	return this.apellido;
}

public void setApellido(String apellido) {
	this.apellido = apellido;
}
public void setIdUsuario(int idusuario){
    this.idUsuario = idusuario;
}

public int getIdCliente(){
    return this.idCliente;
}

public void setIdCliente(int idcliente){
    this.idCliente = idcliente;
}



public String getEstado(){
    return this.estado;
}

public void setEstado(String estado){
    this.estado = estado;
}

public int getIdProfesional(){
    return this.idProfesional;
}

public void setIdProfesional(int idProfesional){
    this.idCliente = idProfesional;
}

public BigDecimal getPrecioLocal(){
    return this.precioLocal;
}

public int getIdServicio() {
	return idServicio;
}
public void setIdServicio(int idServicio) {
	this.idServicio = idServicio;
}
public void setPrecioLocal(BigDecimal preciolocal){
    this.precioLocal = preciolocal;
}

public BigDecimal getPrecioDolar(){
    return this.precioDolar;
}

public void setPrecioDolar(BigDecimal preciodolar){
    this.precioDolar = preciodolar;
}


public LocalTime getHoraInicio(){
    return this.horaInicio;
}

public void setHora( LocalTime hora){
    this.horaInicio = hora;
}

public LocalTime getHoraFin() {
	return horaFin;
}
public void setHoraFin(LocalTime horaFin) {
	this.horaFin = horaFin;
}

public LocalDate getFecha(){
    return this.fecha;
}

public void setFecha(LocalDate fecha){
    this.fecha = fecha;
}

public int getIdSucursal(){
    return this.idSucursal;
}

public void setIdSucursal(int idsucursal){
    this.idSucursal = idsucursal;
}
public String getNomProfesional() {
	return nomProfesional;
}
public void setNomProfesional(String nomProfesional) {
	this.nomProfesional = nomProfesional;
}
public String getNomSucursal() {
	return nomSucursal;
}
public void setNomSucursal(String nomSucursal) {
	this.nomSucursal = nomSucursal;
}

}