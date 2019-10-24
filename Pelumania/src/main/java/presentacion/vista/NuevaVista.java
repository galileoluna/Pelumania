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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import util.RowsRenderer;

public class NuevaVista implements Runnable {

	/*
	 * JComponentes de la instancia de la Vista
	 */
	private JFrame frame;
	
	private JMenuBar menuBar;
		private JMenu JM_Sucursales;
			private JMenuItem mntmGestionDeSucursales;
		private JMenu JM_Promociones;
			private JMenuItem mntmGestionDePromociones;
			private JMenuItem mntmVerPromocionesVigentes;
		private JMenu JM_Profesional;
			private JMenuItem mntmGestionDeProfesionales;
		private JMenu JM_Servicio;
			private JMenuItem mntmGestionDeServicios;
		private JMenu JM_Cliente;
			private JMenuItem mntmGestionDeClientes;
		private JMenu JM_Caja;
			private JMenuItem mntmConsultarCategorias;
			private JMenuItem mntmUtilizarCaja;
			
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
			
			/*
			 * Variables para manejar las Referencias*/
			private JLabel Lbl_Rojo;
			private JLabel Lbl_Naranja;
			private JLabel Lbl_Amarillo;
			private JLabel Lbl_Verde;
			private JLabel Lbl_Azul;
			private JLabel Lbl_Gris;
			private JLabel label_5;
			private JLabel label_6;
			private JLabel Lbl_Usuario;
			private JLabel Lbl_Sucursal;
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
			
			/*
			 * Variables para manejo de tabla Citas
			 * */
			private JTable tablaCitas;
			private DefaultTableModel modelCitas;
			private String[] nombreColumnas = {"Cliente","Precio en $",
					"Precio en USD","Hora Inicio", "Hora Fin", "Estado"};

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
				crearTablaCitas();
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
		
		mntmGestionDeClientes = new JMenuItem("Gestion de Clientes");
		JM_Cliente.add(mntmGestionDeClientes);
		
		JM_Servicio = new JMenu("Servicio");
		menuBar.add(JM_Servicio);
		
		mntmGestionDeServicios = new JMenuItem("Gestion de Servicios");
		JM_Servicio.add(mntmGestionDeServicios);
		
		JM_Profesional = new JMenu("Profesional");
		menuBar.add(JM_Profesional);
		
		mntmGestionDeProfesionales = new JMenuItem("Gestion de Profesionales");
		JM_Profesional.add(mntmGestionDeProfesionales);
		
		JM_Promociones = new JMenu("Promociones");
		menuBar.add(JM_Promociones);
		
		mntmGestionDePromociones = new JMenuItem("Gestion de Promociones");
		JM_Promociones.add(mntmGestionDePromociones);
		
		mntmVerPromocionesVigentes = new JMenuItem("Ver promociones Vigentes");
		JM_Promociones.add(mntmVerPromocionesVigentes);
		
		JM_Sucursales = new JMenu("Sucursales");
		menuBar.add(JM_Sucursales);
		
		mntmGestionDeSucursales = new JMenuItem("Gestion de Sucursales");
		JM_Sucursales.add(mntmGestionDeSucursales);
		
		JM_Caja = new JMenu("Caja");
		menuBar.add(JM_Caja);
		
		mntmConsultarCategorias = new JMenuItem("Consulta de categorías");
		JM_Caja.add(mntmConsultarCategorias);
		
		mntmUtilizarCaja = new JMenuItem("Utilizar Caja");
		JM_Caja.add(mntmUtilizarCaja);
		
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
			Lbl_Verde.setBackground(RowsRenderer.verde);
			Lbl_Verde.setBounds(10, 11, 20, 20);
			JPnl_Referencias.add(Lbl_Verde);
			
			lblCitasActivas = new JLabel("Citas Activas");
			lblCitasActivas.setBounds(40, 11, 157, 20);
			JPnl_Referencias.add(lblCitasActivas);
			//_________________________________________
			
			Lbl_Rojo = new JLabel();
			Lbl_Rojo.setBounds(10, 102, 20, 20);
			Lbl_Rojo.setBorder(new LineBorder(new Color (0,0,0)));
			Lbl_Rojo.setBackground(RowsRenderer.rojo);
			Lbl_Rojo.setOpaque(true);
			JPnl_Referencias.add(Lbl_Rojo);
			
			lblCitasCanceladas = new JLabel("Citas Canceladas");
			lblCitasCanceladas.setBounds(40, 102, 157, 20);
			JPnl_Referencias.add(lblCitasCanceladas);
			//_________________________________________
		
