package presentacion.controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import dto.ProfesionalDTO;
import dto.ServicioDTO;
import modelo.Sistema;
import persistencia.conexion.Conexion;
import presentacion.vista.VentanaProfesional;
import presentacion.vista.VentanaServicioProfesional;

public class ControladorServicioProfesional {
	private static ControladorServicioProfesional INSTANCE;
	private Sistema sistema;
	private String nombre;
	private int idProfesional;
	private List<String> servEnTabla; 
	private List<String> allServ;
	private List<Integer> idServ = new ArrayList<Integer>();
	private VentanaServicioProfesional ventServProf;

	
	private ControladorServicioProfesional(Sistema sistema) {
		this.ventServProf = ventServProf.getInstance();
		this.ventServProf.getBtnAgregar().addActionListener(l -> agregarServ(l));
		this.ventServProf.getBtnBorrar().addActionListener(k -> borrarServ(k));
		this.sistema=sistema;
	}
	
	// Instancia de la pantalla que muestra la lista de servicios asociados a un profesional
	// Recibe el sistema, el id del profesional y el nombre del empleado
	
	public static ControladorServicioProfesional getInstance(Sistema sistema, int idProf, String nombre) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorServicioProfesional(sistema);
		}
		INSTANCE.nombre=nombre;
		INSTANCE.ventServProf.setNombreEmpl(nombre);
		INSTANCE.idProfesional=idProf;
		INSTANCE.servEnTabla=INSTANCE.sistema.obtenerServEnTabla(idProf);
		INSTANCE.allServ=llenarServ(INSTANCE.idServ,INSTANCE.sistema);
		llenarCombo(INSTANCE.ventServProf.getCombo(),INSTANCE.allServ);
		INSTANCE.ventServProf.llenarTabla(INSTANCE.servEnTabla);
		INSTANCE.ventServProf.show();
		return INSTANCE;
	}
	
	// Obtiene el campo seleccionado de la pantalla (VentanaServicioProfesional) y hace el insert en la tabla
	private void agregarServ(ActionEvent l) {
		String serv=this.ventServProf.getCombo().getSelectedItem().toString();
		if(validar(serv)) {
			this.sistema.agregarServicioProfesional(idServ.get(allServ.indexOf(serv)), idProfesional);
		// llamos  a la instancia para que vuelva a cargar la tabla con el servicio nuevo
			this.getInstance(sistema, idProfesional, nombre);
		}else {
			JOptionPane.showMessageDialog(null, "El profesional ya tiene relacionado el servicio que intenta asociar", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//borra el servicio seleccionado, el delete lo voy a dejar porque solo borra una relacion creo que no afecta en nada si hay citas asociado a un profesional y ya no realiza este servicio tendriamos que definir que hacer
	private void borrarServ(ActionEvent k) {
		int[] filasSeleccionadas = this.ventServProf.getTablServicioProfesional().getSelectedRows();
       
    	for (int fila : filasSeleccionadas)
    	{
        	if(this.servEnTabla.get(fila)!=null) {
        		 this.sistema.deleteServProfesional(idServ.get(allServ.indexOf(this.servEnTabla.get(fila))), idProfesional);
        	}
		}
    	this.getInstance(sistema, idProfesional, nombre);
	}
	
	// esta funcion hace dos cosas llena la lista con los servicios que se van a cargar en el desplegable y ademas llena una segunda lista con los indices(idServ) de dicha tabla para luego poder hacer mas facil los insert y delete
	// la lista que recibe por parametros el la lista de los id (idServ), devuelve la lista de nombres de los servicios
	private static List<String> llenarServ(List<Integer> id,Sistema sistema) {
		List<ServicioDTO> servicios=sistema.obtenerServicios();
		List<String> ret = new ArrayList<String>();	
		for (ServicioDTO s : servicios) {
			ret.add(s.getNombre());
			id.add(s.getIdServicio());
		}
			return ret;
			
	}
	// llena el combo con la lista que fue cargada anteriormente
	//recibe el combo y la lista
	private static void llenarCombo(JComboBox combo,List<String> lista) {
		combo.removeAllItems();
		for (String  s : lista) {
			combo.addItem(s);
		}
		
	}
		
	private boolean validar(String serv) {
		int encontro=0;
		for(String s : servEnTabla) {
			if(s.equals(serv)) {
				encontro++;
			}
		}
		if(encontro==0) {
			return true;
		}else {
			return false;
	
		}
	}
}
