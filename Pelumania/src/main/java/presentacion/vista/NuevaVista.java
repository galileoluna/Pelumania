package presentacion.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;

public class NuevaVista implements Runnable {

	/*
	 * JComponentes de la instancia de la Vista
	 */
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
				private JLabel Lbl_Reloj;
	
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
			private JPanel JPnl_Detalle;
			
			/*
			 * Variables globales para manejar la hora
			 * */
			String hora,minutos,segundos,ampm;
			Calendar calendar;    
			Thread h1;
			private JLabel Lbl_Rojo;
			private JLabel Lbl_Naranja;
			private JLabel Lbl_Amarillo;
			private JLabel Lbl_Verde;
			private JLabel Lbl_Azul;
			private JLabel Lbl_Gris;
			private JLabel label_5;
			private JLabel label_6;
			private JLabel lblCitasActivas;
			private JLabel LblCitasEnCurso;
			private JLabel LblCitasAReprogramar;
			private JLabel lblCitasCanceladas;
			private JLabel lblCitasFinalizadas;
			private JLabel lblCitasVencidas;
			
			/*
			 * Variables Globales para settear la vista 
			 */
			
			/*
			private UsuarioDTO usuario;
			private SucursalDTO sucursal;
			 */

	public NuevaVista() {
		initialize();
	}
	
	private void initialize() {
		crearFrame();
		
		crearBarraMenu();
		
		crearPanelIzquierdo();
			crearPanelCalendario();
			crearCalendario();
			crearPanelReferencias();
				crearLabelsReferencias();
			crearPanelNotificaciones();
			crearPanelInformacion();
				iniciarReloj();
				crearLabelUsuario();
				crearLabelSucursal();
		
		crearPanelCitas();
			crearPanelFiltros();
			crearPanelTablaCitas();
			crearPanelDetalle();
			crearPanelBotones();
			crearBotones();
			
		frame.setVisible(true);
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
		JPnl_Referencias.setLayout(null);
	}

