package presentacion.vista;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.ProfesionalDTO;
import dto.ServicioDTO;


public class VentanaAgregarCita extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAgregarCita INSTANCE;

	private JButton btn_AgregarCita;
	private JButton btn_Cancelar;

	private JLabel lblFecha;
	private JTextField txtFecha;
	private JTextField txtNombre;
	private JTextField txtApellido;

	private JButton btnBuscarCliente;
	private JButton btnRegistrarCliente;
	
	private JComboBox<Integer> JCBoxHora;
	private JComboBox<Integer> JCBoxMinutos;
	
	private JTable tablaServicios;
	private DefaultTableModel modelServicios;
	private String[] nombreColumnas = {"Nombre","Precio",
			"USD","Duracion"};

	private JLabel lblProfesional;
	private JComboBox<ProfesionalDTO> JCBoxProfesional;

	private LocalDate fechaCita;
	private Integer idCliente = -1;
	
	private JLabel lblNombreProfesional;
	private JLabel lblServiciosQueRealiza;
	private JLabel lblServiciosElegidos;


	public static VentanaAgregarCita getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaAgregarCita();
			return new VentanaAgregarCita();
		} else {
			return INSTANCE;
		}
	}

	private VentanaAgregarCita()
	{
		super();

		setTitle("Nueva Cita");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 659, 599);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 623, 538);
		contentPane.add(panel);
		panel.setLayout(null);

		btn_AgregarCita = new JButton("Agregar");
		btn_AgregarCita.setBounds(176, 504, 89, 23);
		panel.add(btn_AgregarCita);

		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setBounds(342, 504, 89, 23);
		panel.add(btn_Cancelar);

		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(23, 11, 133, 23);
		panel.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setBounds(129, 11, 208, 23);
		panel.add(txtFecha);
		txtFecha.setColumns(10);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(77, 157, 59, 23);
		panel.add(lblHora);

		JCBoxHora = new JComboBox<Integer>();
		JCBoxHora.setBounds(116, 157, 53, 23);
		cargarHora(JCBoxHora);
		panel.add(JCBoxHora);

		JLabel lblMinutos = new JLabel("Minutos:");
		lblMinutos.setBounds(180, 157, 59, 23);
		panel.add(lblMinutos);

		JCBoxMinutos = new JComboBox<Integer>();
		JCBoxMinutos.setBounds(229, 157, 59, 23);
		cargarMinutos(JCBoxMinutos);
		panel.add(JCBoxMinutos);

		JLabel lblNewLabel = new JLabel("Ese horario no se encuentra disponible!");
		lblNewLabel.setBounds(100, 183, 208, 14);
		lblNewLabel.setVisible(false);
		panel.add(lblNewLabel);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(23, 45, 133, 23);
		panel.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(23, 79, 133, 23);
		panel.add(lblApellido);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(129, 45, 208, 23);
		panel.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(129, 79, 208, 23);
		panel.add(txtApellido);

		btnBuscarCliente = new JButton("Buscar Cliente");
		btnBuscarCliente.setBounds(196, 113, 141, 23);
		panel.add(btnBuscarCliente);

		btnRegistrarCliente = new JButton("Registrar Cliente");
		btnRegistrarCliente.setBounds(28, 113, 141, 23);
		panel.add(btnRegistrarCliente);

		lblProfesional = new JLabel("Profesional");
		lblProfesional.setBounds(23, 208, 133, 23);
		panel.add(lblProfesional);

		JCBoxProfesional = new JComboBox<ProfesionalDTO>();
		JCBoxProfesional.setBounds(100, 209, 237, 23);
		panel.add(JCBoxProfesional);

		JScrollPane spServicios = new JScrollPane();
		spServicios.setBounds(10, 271, 287, 188);
		panel.add(spServicios);

		modelServicios = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablaServicios = new JTable(modelServicios);


		tablaServicios.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaServicios.getColumnModel().getColumn(0).setResizable(false);
		tablaServicios.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(1).setResizable(false);
		tablaServicios.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(2).setResizable(false);
		tablaServicios.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaServicios.getColumnModel().getColumn(3).setResizable(false);

		spServicios.setViewportView(tablaServicios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 271, 287, 188);
		panel.add(scrollPane);
		
		lblServiciosElegidos = new JLabel("Servicios Elegidos");
		lblServiciosElegidos.setBounds(315, 246, 208, 14);
		panel.add(lblServiciosElegidos);
		
		lblServiciosQueRealiza = new JLabel("Servicios que realiza: ");
		lblServiciosQueRealiza.setBounds(10, 246, 126, 14);
		panel.add(lblServiciosQueRealiza);
		
		lblNombreProfesional = new JLabel("");
		lblNombreProfesional.setBounds(142, 246, 133, 14);
		panel.add(lblNombreProfesional);

		this.setVisible(false);
	}


	public static VentanaAgregarCita getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(VentanaAgregarCita iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public JButton getBtnAgregarCita () {
		return this.btn_AgregarCita;
	}
	public void setBtnAgregarCita(JButton btn_AgregarCita) {
		this.btn_AgregarCita = btn_AgregarCita;
	}

	public JButton getBtn_Cancelar() {
		return btn_Cancelar;
	}

	public void setBtn_Cancelar(JButton btn_Cancelar) {
		this.btn_Cancelar = btn_Cancelar;
	}

	public DefaultTableModel getModelClientes() {
		return modelServicios;
	}

	public void setModelClientes(DefaultTableModel modelClientes) {
		this.modelServicios = modelClientes;
	}

	public JButton getBtnBuscarCliente() {
		return btnBuscarCliente;
	}

	public void setBtnBuscarCliente(JButton btnBuscarCliente) {
		this.btnBuscarCliente = btnBuscarCliente;
	}

	public JButton getBtnRegistrarCliente() {
		return btnRegistrarCliente;
	}

	public void setBtnRegistrarCliente(JButton btnRegistrarCliente) {
		this.btnRegistrarCliente = btnRegistrarCliente;
	}

	public JComboBox<ProfesionalDTO> getJCBoxProfesional() {
		return JCBoxProfesional;
	}

	public void setJCBoxProfesional(JComboBox<ProfesionalDTO> jCBoxProfesional) {
		JCBoxProfesional = jCBoxProfesional;
	}

	public JTextField getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(JTextField txtFecha) {
		this.txtFecha = txtFecha;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JComboBox<Integer> getJCBoxHora() {
		return JCBoxHora;
	}

	public void setJCBoxHora(JComboBox<Integer> jCBoxHora) {
		JCBoxHora = jCBoxHora;
	}

	public JComboBox<Integer> getJCBoxMinutos() {
		return JCBoxMinutos;
	}

	public void setJCBoxMinutos(JComboBox<Integer> jCBoxMinutos) {
		JCBoxMinutos = jCBoxMinutos;
	}

	public LocalDate getFechaCita() {
		return fechaCita;
	}

	public void setFechaCita(LocalDate fechaCita) {
		this.fechaCita = fechaCita;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public JLabel getLblNombreProfesional() {
		return lblNombreProfesional;
	}

	public void setLblNombreProfesional(JLabel lblNombreProfesional) {
		this.lblNombreProfesional = lblNombreProfesional;
	}

	private void cargarHora(JComboBox<Integer> hora) {
		for(int i=0;i<=23;i++) {
			hora.addItem(i);
		}
	}

	private void cargarMinutos(JComboBox<Integer> min) {
		for(int i=0;i<=59;i++) {
			min.addItem(i);
		}
	}

	public void mostrarErrorCampos() {
		JOptionPane.showMessageDialog(new JFrame(), "Campos ingresados inválidos", "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}

	public void cargarFecha(int anio,int mes, int dia) {
		LocalDate fecha = LocalDate.of(anio, mes, dia);
		String S_mes = fecha.getMonth().toString();
		txtFecha.setText(dia + " de "+ S_mes+" de "+anio);
		this.setFechaCita(fecha);
	}
	

	public JTable getTablaServicios() {
		return tablaServicios;
	}

	public void setTablaServicios(JTable tablaServicios) {
		this.tablaServicios = tablaServicios;
	}

	public void cargarServicios(List<ServicioDTO> serviciosEnTabla) {
		for (ServicioDTO s : serviciosEnTabla) {
			
		}
	}

	public void cargarProfesionales(List<ProfesionalDTO> profesionalesEnTabla) {
		for (ProfesionalDTO p : profesionalesEnTabla) {
			JCBoxProfesional.addItem(p);
		}
	}
	
	public void limpiarCampos() {
		this.getTxtNombre().setText(null);
		this.getTxtApellido().setText(null);
		
		this.getTxtNombre().setEditable(true);
		this.getTxtApellido().setEditable(true);
		
		this.getJCBoxHora().setSelectedItem(null);
		this.getJCBoxMinutos().setSelectedItem(null);
	}

	public void cerrar()
	{
		this.JCBoxProfesional.removeAllItems();
		limpiarCampos();
		dispose();
	}
}