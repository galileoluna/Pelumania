package dto;

import java.sql.Time;

public class CitaDTO{

    private int idCita;
    private int idUsuario;
    private int idCliente;
    private String nombre;
    private String apellido;
    private String estado;
    private int idProfesional;
    private double precioLocal;
    private double precioDolar;
    private Time hora;
    //private date fecha;
    private int idSucursal;
    //Falta modelar los servicios asociados del turno

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
                //this.nombre = Cliente.GETNOMBRE();
                //this.apellido = Cliente.GETAPELLIDO();
                String estado,
                int idprofesional,
                double precioLocal,
                double precioDolar,
                Time hora,
                //fecha
                int idsucursal
){
    this.idCita = idcita;
    this.idUsuario = idusuario;
    this.idCliente = idcliente;
    //this.nombre = Cliente.GETNOMBRE();
    //this.apellido = Cliente.GETAPELLIDO();
    this.estado = estado;
    this.idProfesional = idprofesional;
    this.precioLocal = precioLocal;
    this.precioDolar = precioDolar;
    this.hora = hora;
    //this.fecha = fecha;
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
                //String nombre = Cliente.GETNOMBRE();
                //String apellido = Cliente.GETAPELLIDO();
                String estado,
                double precioLocal,
                double precioDolar,
                Time hora,
                //fecha
                int idsucursal
){
    this.idCita = idcita;
    this.idUsuario = idusuario;
    this.idCliente = idcliente;
    this.estado = estado;
    this.precioLocal = precioLocal;
    this.precioDolar = precioDolar;
    this.hora = hora;
    //this.fecha = fecha;
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
                double precioLocal,
                double precioDolar,
                Time hora,
                //fecha
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
    this.hora = hora;
    //this.fecha = fecha;
    this.idSucursal = idsucursal;
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

public double getPrecioLocal(){
    return this.precioLocal;
}

public void setPrecioLocal(double preciolocal){
    this.precioLocal = preciolocal;
}

public double getPrecioDolar(){
    return this.precioDolar;
}

public void setPrecioDolar(double preciodolar){
    this.precioDolar = preciodolar;
}


public Time getHora(){
    return this.hora;
}

public void setHora( Time hora){
    this.hora = hora;
}


/*
public FECHa getfecha(){
    return this.fecha;
}

public void setFecha(FECHA fecha){
    this.fecha = fecha;
}
*/ 

public int getIdSucursal(){
    return this.idSucursal;
}

public void setIdSucursal(int idsucursal){
    this.idSucursal = idsucursal;
}

}