	private void crearLabelsReferencias() {
		    //_________________________________________
			Lbl_Verde = new JLabel("");
			Lbl_Verde.setBorder(new LineBorder(new Color (0,0,0)));
			Lbl_Verde.setOpaque(true);
			Lbl_Verde.setBackground(Color.GREEN);
			Lbl_Verde.setBounds(10, 11, 20, 20);
			JPnl_Referencias.add(Lbl_Verde);
			
			lblCitasActivas = new JLabel("Citas Activas");
			lblCitasActivas.setBounds(40, 11, 157, 20);
			JPnl_Referencias.add(lblCitasActivas);
			//_________________________________________
			
			Lbl_Rojo = new JLabel();
			Lbl_Rojo.setBounds(10, 102, 20, 20);
			Lbl_Rojo.setBorder(new LineBorder(new Color (0,0,0)));
			Lbl_Rojo.setBackground(Color.RED);
			Lbl_Rojo.setOpaque(true);
			JPnl_Referencias.add(Lbl_Rojo);
			
			lblCitasCanceladas = new JLabel("Citas Canceladas");
			lblCitasCanceladas.setBounds(40, 102, 157, 20);
			JPnl_Referencias.add(lblCitasCanceladas);
			//_________________________________________
		
			Lbl_Naranja = new JLabel("");
			Lbl_Naranja.setBorder(new LineBorder(new Color (0,0,0)));
			Lbl_Naranja.setBackground(Color.ORANGE);
			Lbl_Naranja.setBounds(10, 40, 20, 20);
			Lbl_Naranja.setOpaque(true);
			JPnl_Referencias.add(Lbl_Naranja);
			
			LblCitasEnCurso = new JLabel("Citas En Curso");
			LblCitasEnCurso.setBounds(40, 40, 157, 20);
			JPnl_Referencias.add(LblCitasEnCurso);
			//_________________________________________
		
			Lbl_Amarillo = new JLabel("");
			Lbl_Amarillo.setBackground(Color.YELLOW);
			Lbl_Amarillo.setBorder(new LineBorder(new Color (0,0,0)));
			Lbl_Amarillo.setOpaque(true);
			Lbl_Amarillo.setBackground(Color.YELLOW);
			Lbl_Amarillo.setBounds(10, 71, 20, 20);
			JPnl_Referencias.add(Lbl_Amarillo);
			
			LblCitasAReprogramar = new JLabel("Citas a Reprogramar");
			LblCitasAReprogramar.setBounds(40, 71, 157, 20);
			JPnl_Referencias.add(LblCitasAReprogramar);
			//_________________________________________

			Lbl_Azul = new JLabel("");
			Lbl_Azul.setBorder(new LineBorder(new Color (0,0,0)));
			Lbl_Azul.setBackground(Color.BLUE);
			Lbl_Azul.setOpaque(true);
			Lbl_Azul.setBounds(207, 11, 20, 20);
			JPnl_Referencias.add(Lbl_Azul);
			
			lblCitasFinalizadas = new JLabel("Citas Finalizadas");
			lblCitasFinalizadas.setBounds(237, 11, 157, 20);
			JPnl_Referencias.add(lblCitasFinalizadas);
			//_________________________________________
			
			Lbl_Gris = new JLabel("");
			Lbl_Gris.setBorder(new LineBorder(new Color (0,0,0)));
			Lbl_Gris.setBackground(Color.LIGHT_GRAY);
			Lbl_Gris.setOpaque(true);
			Lbl_Gris.setBounds(207, 40, 20, 20);
			JPnl_Referencias.add(Lbl_Gris);
			
			lblCitasVencidas = new JLabel("Citas Vencidas");
			lblCitasVencidas.setBounds(237, 40, 157, 20);
			JPnl_Referencias.add(lblCitasVencidas);

			//_________________________________________
			label_5 = new JLabel("");
			label_5.setBorder(new LineBorder(new Color (0,0,0)));
			label_5.setBackground(Color.RED);
			label_5.setBounds(207, 71, 20, 20);
			JPnl_Referencias.add(label_5);

			//_________________________________________
			
			label_6 = new JLabel("");
			label_6.setBorder(new LineBorder(new Color (0,0,0)));
			label_6.setBackground(Color.RED);
			label_6.setBounds(207, 102, 20, 20);
			JPnl_Referencias.add(label_6);

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
		JPnl_Informacion.setBounds(0, 627, 415, 35);
		JPnl_Izquierdo.add(JPnl_Informacion);
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
		JPnl_TablaCitas.setBounds(0, 139, 917, 332);
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

	private void crearPanelDetalle() {
		JPnl_Detalle = new JPanel();
		JPnl_Detalle.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPnl_Detalle.setBounds(0, 471, 917, 151);
		JPnl_Citas.add(JPnl_Detalle);
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/images.jpg"));
		frame.setBounds(10,10,1380,740);
		frame.setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.getContentPane().setLayout(null);
	}

	public void crearLabelHora() {
		JPnl_Informacion.setLayout(new GridLayout(0, 3, 0, 0));
		Lbl_Reloj = new JLabel("");
		JPnl_Informacion.add(Lbl_Reloj);
	}
	
	public void crearLabelUsuario() {
		JLabel Lbl_Usuario = new JLabel("UsuarioDefault");
		JPnl_Informacion.add(Lbl_Usuario);
	}
	
	public void crearLabelSucursal() {
		JLabel Lbl_Sucursal = new JLabel("SucursalPorDefecto");
		JPnl_Informacion.add(Lbl_Sucursal);
	}
	
	public void iniciarReloj() {
		h1 = new Thread(this);
		h1.start();
		crearLabelHora();
	}
	
	@Override
	public void run() {
		Thread ct = Thread.currentThread();
		 while(ct == h1) {   
		  calcula();
		  Lbl_Reloj.setText(hora + ":" + minutos + ":" + segundos + " "+ampm);
		  try {
		   Thread.sleep(1000);
		  }catch(InterruptedException e) {}
		 }
	}
	
	public void calcula () {        
		Calendar calendario = new GregorianCalendar();
		Date fechaHoraActual = new Date();

		calendario.setTime(fechaHoraActual);
		ampm = calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";

		if(ampm.equals("PM")){
		 int h = calendario.get(Calendar.HOUR_OF_DAY)-12;
		 hora = h>9?""+h:"0"+h;
		}else{
		 hora = calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);            
		}
		minutos = calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
		segundos = calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND); 
		}
}
