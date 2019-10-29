package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.jfree.util.Log;

import dto.SucursalDTO;
import modelo.Sistema;
import presentacion.vista.nuevaVentanaCita;

public class ControladorCita implements ActionListener{
	private Sistema sistema;
	private nuevaVentanaCita ventanaCita;
	private static ControladorCita INSTANCE;
	
	private LocalDate fechaCita;
	private SucursalDTO sucursal; 
	
	private List<SucursalDTO> listaSucursales;
	
	public ControladorCita(Sistema s) {
		this.ventanaCita = nuevaVentanaCita.getInstance();
		this.sistema = s;
		
		this.ventanaCita.getBtnEditarFecha().addActionListener(a -> habilitarEditarFecha(a));
		
		inicializarArreglos();
	}
	
	public static ControladorCita getInstance(Sistema sistema) {
		if ( INSTANCE == null) {
			INSTANCE = new ControladorCita(sistema);
		}
		nuevaVentanaCita.getInstance();
		return INSTANCE;
	}

	public LocalDate getFecha() {
		return fechaCita;
	}

	public void setFecha(LocalDate fecha) {
		this.fechaCita = fecha;
		ventanaCita.setFechaCita(fecha);
		}

	public void inicializarArreglos() {
		listaSucursales = this.sistema.obtenerSucursales();
	}
	
	public void cargarDatos() {
		cargarListaSucursales();
		setearSucursalActual();
	}
	
	public SucursalDTO getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalDTO sucursal) {
		this.sucursal = sucursal;
	}

	public void cargarListaSucursales() {
		for (SucursalDTO sucu : listaSucursales) {
			this.ventanaCita.getJCBoxSucursal().addItem(sucu);
		}
	}
	
	public void setearSucursalActual() {
		System.out.println(sucursal);
		this.ventanaCita.getJCBoxSucursal().setSelectedItem(sucursal);
	}
	
	
	/* METODOS PARA LOS CONTROLADORES */
	public void habilitarEditarFecha(ActionEvent a) {
		this.ventanaCita.getJDChooserFecha().setEnabled(true);
	}
		
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}