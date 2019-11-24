package dto;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class CitaDTO{
    
        private int idCita;
        private int idUsuario;
        private int idCliente;
        private String nombre;
        private String apellido;
        private String telefono;
        private String mail;
        private String estado;
        private BigDecimal precioLocal;
        private BigDecimal precioDolar;
        private LocalTime horaInicio;
        private LocalTime horaFin;
        private LocalDate fecha;
        private int idSucursal;
    	private String nomProfesional;
    	private String nomSucursal;
    	private Time horaTurno;
    	private Date diaTurno;
    	private int idPromocion;

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
            String telefono,
            String mail,
            String estado,
            BigDecimal precioLocal,
            BigDecimal precioDolar,
            LocalTime horaInicio,
            LocalTime horaFin,
            LocalDate fecha,
            int idsucursal,
            int idPromocion
	){
	this.idCita = idcita;
	this.idUsuario = idusuario;
	this.idCliente = idcliente;
	this.nombre = nombre;
	this.apellido = apellido;
	this.telefono = telefono;
	this.mail = mail;
	this.estado = estado;
	this.precioLocal = precioLocal;
	this.precioDolar = precioDolar;
	this.horaInicio = horaInicio;
	this.horaFin = horaFin;
	this.fecha = fecha;
	this.idSucursal = idsucursal;
	this.idPromocion = idPromocion;
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
            BigDecimal precioLocal,
            BigDecimal precioDolar,
            LocalTime horaInicio,
            LocalTime horaFin,
            LocalDate fecha,
            int idsucursal,
            int idPromocion
    ){
	this.idCita = idcita;
	this.idUsuario = idusuario;
	this.nombre = nombre;
	this.apellido = apellido;
	this.estado = estado;
	this.precioLocal = precioLocal;
	this.precioDolar = precioDolar;
	this.horaInicio = horaInicio;
	this.horaFin = horaFin;
	this.fecha = fecha;
	this.idSucursal = idsucursal;
	this.idPromocion=idPromocion;
}
    
    //Contructor para visualizacion de turno por tabla
    // se pueden agregar mas campos o sacarlos hasta que definamos bien que mostrar con esto creo que alcanza
    public CitaDTO(int idCita2, String profesional, String cliente, Time hora, java.sql.Date dia, String sucursal, String estado2) {
    	this.idCita=idCita2;
    	this.setNomProfesional(profesional);
    	this.nombre=cliente;
    	this.setHoraTurno(hora);
    	this.setDiaTurno(dia);
    	this.setNomSucursal(sucursal);
    	this.estado=estado2;
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
    
    public String getTelefono() {
    	return telefono;
    }
    
    public void setTelefono(String telefono) {
    	this.telefono = telefono;
    }
    
    public String getMail() {
    	return mail;
    }
    
    public void setMail(String mail) {
    	this.mail = mail;
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
    
    public void setIdProfesional(int idProfesional){
        this.idCliente = idProfesional;
    }
    
    public BigDecimal getPrecioLocal(){
        return this.precioLocal;
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
    
    public void setHoraInicio( LocalTime hora){
        this.horaInicio = hora;
    }
    
    public LocalTime getHoraFin() {
    	return this.horaFin;
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
    public Date getDiaTurno() {
    	return diaTurno;
    }
    public void setDiaTurno(Date diaTurno) {
    	this.diaTurno = diaTurno;
    }
    public Time getHoraTurno() {
    	return horaTurno;
    }
    public void setHoraTurno(Time horaTurno) {
    	this.horaTurno = horaTurno;
    }
    public boolean esActiva() {
    	return this.estado.equalsIgnoreCase("Activa")|| this.estado.equalsIgnoreCase("fiado");
    }
    public boolean esSoloActiva() {
    	return this.estado.equalsIgnoreCase("Activa");
    }
    public int getIdPromocion() {
		return idPromocion;
	}
	public void setIdPromocion(int idPromocion) {
		this.idPromocion = idPromocion;
	}
    public boolean estaEnCurso() {
    	if(estado.equalsIgnoreCase("En curso"))return true;
    	else return false;
    }
}