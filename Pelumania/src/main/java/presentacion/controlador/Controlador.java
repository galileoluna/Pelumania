package presentacion.controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;

import dto.CitaDTO;
import dto.ProfesionalDTO;
import dto.ServicioDTO;
import dto.ServicioTurnoDTO;
import modelo.Sistema;
import presentacion.vista.ComponenteCita;
import presentacion.vista.Vista;

public class Controlador implements ActionListener {
	private Vista vista;
	private ControladorCliente controladorCliente;
	private ControladorServicio controladorServicio;
	
	private List<CitaDTO> citasEnTabla;

	private ControladorProfesional controladorProfesional;
	private ControladorAgregarCita controladoragregarcita;
	private ControladorPromocion controladorPromocion;
	private ControladorSucursal controladorSucursal;
	private ControladorPromocionesVigentes controladorPromoVigente;
	private ControladorCaja controladorCaja;
	private ControladorCategoriaMovimientoCaja controladorCategoriaMovimientoCaja;
	
	private Sistema sistema;

	public Controlador(Vista vista, Sistema sistema)
	{
		this.vista = vista; 
		this.sistema = sistema;
		
		cargarCitasDelDia();

		this.vista.getMnItmConsultarServicios().addActionListener(c->ventanaServicios(c));
		this.vista.getMenuProfesional().addActionListener(l->ventanaProfesional(l));
		this.vista.getMenuConsultaClientes().addActionListener(l -> ventanaAgregarCliente(l));
		this.vista.getMenuPromocion().addActionListener(m -> ventanaPromocion(m));
		this.vista.getMenuPromoVigente().addActionListener(p -> verPromosVigentes(p));
		this.vista.getMenuSucursal().addActionListener(e -> ventanaSucursal(e));
		this.vista.getMenuCaja().addActionListener(a -> ventanaCaja(a));
		this.vista.getMenuConsultarCategoriaCaja().addActionListener(a -> ventanCategoriaMovimientoCaja(a));
		
		this.vista.getBtnAgregarCita().addActionListener(d -> ventanaAgregarCita(d));
		this.vista.getBtnCancelarCita().addActionListener(e -> cancelarCita(e));
	}

	private void ventanCategoriaMovimientoCaja(ActionEvent a) {
		this.controladorCategoriaMovimientoCaja = ControladorCategoriaMovimientoCaja.getInstance(sistema);
	}

	private void ventanaCaja(ActionEvent a) {
		this.controladorCaja = ControladorCaja.getInstance(sistema);
	}

	private void ventanaServicios(ActionEvent c) {
		this.controladorServicio = ControladorServicio.getInstance(sistema);
	}

	private void ventanaAgregarCliente(ActionEvent a) {
		this.controladorCliente = ControladorCliente.getInstance(sistema);
	}

	private void ventanaProfesional (ActionEvent b) {
		this.controladorProfesional= ControladorProfesional.getInstance(sistema);
	}

	private void ventanaPromocion(ActionEvent m) {
		this.controladorPromocion= ControladorPromocion.getInstance(sistema);
	}
	
	private void ventanaSucursal (ActionEvent b) {
		this.controladorSucursal= ControladorSucursal.getInstance(sistema);
	}

	private void ventanaAgregarCita(ActionEvent d) {
		int dia = this.vista.getCalendario().getDayChooser().getDay();
		int mes = this.vista.getCalendario().getMonthChooser().getMonth();
		int anio = this.vista.getCalendario().getYearChooser().getYear();
		
		LocalDate fecha = LocalDate.of(anio, mes+1, dia);
		
		if (validarFechaCita(fecha))
			{
			ControladorAgregarCita.setANIO(anio);
			ControladorAgregarCita.setMES(mes+1);
			ControladorAgregarCita.setDIA(dia);
			this.controladoragregarcita = ControladorAgregarCita.getInstance(sistema);
			}else{
				JOptionPane.showMessageDialog(null, "No puedes cargar una cita para un dia que ya transcurrio!");
			}
	}
	
	/*
	 * Metodo que responde al boton CancelarCita. Obtiene la variable estatica getComponenteCitaSeleccionado
	 * de la vista, y esa es la cita que cancela.*/
	private void cancelarCita(ActionEvent e) {
		int confirm = JOptionPane.showOptionDialog(null, "Estas seguro que deseas cancelar la cita?","Confirmacion", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			this.sistema.cancelarCita(Vista.getCitaSeleccionada());
			Vista.getCitaSeleccionada().setEstado("Cancelada");
			this.vista.getComponenteCitaSeleccionado().getLbl_Estado().setText("Cancelada");
			this.vista.cambiarColorCita(Vista.getComponenteCitaSeleccionado(), Vista.getCitaSeleccionada().getEstado());
		}
	}
	
	private void verPromosVigentes(ActionEvent p) {
		this.controladorPromoVigente=ControladorPromocionesVigentes.getInstance(sistema,vista);
	}

