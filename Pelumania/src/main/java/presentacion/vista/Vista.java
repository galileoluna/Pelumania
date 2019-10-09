package presentacion.vista;

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
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import dto.CitaDTO;
import persistencia.conexion.Conexion;

public class Vista {

	private JFrame frame;
	private JCalendar calendario;
	private JButton btnServicios;

	private JMenu mnServicio;
	private JMenuItem mntmConsultarServicios;
	private JMenu mnProfesional;
	private JMenuItem menuConsultarProf;
	private JMenuItem menuConsultaClientes;
	private JMenu mnPromocion;
	private JMenuItem menuConsultaPromo;
	private JMenu mnCliente;
	private DefaultTableModel modelCita;
	private  String[] nombreColumnas = {"Horario","Cliente","Estado"};
	private JTable table;
	private JButton btnAgregarCita;


	public Vista()
	{
		super();
		initialize();
	}

	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 40, 1065, 683);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/images.jpg"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setTitle("Hair & Head");

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 995, 21);
		frame.getContentPane().add(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

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

		menuConsultaPromo= new JMenuItem("Consultar Promociones");
		mnPromocion.add(menuConsultaPromo);


		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1039, 633);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		calendario = new JCalendar();
		calendario.setBounds(10, 33, 578, 600);
		calendario.getYearChooser().getSpinner().setEnabled(false);
		calendario.setTodayButtonVisible(true);
		panel.add(calendario);
		calendario.setVisible(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(620, 110, 409, 512);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setColumnHeaderView(table);

		modelCita = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(modelCita);

		table.getColumnModel().getColumn(0).setPreferredWidth(103);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);

		scrollPane.setViewportView(table);

		btnAgregarCita = new JButton("Agregar Cita");
		btnAgregarCita.setBounds(620, 60, 117, 39);
		panel.add(btnAgregarCita);
		
		JButton btnCancelarCita = new JButton("Cancelar Cita");
		btnCancelarCita.setBounds(767, 60, 117, 39);
		panel.add(btnCancelarCita);
		
		JButton btnBorrarCita = new JButton("Borrar Cita");
		btnBorrarCita.setBounds(912, 60, 117, 39);
		panel.add(btnBorrarCita);
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
	
	public JMenuItem getMenuPromocion() {
		return menuConsultaPromo;
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

	public void  llenarTabla (List<CitaDTO> CitaslEnTabla) {
		this.getmodelCita().setRowCount(0); //Para vaciar la tabla
		this.getmodelCita().setColumnCount(0);
		this.getmodelCita().setColumnIdentifiers(this.getNombreColumnas());
		for (CitaDTO c : CitaslEnTabla) {
			Time hora= c.getHoraTurno();
			String cliente=c.getNombre();
			String estado=c.getEstado();
			Object[] fila = {hora, cliente,estado};
			this.getmodelCita().addRow(fila);
		}
	}
}
