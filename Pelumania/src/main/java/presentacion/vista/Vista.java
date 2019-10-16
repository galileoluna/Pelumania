package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JCalendar;

import dto.CitaDTO;
import dto.ServicioTurnoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.mariadb.ServicioDAOSQL;

public class Vista {

	private JFrame frame;
	private JCalendar calendario;
	private JButton btnServicios;

	private JMenu mnServicio;
	private JMenuItem mntmConsultarServicios;
	private JMenu mnProfesional;
	private JMenuItem menuConsultarProf;
	private JMenuItem menuConsultaClientes;
	private JMenuItem menuConsultarSucursal;
	private JMenu mnPromocion;
	private JMenuItem menuConsultaPromo;
	private JMenuItem menuPromosVigentes;
	private JMenu mnCliente;
	private JMenu mnSucursal;
	private JMenu mnCaja;
	private JMenuItem menuConsultarCaja;
	private JMenuItem menuConsultaCategoriaCaja;
	

	private JButton btnAgregarCita;
	private JButton btnEditarCita;
	private JButton btnCancelarCita;

	private JPanel JPanelCitas;
	private JScrollPane scrollPanelCitas;
	
	private static CitaDTO citaSeleccionada;
	private static ComponenteCita componenteCitaSeleccionado;
	private int cantCitas;



	public Vista()
	{
		super();
		initialize();
	}

	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 40, 1109, 683);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/images.jpg"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Hair & Head");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1091, 21);
		frame.getContentPane().add(menuBar);

		mnServicio = new JMenu("Servicio");
		menuBar.add(mnServicio);

		mntmConsultarServicios = new JMenuItem("Consultar Servicios");
		mnServicio.add(mntmConsultarServicios);

		mnProfesional = new JMenu("Profesional");
		menuBar.add(mnProfesional);

		menuConsultarProf= new JMenuItem("Consultar Profesionales");
		mnProfesional.add(menuConsultarProf);

		mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		menuConsultaClientes = new JMenuItem("Consultar Clientes");
		mnCliente.add(menuConsultaClientes);
		
		mnPromocion=new JMenu("Promociones");
		menuBar.add(mnPromocion);
		
		menuPromosVigentes= new JMenuItem("Ver Promos vigentes");
		mnPromocion.add(menuPromosVigentes);
		
		menuConsultaPromo= new JMenuItem("Consultar Promociones");
		mnPromocion.add(menuConsultaPromo);

        mnSucursal = new JMenu("Sucursal");
		menuBar.add(mnSucursal);
		
		menuConsultarSucursal = new JMenuItem("Consultar Sucursales");
		mnSucursal.add(menuConsultarSucursal);
		
		mnCaja = new JMenu("Caja");
		menuBar.add(mnCaja);
		
		menuConsultarCaja = new JMenuItem("Utilizar Caja");
		menuConsultaCategoriaCaja = new JMenuItem("Consultar Categorias");
		mnCaja.add(menuConsultaCategoriaCaja);
		mnCaja.add(menuConsultarCaja);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1091, 633);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		calendario = new JCalendar();
		calendario.setBounds(10, 33, 578, 600);
		calendario.getYearChooser().getSpinner().setEnabled(false);
		calendario.setTodayButtonVisible(true);
		panel.add(calendario);
		calendario.setVisible(true);

		btnAgregarCita = new JButton("Agregar Cita");
		btnAgregarCita.setBounds(608, 33, 117, 39);
		panel.add(btnAgregarCita);
		
		btnCancelarCita = new JButton("Cancelar Cita");
		btnCancelarCita.setBounds(752, 33, 117, 39);
		btnCancelarCita.setEnabled(false);
		panel.add(btnCancelarCita);
		
		btnEditarCita = new JButton("Borrar Cita");
		btnEditarCita.setBounds(897, 33, 117, 39);
		btnEditarCita.setEnabled(false);
		panel.add(btnEditarCita);
		
		JPanelCitas = new JPanel();
		JPanelCitas.setLayout(null);
		JPanelCitas.setPreferredSize(new Dimension(409,cantCitas*350));
		JPanelCitas.setBounds(620, 83, 460, 560);
		panel.add(JPanelCitas);
		
		scrollPanelCitas = new JScrollPane();
		scrollPanelCitas.setBounds(608,83,480,540);
		scrollPanelCitas.getVerticalScrollBar().setUnitIncrement(16);
		
		frame.setVisible(true);
	}
