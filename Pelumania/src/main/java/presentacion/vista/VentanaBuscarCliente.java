package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.ClienteDTO;

public class VentanaBuscarCliente extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaBuscarCliente INSTANCE;

	private JButton btn_Cancelar;
	private JTextField textField;

	private String[] nombreColumnas = {"Nombre", "Apellido", "Telefono",
			"Mail", "Puntos", "Estado", "Deuda"};
	private JTable tablaClientes;
	private DefaultTableModel modelClientes;

	JComboBox<String> CBoxBuscarPor;

	private static List<String> filtrosColumnas = new ArrayList<String>(Arrays.asList("ID","Nombre","Apellido","Telefono","Mail"));
	private JButton btnSeleccionarCliente;

	public static VentanaBuscarCliente getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaBuscarCliente();
			return new VentanaBuscarCliente();
		} else {
			return INSTANCE;
		}
	}
	
	private VentanaBuscarCliente()
	{
		super();

		setTitle("Buscar Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 735, 318);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 699, 257);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBuscarPor = new JLabel("Buscar por: ");
		lblBuscarPor.setBounds(23, 11, 133, 23);
		panel.add(lblBuscarPor);

		CBoxBuscarPor = new JComboBox<String>();
		CBoxBuscarPor.setBounds(162, 11, 208, 23);
		panel.add(CBoxBuscarPor);
		cargarDesplegables();

		textField = new JTextField();
		textField.setBounds(23, 45, 347, 23);
		panel.add(textField);
		textField.setColumns(10);

		JScrollPane spClientes = new JScrollPane();
		spClientes.setBounds(23, 79, 666, 143);
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

		btnSeleccionarCliente = new JButton("Seleccionar Cliente");
		btnSeleccionarCliente.setBounds(571, 233, 118, 23);
		panel.add(btnSeleccionarCliente);

		this.setVisible(false);
	}

	public static VentanaBuscarCliente getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(VentanaBuscarCliente iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public JButton getBtnSeleccionarCliente() {
		return btnSeleccionarCliente;
	}

	public void setBtnSeleccionarCliente(JButton btnSeleccionarCliente) {
		this.btnSeleccionarCliente = btnSeleccionarCliente;
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
	
	public void cargarDesplegables() {
		for (String campo : filtrosColumnas) {
			CBoxBuscarPor.addItem(campo);
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
		this.dispose();
	}
}