			Lbl_Naranja = new JLabel("");
			Lbl_Naranja.setBorder(new LineBorder(new Color (0,0,0)));
			Lbl_Naranja.setBackground(RowsRenderer.naranja);
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
			Lbl_Amarillo.setBackground(RowsRenderer.amarillo);
			Lbl_Amarillo.setBounds(10, 71, 20, 20);
			JPnl_Referencias.add(Lbl_Amarillo);
			
			LblCitasAReprogramar = new JLabel("Citas a Reprogramar");
			LblCitasAReprogramar.setBounds(40, 71, 157, 20);
			JPnl_Referencias.add(LblCitasAReprogramar);
			//_________________________________________

			Lbl_Azul = new JLabel("");
			Lbl_Azul.setBorder(new LineBorder(new Color (0,0,0)));
			Lbl_Azul.setBackground(RowsRenderer.azul);
			Lbl_Azul.setOpaque(true);
			Lbl_Azul.setBounds(207, 11, 20, 20);
			JPnl_Referencias.add(Lbl_Azul);
			
			lblCitasFinalizadas = new JLabel("Citas Finalizadas");
			lblCitasFinalizadas.setBounds(237, 11, 157, 20);
			JPnl_Referencias.add(lblCitasFinalizadas);
			//_________________________________________
			
			Lbl_Gris = new JLabel("");
			Lbl_Gris.setBorder(new LineBorder(new Color (0,0,0)));
			Lbl_Gris.setBackground(RowsRenderer.gris);
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
		btn_Agregar.setEnabled(false);
		JPnl_Botones.add(btn_Agregar);
		
		btn_Editar = new JButton("Editar");
		btn_Editar.setEnabled(false);
		JPnl_Botones.add(btn_Editar);
		
		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setEnabled(false);
		JPnl_Botones.add(btn_Cancelar);
		
		btn_Finalizar = new JButton("Finalizar");
		btn_Finalizar.setEnabled(false);
		JPnl_Botones.add(btn_Finalizar);
		
		btn_VerDetalle = new JButton("Ver Detalle");
		btn_VerDetalle.setEnabled(false);
		JPnl_Botones.add(btn_VerDetalle);
		