/*
	public void cargarCitas(List<CitaDTO> citasDelDia) {
		JPanelCitas.removeAll();

		int x = 10;
		int y = 10;
		
		cantCitas = citasDelDia.size();
		
		JPanelCitas.setPreferredSize(new Dimension(409,cantCitas*340));
		
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
			
			cc.setFocusable(true);
			cc.addMouseListener(new java.awt.event.MouseAdapter() {
				
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                	cc.requestFocus();
                	cc.isFocusOwner();
                	if (cc.hasFocus()) {
                		cc.setBackground(Color.white);
                    	setCitaSeleccionada(citaCargada);
                    	setComponenteCitaSeleccionado(cc);
                    	System.out.println(getCitaSeleccionada());
                    	
                    	if (citaSeleccionada.getEstado()!= "Cancelada") {
                    		getBtnCancelarCita().setEnabled(true);
                    		getBtnEditarCita().setEnabled(true);
                    	}
                	}
                }
  
            });
			
			cambiarColorCita(cc, citaCargada.getEstado());
			JPanelCitas.add(cc);
			y = y + 330;
		}
		
		scrollPanelCitas.setBounds(608,83,480,540);
		
		scrollPanelCitas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPanelCitas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		scrollPanelCitas.setViewportView(JPanelCitas);
		frame.getContentPane().add(scrollPanelCitas, BorderLayout.CENTER);
		
		scrollPanelCitas.setVisible(true);
	}
*/
	public void cambiarColorCita(ComponenteCita cita, String estado) {
		Color rojo = new Color(225,64,68);
		Color verde = new Color(129,152,48);
		switch (estado) {
		case "Activa":
			cita.setBackground(verde);
			break;
		case "Cancelada":
			cita.setBackground(rojo);
			break;
		}
	}
	
	public void show()
	{
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(
						null, "¿Deseas cerrar Pelumanía?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public JButton getBtnServicios() {
		return btnServicios;
	}

	public void setBtnServicios(JButton btnServicios) {
		this.btnServicios = btnServicios;
	}

	public JMenuItem getMnItmConsultarServicios() {
		return mntmConsultarServicios;
	}

	public JMenuItem getMenuProfesional() {
		return menuConsultarProf;
	}
	
	public JMenuItem getMenuSucursal() {
		return menuConsultarSucursal;
	}
	
	public JMenuItem getMenuPromocion() {
		return menuConsultaPromo;
	}
	
	public JMenuItem getMenuPromoVigente() {
		return menuPromosVigentes;
	}

	public JButton getBtnAgregarCita() {
		return btnAgregarCita;
	}

	public void setBtnAgregarCita(JButton btnAgregarCita) {
		this.btnAgregarCita = btnAgregarCita;
	}

	public JButton getBtnEditarCita() {
		return btnEditarCita;
	}

	public void setBtnEditarCita(JButton btnEditarCita) {
		this.btnEditarCita = btnEditarCita;
	}

	public JButton getBtnCancelarCita() {
		return btnCancelarCita;
	}

	public void setBtnCancelarCita(JButton btnCancelarCita) {
		this.btnCancelarCita = btnCancelarCita;
	}

	public JMenuItem getMenuConsultaClientes() {
		return menuConsultaClientes;
	}

	public void setMenuConsultaClientes(JMenuItem menuConsultaClientes) {
		this.menuConsultaClientes = menuConsultaClientes;
	}

	public JMenu getMnCliente() {
		return mnCliente;
	}
	
	public JMenuItem getMenuConsultaSucursal() {
		return menuConsultarSucursal;
	}

	public void setMenuConsultaSucursal(JMenuItem menuConsultaSucursal) {
		this.menuConsultarSucursal = menuConsultaSucursal;
	}

	public JMenu getMnSucursal() {
		return mnSucursal;
	}

	public JCalendar getCalendario() {
		return calendario;
	}

	public void setCalendario(JCalendar calendario) {
		this.calendario = calendario;
	}
	public JMenuItem getMenuCaja() {
		return this.menuConsultarCaja;
	}
	public JMenuItem getMenuConsultarCategoriaCaja() {
		return menuConsultaCategoriaCaja;
	}

	public void setMenuConsultarCategoriaCaja(JMenuItem menuConsultarCategoriaCaja) {
		this.menuConsultaCategoriaCaja = menuConsultarCategoriaCaja;
	}

	public static CitaDTO getCitaSeleccionada() {
		return citaSeleccionada;
	}

	public static void setCitaSeleccionada(CitaDTO citaSeleccionada) {
		Vista.citaSeleccionada = citaSeleccionada;
	}

	public static ComponenteCita getComponenteCitaSeleccionado() {
		return componenteCitaSeleccionado;
	}

	public static void setComponenteCitaSeleccionado(ComponenteCita componenteCitaSeleccionado) {
		Vista.componenteCitaSeleccionado = componenteCitaSeleccionado;
	}

	public JPanel getJPanelCitas() {
		return JPanelCitas;
	}

	public void setJPanelCitas(JPanel jPanelCitas) {
		JPanelCitas = jPanelCitas;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JScrollPane getScrollPanelCitas() {
		return scrollPanelCitas;
	}

	public void setScrollPanelCitas(JScrollPane scrollPanelCitas) {
		this.scrollPanelCitas = scrollPanelCitas;
	}

	public int getCantCitas() {
		return cantCitas;
	}

	public void setCantCitas(int cantCitas) {
		this.cantCitas = cantCitas;
	}
	
	
}