	public void cargarCitasDelDia() {
		int dia = this.vista.getCalendario().getDayChooser().getDay();
		int mes = this.vista.getCalendario().getMonthChooser().getMonth();
		int anio = this.vista.getCalendario().getYearChooser().getYear();
		
		this.vista.getCalendario().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				
				int dia = Controlador.this.vista.getCalendario().getDayChooser().getDay();
				String S_dia;
				if (dia<10)
					S_dia = "0"+Integer.toString(Controlador.this.vista.getCalendario().getDayChooser().getDay());
				else
					S_dia = Integer.toString(Controlador.this.vista.getCalendario().getDayChooser().getDay());
				
				String mes =Integer.toString(Controlador.this.vista.getCalendario().getMonthChooser().getMonth()+1);
				String anio =Integer.toString(Controlador.this.vista.getCalendario().getYearChooser().getYear());
				
				//A obtenerTablaCita se le pasa el dia que se selecciona como un string EJ: 2019-10-06
				Controlador.this.citasEnTabla = Controlador.this.sistema.getCitasPorDia(anio+mes+S_dia);
				
				if (Controlador.this.citasEnTabla.size() != 0) {
					cargarCitas(citasEnTabla);
				}else {
				cargarCitas(citasEnTabla);
				JLabel SinCitas = new JLabel("Aún no hay citas para este día!");
				SinCitas.setBounds(150,250,200,60);
				Controlador.this.vista.getJPanelCitas().add(SinCitas);
				SinCitas.setVisible(true);
				}
			}
		});
	}
	
	/*Recibe el ID de cita, y su Tarjeta en el panel, y llama al metodo que las carga. */
	public void llenarTablaServicios(int idCita, ComponenteCita Cita) {
		List<ServicioTurnoDTO> serviciosDeCita = this.sistema.getByIdCita(idCita);
		cargarServiciosDeCita(serviciosDeCita, Cita);
	}
	
	/*Metodo que recibe las citas de un turno, y su Tarjeta en el Panel y carga los servicios en la tabla*/
	public void cargarServiciosDeCita(List<ServicioTurnoDTO> serviciosDeCita, ComponenteCita Cita) {
			
			Cita.getModelServicios().setRowCount(0); //Para vaciar la tabla
			Cita.getModelServicios().setColumnCount(0);
			Cita.getModelServicios().setColumnIdentifiers(Cita.getNombreColumnas());

			for (ServicioTurnoDTO st : serviciosDeCita)
			{
				
				int idServ = st.getIdServicio();
				int idProf = st.getIdProfesional();
				
				ServicioDTO serv = this.sistema.getServicioById(idServ);
				ProfesionalDTO prof = this.sistema.getProfesionalById(idProf);
				
				String nombre = serv.getNombre();
				BigDecimal precioLocal = serv.getPrecioLocal();
				BigDecimal precioDolar = serv.getPrecioDolar();
				LocalTime duracion = serv.getDuracion();
				String nombreProf = prof.getNombre()+" "+prof.getApellido();
				Object[] fila = {nombre, precioLocal, precioDolar, duracion, nombreProf};
				Cita.getModelServicios().addRow(fila);
			}	
		}
	
	/*
	 * Metodo que recibe las citas del dia elegido en el calendario, y se encarga de cargar una "Tarjeta"
	 * en pantalla con los datos de cada una. */
	
	public void cargarCitas(List<CitaDTO> citasDelDia) {
		this.vista.getJPanelCitas().removeAll();

		int x = 10;
		int y = 10;
		
		this.vista.setCantCitas(citasDelDia.size());
		
		this.vista.getJPanelCitas().setPreferredSize(new Dimension(409,this.vista.getCantCitas()*340));
		
		for (int i =0; i < citasDelDia.size(); i++) {
			ComponenteCita cc = new ComponenteCita(x,y);
			cc.setFocusable(true);
			CitaDTO citaCargada = citasDelDia.get(i);
			cc.getLbl_IdCita().setText(Integer.toString(citaCargada.getIdCita()));
			cc.getLbl_HoraInicio().setText(citaCargada.getHoraInicio().toString());
			cc.getLbl_HoraFin().setText(citaCargada.getHoraFin().toString());
			cc.getLbl_NombreCliente().setText(citaCargada.getNombre()+citaCargada.getApellido());	
			cc.getLbl_Estado().setText(citaCargada.getEstado());
			cc.getLbl_Total().setText(citaCargada.getPrecioLocal().toString());
			cc.getLbl_TotalUSD().setText(citaCargada.getPrecioDolar().toString());

			llenarTablaServicios(citaCargada.getIdCita(), cc);
			
			cc.setFocusable(true);
			cc.addMouseListener(new java.awt.event.MouseAdapter() {
				
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                	cc.requestFocus();
                	cc.isFocusOwner();
                	if (cc.hasFocus()) {
                		cc.setBackground(Color.white);
                    	Vista.setCitaSeleccionada(citaCargada);
                    	Vista.setComponenteCitaSeleccionado(cc);
                    	
                    	if (Vista.getCitaSeleccionada().getEstado()!= "Cancelada") {
                    	    Controlador.this.vista.getBtnCancelarCita().setEnabled(true);
                    	    Controlador.this.vista.getBtnEditarCita().setEnabled(true);
                    	}
                	}
                }
 
            });
			
			this.vista.cambiarColorCita(cc, citaCargada.getEstado());
			this.vista.getJPanelCitas().add(cc);
			y = y + 330;
		}
		
		this.vista.getScrollPanelCitas().setBounds(608,83,480,540);
		
		this.vista.getScrollPanelCitas().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.vista.getScrollPanelCitas().setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.vista.getScrollPanelCitas().setViewportView(this.vista.getJPanelCitas());
		this.vista.getFrame().getContentPane().add(this.vista.getScrollPanelCitas(), BorderLayout.CENTER);
		
		this.vista.getScrollPanelCitas().setVisible(true);
	}
	
	/* Metodo que recibe una fecha y devuelve true si la fecha es despues
	 * del dia de hoy, y false si el dia es anterior a hoy.
	 */
	
	public boolean validarFechaCita(LocalDate fechaCita) {
		LocalDate hoy = LocalDate.now();
		return fechaCita.isAfter(hoy) || fechaCita.isEqual(hoy);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { }
}
