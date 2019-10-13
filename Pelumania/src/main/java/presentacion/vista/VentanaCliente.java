
package presentacion.vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dto.ClienteDTO;
import javax.swing.JComboBox;

public class VentanaCliente extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTable tablaClientes;
	private static VentanaCliente INSTANCE;
	private DefaultTableModel modelClientes;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	
	private JTextField buscador;
	private JButton btnBuscar;
	private JComboBox variableBuscar;
	
	private String[] nombreColumnas = {"Nombre", "Apellido", "Telefono",
			"Mail", "Puntos", "Estado", "Deuda"};
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtApellido;
	private JTextField txtMail;
	private VentanaCliente()
	{
		super();
		initialize();
	}

	public static VentanaCliente getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaCliente();
			return new VentanaCliente();
		} else {
			return INSTANCE;
		}
	}


	private void initialize()
	{
		this.setBounds(100, 100, 803, 560);
		this.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 787, 522);
		this.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spClientes = new JScrollPane();
		spClientes.setBounds(10, 234, 767, 218);
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

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(338, 168, 89, 34);
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}

		});
		panel.add(btnAgregar);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(10, 473, 129, 34);
		panel.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(140, 473, 129, 34);
		panel.add(btnBorrar);
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBuscarPor.setBounds(280, 473, 129, 34);
		panel.add(lblBuscarPor);
		
		variableBuscar = new JComboBox();
		variableBuscar.setBounds(360, 473, 129, 34);
		panel.add(variableBuscar);
		variableBuscar.addItem("Todos");
		variableBuscar.addItem("Nombre");
		variableBuscar.addItem("Apellido");
		variableBuscar.addItem("EstadoCliente");
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(625, 473, 129, 34);
		panel.add(btnBuscar);
		
		buscador = new JTextField();
		buscador.setBounds(493, 473, 129, 34);
		panel.add(buscador);
		buscador.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 113, 14);
		panel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 26);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 56, 113, 14);
		panel.add(lblApellido);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 92, 164, 26);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 101, 113, 14);
		panel.add(lblTelfono);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(133, 47, 164, 26);
		panel.add(txtApellido);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(10, 142, 113, 14);
		panel.add(lblMail);

		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(133, 136, 164, 26);
		panel.add(txtMail);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 181, 113, 14);
		panel.add(lblEstado);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(133, 175, 164, 20);
		comboBox.addItem("activo");
		comboBox.addItem("vip");
		comboBox.addItem("moroso");
		comboBox.addItem("inactivo");
		panel.add(comboBox);
		
		
		
		JLabel lblImagen =  new JLabel();
		lblImagen.setBounds(518, 27, 200, 167);		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("imagenes/cliente.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		lblImagen.setIcon(imageIcon);
		panel.add(lblImagen);
		
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

	public void setModelServicios(DefaultTableModel modelClientes) {
		this.modelClientes = modelClientes;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	public JComboBox getVariableBuscar() {
		return variableBuscar;
	}
	
	public JTextField getBuscador() {
		return buscador;
	}

	public JTextField getTxtNombre()
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono()
	{
		return txtTelefono;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JTextField getTxtMail() {
		return txtMail;
	}

	public void setTxtMail(JTextField txtMail) {
		this.txtMail = txtMail;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public void llenarTabla(List<ClienteDTO> clientesEnTabla) {
		this.getModelClientes().setRowCount(0); //Para vaciar la tabla
		this.getModelClientes().setColumnCount(0);
		this.getModelClientes().setColumnIdentifiers(this.getNombreColumnas());
		
		JComboBox<String> comboEstados = new JComboBox<String>();
		comboEstados.addItem("activo");
		comboEstados.addItem("vip");
		comboEstados.addItem("moroso");
		comboEstados.addItem("inactivo");
		
		TableColumn columnaEstados = this.tablaClientes.getColumnModel().getColumn(5);
		columnaEstados.setCellEditor(new DefaultCellEditor(comboEstados));

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

	public void mostrar() {
		this.setVisible(true);

	}
	public void limpiarInputs()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.txtApellido.setText(null);
		this.txtMail.setText(null);
	}

	public void mostrarErrorCamposInvalidos() {
		JOptionPane.showMessageDialog(new JFrame(), "Campos ingresados inválidos", "Dialog",
				JOptionPane.ERROR_MESSAGE);

	}

	public void mostrarErrorSinSeleccionar() {
		JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar un cliente", "Dialog",
				JOptionPane.ERROR_MESSAGE);

	}

	public void mostrarExitoEditar() {
		JOptionPane.showMessageDialog(new JFrame(), "El cliente fue editado con éxito","Dialog",JOptionPane.INFORMATION_MESSAGE);
		
	}

	public void mostrarExitoAlta() {
		JOptionPane.showMessageDialog(new JFrame(), "El cliente fue dado de alta con éxito","Dialog",JOptionPane.INFORMATION_MESSAGE);
		
	}
}
