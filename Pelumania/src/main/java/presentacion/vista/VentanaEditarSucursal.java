package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.PropertyManager;

public class VentanaEditarSucursal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaEditarSucursal INSTANCE;

	private JTextField txtNombreSucursal;
	private JTextField txtDireccion;
	private JTextField txtNumero;

	private JLabel lbl_NombreSucursal;
	private JLabel lbl_Direccion;
	private JLabel lbl_Numero;
	private JComboBox<String> JCBoxEstado;

	private JButton btn_GuardarSucursal;
	private JButton btn_Cancelar;
	private Locale locale;
	private ResourceBundle idioma;

	public static VentanaEditarSucursal getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaEditarSucursal();
			return new VentanaEditarSucursal();
		} else
			return INSTANCE;
	}

	private VentanaEditarSucursal() {
		super();

		locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
				PropertyManager.leer("configuracion", "pais"));
		idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);

		setTitle(idioma.getString("sucursal.editar"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 346, 354);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

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

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(132, 56, 167, 26);
		panel.add(txtDireccion);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(132, 93, 167, 26);
		panel.add(txtNumero);

		lbl_NombreSucursal = new JLabel(idioma.getString("nombre"));
		lbl_NombreSucursal.setBounds(0, 19, 124, 26);
		panel.add(lbl_NombreSucursal);

		lbl_Direccion = new JLabel(idioma.getString("direccion"));
		lbl_Direccion.setBounds(0, 56, 124, 26);
		panel.add(lbl_Direccion);

		lbl_Numero = new JLabel(idioma.getString("numero"));
		lbl_Numero.setBounds(0, 93, 124, 26);
		panel.add(lbl_Numero);

		btn_GuardarSucursal = new JButton(idioma.getString("guardar"));
		btn_GuardarSucursal.setBounds(100, 270, 89, 23);
		panel.add(btn_GuardarSucursal);

		/*
		 * btn_Cancelar = new JButton("Cancelar"); btn_Cancelar.setBounds(165, 270, 89,
		 * 23); panel.add(btn_Cancelar);
		 */
		JLabel lbl_Estado = new JLabel(idioma.getString("estado"));
		lbl_Estado.setBounds(0, 204, 124, 26);
		panel.add(lbl_Estado);

		JCBoxEstado = new JComboBox<String>();
		JCBoxEstado.setBounds(132, 207, 167, 26);
		panel.add(JCBoxEstado);

		JCBoxEstado.addItem("Activo");
		JCBoxEstado.addItem("Inactivo");

		this.setVisible(false);
	}

	public JTextField getTxtNombreSucursal() {
		return txtNombreSucursal;
	}

	public void setTxtNombreSucursale(JTextField txtNombreSucursal) {
		this.txtNombreSucursal = txtNombreSucursal;
	}

	public JTextField getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(JTextField txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public JTextField getTxtNumero() {
		return txtNumero;
	}

	public void setTxtNumero(JTextField txtNumero) {
		this.txtNumero = txtNumero;
	}

	public JButton getBtnGuardarSucursal() {
		return btn_GuardarSucursal;
	}

	public void setBtnEditarSucursal(JButton btn_GuardarSucursal) {
		this.btn_GuardarSucursal = btn_GuardarSucursal;
	}

	public JButton getBtn_Cancelar() {
		return btn_Cancelar;
	}

	public void setBtn_Cancelar(JButton btn_Cancelar) {
		this.btn_Cancelar = btn_Cancelar;
	}

	public JComboBox<String> getJCBoxEstado() {
		return JCBoxEstado;
	}

	public void setJCBoxEstado(JComboBox<String> jCBoxEstado) {
		JCBoxEstado = jCBoxEstado;
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public void mostrarErrorCampos() {
		JOptionPane.showMessageDialog(new JFrame(), idioma.getString("error.campos.invalidos"), "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}

	public void cerrar() {

		this.dispose();
	}

	public void mostrarErrorCamposVacios() {
		JOptionPane.showMessageDialog(null, idioma.getString("error.campos.vacios"), "Error",
				JOptionPane.ERROR_MESSAGE);
	}

}
