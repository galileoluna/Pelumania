package presentacion.vista;

import java.math.BigDecimal;
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

import dto.ClienteDTO;


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
	private JButton btnCargarCliente;

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
		
		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(23, 186, 133, 23);
		panel.add(lblHora);
		
		JComboBox CBoxHora = new JComboBox();
		CBoxHora.setBounds(129, 186, 208, 23);
		panel.add(CBoxHora);
		
		JLabel lblNewLabel = new JLabel("Ese horario no se encuentra disponible!");
		lblNewLabel.setBounds(129, 205, 208, 14);
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
		btnBuscarCliente.setBounds(219, 113, 118, 23);
		panel.add(btnBuscarCliente);
		
		btnCargarCliente = new JButton("Cargar Cliente");
		btnCargarCliente.setBounds(80, 113, 118, 23);
		panel.add(btnCargarCliente);


		this.setVisible(false);
	}


	public static VentanaAgregarCita getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(VentanaAgregarCita iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public void setBtnAgregarServicio(JButton btn_AgregarServicio) {
		this.btn_AgregarCita = btn_AgregarServicio;
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

	public void mostrarVentana()
	{
		this.setVisible(true);
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

	public void llenarTabla(List<ClienteDTO> clientesEnTabla) {
		this.getModelClientes().setRowCount(0); //Para vaciar la tabla
		this.getModelClientes().setColumnCount(0);
		this.getModelClientes().setColumnIdentifiers(this.getNombreColumnas());

		for (ClienteDTO c : clientesEnTabla)
		{
			String nombre = c.getNombre();
			String apellido = c.getApellido();
			String telefono = c.getTelefono();
			String mail = c.getMail();
			int puntos = c.getPuntos();
			String estadoCliente = c.getEstadoCliente();
			BigDecimal deuda = c.getDeuda();
			Object[] fila = {nombre, apellido, telefono, mail, puntos, estadoCliente, deuda};
			this.getModelClientes().addRow(fila);
		}

	}
	private String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void cerrar()
	{
		this.dispose();
	}
}