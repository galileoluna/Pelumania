package presentacion.vista;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JComboBox;

public class VentanaAgregarCategoriaMovimientosCaja extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAgregarCategoriaMovimientosCaja INSTANCE;

	private JTextField txtNombreCategoria;

	private JLabel lblNombreCategoria;
	private JLabel lblTipoMovimiento;
	private JLabel lblEstado;
	

	private JButton btnAgregarCategoria;
	private JButton btnCancelar;
	private JComboBox<String> comboEstado;
	private JTextField txtEgreso;
	private JButton btnEditarCategoria;
	
	public static VentanaAgregarCategoriaMovimientosCaja getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaAgregarCategoriaMovimientosCaja();
		}
			return INSTANCE;
	}

	private VentanaAgregarCategoriaMovimientosCaja()
	{
		super();

		
		setTitle("Nueva categoria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 346, 354);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 309, 304);
		contentPane.add(panel);
		panel.setLayout(null);

		txtNombreCategoria = new JTextField();
		txtNombreCategoria.setBounds(132, 19, 167, 26);
		panel.add(txtNombreCategoria);
		txtNombreCategoria.setColumns(10);

		lblNombreCategoria = new JLabel("Nombre Categoria");
		lblNombreCategoria.setBounds(0, 19, 124, 26);
		panel.add(lblNombreCategoria);

		lblTipoMovimiento = new JLabel("Tipo de movimiento");
		lblTipoMovimiento.setBounds(0, 56, 124, 26);
		panel.add(lblTipoMovimiento);

		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(0, 93, 124, 26);
		panel.add(lblEstado);

		btnAgregarCategoria = new JButton("Agregar");
		btnAgregarCategoria.setBounds(66, 270, 89, 23);
		panel.add(btnAgregarCategoria);
		
		btnEditarCategoria = new JButton("Editar");
		btnEditarCategoria.setBounds(66, 270, 89, 23);
		panel.add(btnEditarCategoria);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(165, 270, 89, 23);
		panel.add(btnCancelar);
		
		comboEstado = new JComboBox<String>();
		comboEstado.setBounds(132, 96, 167, 23);
		panel.add(comboEstado);
		
		txtEgreso = new JTextField();
		txtEgreso.setText("Egreso");
		txtEgreso.setEnabled(false);
		txtEgreso.setBounds(132, 56, 167, 26);
		panel.add(txtEgreso);
		txtEgreso.setColumns(10);


		this.setVisible(false);
	}
	public static VentanaAgregarCategoriaMovimientosCaja getINSTANCE() {
		return INSTANCE;
	}

	public JTextField getTxtNombre() {
		return txtNombreCategoria;
	}

	public void setTxtNombreSucursal(JTextField txtNombreSucursal) {
		this.txtNombreCategoria = txtNombreSucursal;
	}

public JButton getBtnAgregarCategoria() {
		return btnAgregarCategoria;
	}

	public void setBtnAgregarSucursal(JButton btn_AgregarSucursal) {
		this.btnAgregarCategoria = btn_AgregarSucursal;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btn_Cancelar) {
		this.btnCancelar = btn_Cancelar;
	}

	public JComboBox<String> getComboEstado() {
		return comboEstado;
	}

	public void setComboEstado(JComboBox<String> comboEstado) {
		this.comboEstado = comboEstado;
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public void mostrarErrorCampos() {
		JOptionPane.showMessageDialog(new JFrame(), "Campos ingresados inválidos", "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}

	public void limpiarCampos() {
		this.getTxtNombre().setText(null);

	}
	
	public void cerrar(){
		this.dispose();
	}

	public JButton getBtnEditarCategoria() {
		return this.btnEditarCategoria;
		
	}
}