package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridLayout;

public class NuevaVista {

	private JFrame frame;
	
	private JMenuBar menuBar;
		private JMenu JM_Sucursales;
		private JMenu JM_Promociones;
		private JMenu JM_Profesional;
		private JMenu JM_Servicio;
		private JMenu JM_Cliente;
		
	private JPanel JPnl_Izquierdo;
	
		private JPanel JPnl_Calendario;
			private JCalendar calendario;
		
			private JPanel JPnl_Referencias;	
			private JPanel JPnl_Notificaciones;
			private JPanel JPnl_Informacion;
	
	private JPanel JPnl_Citas;
		private JPanel JPanel_Filtros;
		private JPanel JPnl_TablaCitas;

	
		private JPanel JPnl_Botones;
			private JButton btn_Agregar;
			private JButton btn_Editar;
			private JButton btn_Cancelar;
			private JButton btn_Finalizar;
			private JButton btn_VerDetalle;
			private JButton btn_VerComprobante;


	public NuevaVista() {
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		crearFrame();
		
		crearBarraMenu();
		
		crearPanelIzquierdo();
			crearPanelCalendario();
			crearCalendario();
			crearPanelReferencias();
			crearPanelNotificaciones();
			crearPanelInformacion();
		
		crearPanelCitas();
			crearPanelFiltros();
			crearPanelTablaCitas();
			crearPanelBotones();
			crearBotones();
	}

	private void crearBarraMenu() {
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		crearOpcionesMenu();
	}

	private void crearOpcionesMenu() {
		JM_Cliente = new JMenu("Cliente");
		menuBar.add(JM_Cliente);
		
		JM_Servicio = new JMenu("Servicio");
		menuBar.add(JM_Servicio);
		
		JM_Profesional = new JMenu("Profesional");
		menuBar.add(JM_Profesional);
		
		JM_Promociones = new JMenu("Promociones");
		menuBar.add(JM_Promociones);
		
		JM_Sucursales = new JMenu("Sucursales");
		menuBar.add(JM_Sucursales);
	}

	private void crearCalendario() {
		calendario = new JCalendar();
		calendario.setWeekOfYearVisible(false);
		calendario.setTodayButtonVisible(true);
		JPnl_Calendario.add(calendario);
	}

	private void crearPanelCalendario() {
		JPnl_Calendario = new JPanel();
		JPnl_Calendario.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Calendario.setBounds(0, 0, 415, 293);
		JPnl_Izquierdo.add(JPnl_Calendario);
		JPnl_Calendario.setLayout(new GridLayout(0, 1, 0, 0));
	}

	private void crearPanelReferencias() {
		JPnl_Referencias = new JPanel();
		JPnl_Referencias.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Referencias.setBounds(0, 292, 415, 133);
		JPnl_Izquierdo.add(JPnl_Referencias);
	}

	private void crearPanelNotificaciones() {
		JPnl_Notificaciones = new JPanel();
		JPnl_Notificaciones.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Notificaciones.setBounds(0, 424, 415, 203);
		JPnl_Izquierdo.add(JPnl_Notificaciones);
	}

	private void crearPanelInformacion() {
		JPnl_Informacion = new JPanel();
		JPnl_Informacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Informacion.setBounds(0, 623, 415, 39);
		JPnl_Izquierdo.add(JPnl_Informacion);
		JPnl_Informacion.setLayout(new BoxLayout(JPnl_Informacion, BoxLayout.X_AXIS));
	}

	private void crearPanelIzquierdo() {
		JPnl_Izquierdo = new JPanel();
		JPnl_Izquierdo.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Izquierdo.setBounds(10, 11, 415, 662);
		frame.getContentPane().add(JPnl_Izquierdo);
		JPnl_Izquierdo.setLayout(null);
	}

	private void crearPanelTablaCitas() {
		JPnl_TablaCitas = new JPanel();
		JPnl_TablaCitas.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_TablaCitas.setBounds(0, 139, 917, 483);
		JPnl_Citas.add(JPnl_TablaCitas);
	}

	private void crearPanelFiltros() {
		JPanel_Filtros = new JPanel();
		JPanel_Filtros.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel_Filtros.setBounds(0, 0, 917, 139);
		JPnl_Citas.add(JPanel_Filtros);
	}

	private void crearBotones() {
		btn_Agregar = new JButton("Agregar");
		JPnl_Botones.add(btn_Agregar);
		
		btn_Editar = new JButton("Editar");
		JPnl_Botones.add(btn_Editar);
		
		btn_Cancelar = new JButton("Cancelar");
		JPnl_Botones.add(btn_Cancelar);
		
		btn_Finalizar = new JButton("Finalizar");
		JPnl_Botones.add(btn_Finalizar);
		
		btn_VerDetalle = new JButton("Ver Detalle");
		JPnl_Botones.add(btn_VerDetalle);
		
		btn_VerComprobante = new JButton("Ver Comprobante");
		JPnl_Botones.add(btn_VerComprobante);
	}

	private void crearPanelBotones() {
		JPnl_Botones = new JPanel();
		JPnl_Botones.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Botones.setBounds(0, 621, 917, 41);
		JPnl_Citas.add(JPnl_Botones);
		JPnl_Botones.setLayout(new GridLayout(0, 6, 0, 0));
	}

	private void crearPanelCitas() {
		JPnl_Citas = new JPanel();
		JPnl_Citas.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Citas.setBounds(435, 11, 917, 662);
		frame.getContentPane().add(JPnl_Citas);
		JPnl_Citas.setLayout(null);
	}

	private void crearFrame() {
		frame = new JFrame();
		frame.setTitle("Hair & Head");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
	}
}
