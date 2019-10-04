package presentacion.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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

import dto.ClienteDTO;


public class VentanaAgregarCita extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAgregarCita INSTANCE;

	private JButton btn_AgregarCita;
	private JButton btn_Cancelar;
	private JTextField textField;

	private String[] nombreColumnas = {"Nombre", "Apellido", "Telefono",
			"Mail", "Puntos", "Estado", "Deuda"};
	private JTable tablaClientes;
	private DefaultTableModel modelClientes;

	JComboBox<String> CBoxColumna;

	private static List<String> filtrosColumnas = new ArrayList<String>(Arrays.asList("ID","Nombre","Apellido","Telefono","Mail"));

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

	/**
	 *
	 */
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

		JLabel lblBuscarPor = new JLabel("Buscar por: ");
		lblBuscarPor.setBounds(23, 30, 133, 23);
		panel.add(lblBuscarPor);

		CBoxColumna = new JComboBox<String>();
		CBoxColumna.setBounds(130, 30, 179, 23);
		panel.add(CBoxColumna);
		cargarDesplegables();

		textField = new JTextField();
		textField.setBounds(23, 64, 347, 23);
		panel.add(textField);
		textField.setColumns(10);

		JScrollPane spClientes = new JScrollPane();
		spClientes.setBounds(23, 105, 347, 143);
		panel.add(spClientes);

		modelClientes = new DefaultTableModel(null,nombreColumnas);
		tablaClientes = new JTable(modelClientes);

		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaClientes.getColumnModel().getColumn(0).setResizable(false);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(10);
		tablaClientes.getColumnModel().getColumn(1).setResizable(false);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(2).setResizable(false);
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(10);
		tablaClientes.getColumnModel().getColumn(3).setResizable(false);
		tablaClientes.getColumnModel().getColumn(4).setPreferredWidth(10);
		tablaClientes.getColumnModel().getColumn(4).setResizable(false);
		tablaClientes.getColumnModel().getColumn(5).setPreferredWidth(30);
		tablaClientes.getColumnModel().getColumn(5).setResizable(false);
		tablaClientes.getColumnModel().getColumn(6).setPreferredWidth(30);
		tablaClientes.getColumnModel().getColumn(6).setResizable(false);

		spClientes.setViewportView(tablaClientes);


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


	public JTable getTablaClientes() {
		return tablaClientes;
	}

	public void setTablaClientes(JTable tablaClientes) {
		this.tablaClientes = tablaClientes;
	}

	public DefaultTableModel getModelClientes() {
		return modelClientes;
	}

	public void setModelClientes(DefaultTableModel modelClientes) {
		this.modelClientes = modelClientes;
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public void mostrarErrorCampos() {
		JOptionPane.showMessageDialog(new JFrame(), "Campos ingresados inválidos", "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}

	public void cargarDesplegables() {
		for (String campo : filtrosColumnas) {
			CBoxColumna.addItem(campo);
		}
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
		this.CBoxColumna.removeAllItems();
		this.dispose();
	}
}