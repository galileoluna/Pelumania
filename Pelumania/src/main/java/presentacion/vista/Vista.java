package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import dto.CitaDTO;
import persistencia.conexion.Conexion;
import javax.swing.border.LineBorder;

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
	private DefaultTableModel modelCita;
	private  String[] nombreColumnas = {"Horario","Cliente","Estado"};
	private JTable table;
	private JButton btnAgregarCita;
	private JMenuItem menuConsultaCategoriaCaja;
	
	private JPanel JPanelCitas;
	private JScrollPane scrollPanelCitas;
	
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

		modelCita = new DefaultTableModel(null,nombreColumnas);

		btnAgregarCita = new JButton("Agregar Cita");
		btnAgregarCita.setBounds(608, 33, 117, 39);
		panel.add(btnAgregarCita);
		
		JButton btnCancelarCita = new JButton("Cancelar Cita");
		btnCancelarCita.setBounds(752, 33, 117, 39);
		panel.add(btnCancelarCita);
		
		JButton btnBorrarCita = new JButton("Borrar Cita");
		btnBorrarCita.setBounds(897, 33, 117, 39);
		panel.add(btnBorrarCita);
		
		JPanelCitas = new JPanel();
		JPanelCitas.setLayout(null);
		JPanelCitas.setPreferredSize(new Dimension(409,cantCitas*350));
		JPanelCitas.setBounds(620, 83, 460, 560);
		panel.add(JPanelCitas);
		
		scrollPanelCitas = new JScrollPane();
		scrollPanelCitas.setBounds(608,83,480,540);
		
		frame.setVisible(true);
	}

	public void cargarCitas(List<CitaDTO> citasDelDia) {
		JPanelCitas.removeAll();

		int x = 10;
		int y = 10;
		
		cantCitas = citasDelDia.size();
		
		JPanelCitas.setPreferredSize(new Dimension(409,cantCitas*340));
		
		for (int i =0; i < citasDelDia.size(); i++) {
			ComponenteCita cc = new ComponenteCita(x,y);
			CitaDTO citaCargada = citasDelDia.get(i);
			cc.getLbl_IdCita().setText(Integer.toString(citaCargada.getIdCita()));
			cc.getLbl_HoraInicio().setText(citaCargada.getHoraInicio().toString());
			cc.getLbl_HoraFin().setText(citaCargada.getHoraFin().toString());
			cc.getLbl_NombreCliente().setText(citaCargada.getNombre()+citaCargada.getApellido());	
			cc.getLbl_Estado().setText(citaCargada.getEstado());
			
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

	public void cambiarColorCita(JPanel cita, String estado) {
		switch (estado) {
		case "Activa":
			cita.setBackground(Color.green);
			break;
		case "":
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

	public String[] getNombreColumnas(){
		return nombreColumnas;
	}

	public JButton getBtnAgregarCita() {
		return btnAgregarCita;
	}

	public void setBtnAgregarCita(JButton btnAgregarCita) {
		this.btnAgregarCita = btnAgregarCita;
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

	public DefaultTableModel getmodelCita()
	{
		return modelCita;
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

	public void  llenarTabla (List<CitaDTO> CitaslEnTabla) {
		this.getmodelCita().setRowCount(0); //Para vaciar la tabla
		this.getmodelCita().setColumnCount(0);
		this.getmodelCita().setColumnIdentifiers(this.getNombreColumnas());
		System.out.println("casi entro al for");
		for (CitaDTO c : CitaslEnTabla) {
			System.out.println("entro al for de vista");
			System.out.println(c.getHoraTurno()+"-"+c.getNombre()+"-"+c.getEstado());
			Time hora= c.getHoraTurno();
			String cliente=c.getNombre();
			String estado=c.getEstado();
			Object[] fila = {hora, cliente,estado};
			this.getmodelCita().addRow(fila);
		}
	}
}