		btn_VerComprobante = new JButton("Ver Comprobante");
		btn_VerComprobante.setEnabled(false);
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
		Lbl_Usuario = new JLabel("UsuarioDefault");
		JPnl_Informacion.add(Lbl_Usuario);
	}
	
	public void crearLabelSucursal() {
		Lbl_Sucursal = new JLabel("SucursalPorDefecto");
		JPnl_Informacion.add(Lbl_Sucursal);
	}
	
	public void iniciarReloj() {
		h1 = new Thread(this);
		h1.start();
		crearLabelHora();
	}
	
	public void crearTablaCitas() {
		JPnl_TablaCitas.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane spServicios = new JScrollPane();
		spServicios.setBounds(10, 11, 693, 277);
		JPnl_TablaCitas.add(spServicios);
		
		modelCitas = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablaCitas = new JTable(modelCitas);
		RowsRenderer rr = new RowsRenderer(5);
		tablaCitas.setDefaultRenderer(Object.class, rr);

		tablaCitas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaCitas.getColumnModel().getColumn(0).setResizable(false);
		tablaCitas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaCitas.getColumnModel().getColumn(1).setResizable(false);
		tablaCitas.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaCitas.getColumnModel().getColumn(2).setResizable(false);
		tablaCitas.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaCitas.getColumnModel().getColumn(3).setResizable(false);
		tablaCitas.getColumnModel().getColumn(4).setPreferredWidth(100);
		tablaCitas.getColumnModel().getColumn(4).setResizable(false);

		spServicios.setViewportView(tablaCitas);
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

	public JFrame getFrame() {
		return frame;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}
	public JMenu getJM_Sucursales() {
		return JM_Sucursales;
	}

	public JMenuItem getMntmGestionDeSucursales() {
		return mntmGestionDeSucursales;
	}
	public JMenu getJM_Promociones() {
		return JM_Promociones;
	}

	public JMenuItem getMntmGestionDePromociones() {
		return mntmGestionDePromociones;
	}
	public JMenuItem getMntmVerPromocionesVigentes() {
		return mntmVerPromocionesVigentes;
	}

	public JMenu getJM_Profesional() {
		return JM_Profesional;
	}
	public JMenuItem getMntmGestionDeProfesionales() {
		return mntmGestionDeProfesionales;
	}

	public JMenu getJM_Servicio() {
		return JM_Servicio;
	}
	public JMenuItem getMntmGestionDeServicios() {
		return mntmGestionDeServicios;
	}

	public JMenu getJM_Cliente() {
		return JM_Cliente;
	}
	public JMenuItem getMntmGestionDeClientes() {
		return mntmGestionDeClientes;
	}

	public JMenu getJM_Caja() {
		return JM_Caja;
	}

	public void setJM_Caja(JMenu jM_Caja) {
		JM_Caja = jM_Caja;
	}

	public JMenuItem getMntmConsultarCategorias() {
		return mntmConsultarCategorias;
	}

	public void setMntmConsultarCategorias(JMenuItem mntmConsultarCategorias) {
		this.mntmConsultarCategorias = mntmConsultarCategorias;
	}

	public JMenuItem getMntmUtilizarCaja() {
		return mntmUtilizarCaja;
	}

	public void setMntmUtilizarCaja(JMenuItem mntmUtilizarCaja) {
		this.mntmUtilizarCaja = mntmUtilizarCaja;
	}

	public JLabel getLbl_Reloj() {
		return Lbl_Reloj;
	}

	public String getHora() {
		return hora;
	}

	public String getMinutos() {
		return minutos;
	}

	public String getSegundos() {
		return segundos;
	}

	public String getAmpm() {
		return ampm;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public Thread getH1() {
		return h1;
	}

	public JLabel getLbl_Rojo() {
		return Lbl_Rojo;
	}

	public JLabel getLbl_Naranja() {
		return Lbl_Naranja;
	}

	public JLabel getLbl_Amarillo() {
		return Lbl_Amarillo;
	}

	public JLabel getLbl_Verde() {
		return Lbl_Verde;
	}

	public JLabel getLbl_Azul() {
		return Lbl_Azul;
	}

	public JLabel getLbl_Gris() {
		return Lbl_Gris;
	}

	public JLabel getLabel_5() {
		return label_5;
	}

	public JLabel getLabel_6() {
		return label_6;
	}

	public JLabel getLblCitasActivas() {
		return lblCitasActivas;
	}

	public JLabel getLblCitasEnCurso() {
		return LblCitasEnCurso;
	}

	public JLabel getLblCitasAReprogramar() {
		return LblCitasAReprogramar;
	}

	public JLabel getLblCitasCanceladas() {
		return lblCitasCanceladas;
	}

	public JLabel getLblCitasFinalizadas() {
		return lblCitasFinalizadas;
	}

	public JLabel getLblCitasVencidas() {
		return lblCitasVencidas;
	}

	public JPanel getJPnl_Izquierdo() {
		return JPnl_Izquierdo;
	}

	public JPanel getJPnl_Calendario() {
		return JPnl_Calendario;
	}

	public JCalendar getCalendario() {
		return calendario;
	}

	public JPanel getJPnl_Referencias() {
		return JPnl_Referencias;
	}

	public JPanel getJPnl_Notificaciones() {
		return JPnl_Notificaciones;
	}

	public JPanel getJPnl_Informacion() {
		return JPnl_Informacion;
	}

	public JPanel getJPnl_Citas() {
		return JPnl_Citas;
	}

	public JPanel getJPanel_Filtros() {
		return JPanel_Filtros;
	}

	public JPanel getJPnl_TablaCitas() {
		return JPnl_TablaCitas;
	}

	public JPanel getJPnl_Botones() {
		return JPnl_Botones;
	}

	public JButton getBtn_Agregar() {
		return btn_Agregar;
	}

	public JButton getBtn_Editar() {
		return btn_Editar;
	}

	public JButton getBtn_Cancelar() {
		return btn_Cancelar;
	}

	public JButton getBtn_Finalizar() {
		return btn_Finalizar;
	}

	public JButton getBtn_VerDetalle() {
		return btn_VerDetalle;
	}

	public JButton getBtn_VerComprobante() {
		return btn_VerComprobante;
	}

	public JPanel getJPnl_Detalle() {
		return JPnl_Detalle;
	}

	public JTable getTablaCitas() {
		return tablaCitas;
	}

	public DefaultTableModel getModelCitas() {
		return modelCitas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	public JLabel getLblUsuario() {
		return Lbl_Usuario;
	}
	
	public JLabel getLblSucursal() {
		return Lbl_Sucursal;
	}
}
