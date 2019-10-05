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
	private JButton btnAgregarCliente;

	private JMenu menu;
	private JMenu mnServicio;
	private JMenuItem mntmConsultarServicios;
	private JMenu mnProfesional;
	private JMenuItem menuConsultarProf;
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

		menu = new JMenu("");
		menuBar.add(menu);


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

		btnAgregarCliente = new JButton("Agregar Cliente");
		btnAgregarCliente.setBounds(620, 60, 152, 39);
		panel.add(btnAgregarCliente);

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
		// HAY QUE BORRARLO, ES SOLO PARA PROBAR
		//-----------------------------------------------
		btnAgregarCita = new JButton("Agregar Cita");
		/*
		btnAgregarCita.addActionListener(new ActionListener() {
			private VentanaAgregarCita ventanaAgregarCita = VentanaAgregarCita.getInstance();

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ventanaAgregarCita.mostrarVentana();

			}
		});
		 */
		btnAgregarCita.setBounds(792, 60, 152, 39);
		panel.add(btnAgregarCita);
	}
	//-----------------------------------------------

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
	public JButton getBtnAgregarCliente () {
		return btnAgregarCliente;
	}


	public JMenuItem getMnItmConsultarServicios() {
		return mntmConsultarServicios;
	}

	public JMenuItem getMenuProfesional() {
		return menuConsultarProf;
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
			LocalTime hora=c.getHora();
			String cliente=c.getNombre()+" "+c.getApellido();
			String estado=c.getEstado();
			Object[] fila = {hora, cliente,estado};
			this.getmodelCita().addRow(fila);
		}
	}

}
