package presentacion.vista;


import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.TextPrompt;
import javax.swing.JComboBox;

public class VentanaAgregarCategoriaMovimientosCaja extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAgregarCategoriaMovimientosCaja INSTANCE;

	private JTextField txtNombreSucursal;

	private JLabel lbl_NombreCategoria;
	private JLabel lbl_TipoMovimiento;
	private JLabel lbl_Estado;
	

	private JButton btn_AgregarSucursal;
	private JButton btn_Cancelar;
	private JComboBox<String> comboEstado;
	private JTextField txtEgreso;
	
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

		setTitle("Nueva sucursal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 346, 354);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 309, 304);
		contentPane.add(panel);
		panel.setLayout(null);

		txtNombreSucursal = new JTextField();
		txtNombreSucursal.setBounds(132, 19, 167, 26);
		panel.add(txtNombreSucursal);
		txtNombreSucursal.setColumns(10);

		lbl_NombreCategoria = new JLabel("Nombre Categoria");
		lbl_NombreCategoria.setBounds(0, 19, 124, 26);
		panel.add(lbl_NombreCategoria);

		lbl_TipoMovimiento = new JLabel("Tipo de movimiento");
		lbl_TipoMovimiento.setBounds(0, 56, 124, 26);
		panel.add(lbl_TipoMovimiento);

		lbl_Estado = new JLabel("Estado");
		lbl_Estado.setBounds(0, 93, 124, 26);
		panel.add(lbl_Estado);

		btn_AgregarSucursal = new JButton("Agregar");
		btn_AgregarSucursal.setBounds(66, 270, 89, 23);
		panel.add(btn_AgregarSucursal);

		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setBounds(165, 270, 89, 23);
		panel.add(btn_Cancelar);
		
		comboEstado = new JComboBox();
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
		return txtNombreSucursal;
	}

	public void setTxtNombreSucursal(JTextField txtNombreSucursal) {
		this.txtNombreSucursal = txtNombreSucursal;
	}

public JButton getBtnAgregarSucursal() {
		return btn_AgregarSucursal;
	}

	public void setBtnAgregarSucursal(JButton btn_AgregarSucursal) {
		this.btn_AgregarSucursal = btn_AgregarSucursal;
	}

	public JButton getBtn_Cancelar() {
		return btn_Cancelar;
	}

	public void setBtn_Cancelar(JButton btn_Cancelar) {
		this.btn_Cancelar = btn_Cancelar;
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
//		this.getTxtNombreSucursal().setText(null);
//		this.getTxtDireccion().setText(null);
//		this.getTxtNumero().setText(null);
		
	}
	
	public void cerrar(){
		this.dispose();
	}
}