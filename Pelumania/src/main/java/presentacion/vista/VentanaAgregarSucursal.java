package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.PropertyManager;

public class VentanaAgregarSucursal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAgregarSucursal INSTANCE;

	private JTextField txtNombreSucursal;
	private JTextField txtDireccion;
	private JTextField txtNumero;

	private JLabel lbl_NombreSucursal;
	private JLabel lbl_Direccion;
	private JLabel lbl_Numero;

	private JButton btn_AgregarSucursal;
	private JButton btn_Cancelar;

	// configuracion de idioma
	private Locale locale;
	private ResourceBundle idioma;

	public static VentanaAgregarSucursal getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaAgregarSucursal();
			return new VentanaAgregarSucursal();
		} else {
			return INSTANCE;
		}
	}

	private VentanaAgregarSucursal() {
		super();
		locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
				PropertyManager.leer("configuracion", "pais"));
		idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);

		setBounds(100, 100, 346, 354);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		setTitle(idioma.getString("sucursal.agregar"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

		btn_AgregarSucursal = new JButton(idioma.getString("agregar"));
		btn_AgregarSucursal.setBounds(66, 270, 89, 23);
		panel.add(btn_AgregarSucursal);

		btn_Cancelar = new JButton(idioma.getString("cancelar"));
		btn_Cancelar.setBounds(165, 270, 89, 23);
		panel.add(btn_Cancelar);

		this.setVisible(false);
	}

	public static VentanaAgregarSucursal getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(VentanaAgregarSucursal iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public JTextField getTxtNombreSucursal() {
		return txtNombreSucursal;
	}

	public void setTxtNombreSucursal(JTextField txtNombreSucursal) {
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

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public void mostrarErrorCampos() {
		JOptionPane.showMessageDialog(new JFrame(), idioma.getString("error.campos.invalidos"), "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}

	public void limpiarCampos() {
		this.getTxtNombreSucursal().setText(null);
		this.getTxtDireccion().setText(null);
		this.getTxtNumero().setText(null);

	}

	public void cerrar() {
		this.dispose();
	}
}