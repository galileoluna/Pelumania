package presentacion.vista;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

	private String[] nombreColumnas = {"Nombre", "Apellido", "Telefono",
			"Mail", "Puntos", "Estado", "Deuda"};
	private DefaultTableModel modelClientes;

	private JLabel lblFecha;
	private JTextField txtFecha;
	private JTextField txtNombre;
	private JTextField txtApellido;

	private JButton btnBuscarCliente;
	private JButton btnRegistrarCliente;
	private JComboBox<Integer> JCBoxHora;
	private JComboBox<Integer> JCBoxMinutos;
	private JComboBox<ServicioDTO> JCBoxServicio;

	private JLabel lblProfesional;
	private JComboBox<ProfesionalDTO> JCBoxProfesional;



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
		setBounds(100, 100, 428, 547);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 392, 486);
		contentPane.add(panel);
		panel.setLayout(null);

		btn_AgregarCita = new JButton("Agregar");
		btn_AgregarCita.setBounds(80, 452, 89, 23);
		panel.add(btn_AgregarCita);

		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setBounds(219, 452, 89, 23);
		panel.add(btn_Cancelar);

		modelClientes = new DefaultTableModel(null,nombreColumnas);

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

		JLabel lblServicio = new JLabel("Servicio:");
		lblServicio.setBounds(23, 208, 133, 23);
		panel.add(lblServicio);

		JCBoxServicio = new JComboBox<ServicioDTO>();
		JCBoxServicio.setBounds(100, 209, 237, 23);
		panel.add(JCBoxServicio);

		lblProfesional = new JLabel("Profesional");
		lblProfesional.setBounds(23, 248, 133, 23);
		panel.add(lblProfesional);

		JCBoxProfesional = new JComboBox<ProfesionalDTO>();
		JCBoxProfesional.setBounds(100, 249, 237, 23);
		panel.add(JCBoxProfesional);


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
		return modelClientes;
	}

	public void setModelClientes(DefaultTableModel modelClientes) {
		this.modelClientes = modelClientes;
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

	public JComboBox<ServicioDTO> getJCBoxServicio() {
		return JCBoxServicio;
	}

	public void setJCBoxServicio(JComboBox<ServicioDTO> jCBoxServicio) {
		JCBoxServicio = jCBoxServicio;
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

	public void mostrarVentana()
	{
		this.setVisible(true);
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
	}

	public void cargarServicios(List<ServicioDTO> serviciosEnTabla) {
		for (ServicioDTO s : serviciosEnTabla) {
			JCBoxServicio.addItem(s);
		}
	}

	public void cargarProfesionales(List<ProfesionalDTO> profesionalesEnTabla) {
		for (ProfesionalDTO p : profesionalesEnTabla) {
			JCBoxProfesional.addItem(p);
		}
	}

	public void cerrar()
	{
		this.JCBoxServicio.removeAllItems();
		this.JCBoxProfesional.removeAllItems();
		dispose();
	}
}