package presentacion.vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.TextPrompt;
import javax.swing.JComboBox;

public class VentanaEditarProducto extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaEditarProducto INSTANCE;
	
	private JTextField txtNombre;
	private JTextField txtPrecioLocal;
	private JTextField txtPrecioDolar;
	private JTextField txtPuntos;

	private JComboBox<String> JCBoxEstado;
	
	private JLabel lbl_Nombre;
	private JLabel lbl_PrecioLocal;
	private JLabel lbl_precioDolar;
	private JLabel lbl_Puntos;

	
	private JButton btn_GuardarProducto;
	private JButton btn_Cancelar;
	
	public static VentanaEditarProducto getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaEditarProducto(); 	
			return new VentanaEditarProducto();
		}
		else
			return INSTANCE;
	}

	/**
	 * 
	 */
	private VentanaEditarProducto() 
	{
		super();
		
		setTitle("Actualizar Producto");
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
		
		txtNombre = new JTextField();
		txtNombre.setBounds(132, 19, 167, 26);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtPrecioLocal = new JTextField();
		txtPrecioLocal.setColumns(10);
		txtPrecioLocal.setBounds(132, 56, 167, 26);
		panel.add(txtPrecioLocal);
		
		txtPrecioDolar = new JTextField();
		txtPrecioDolar.setColumns(10);
		txtPrecioDolar.setBounds(132, 93, 167, 26);
		panel.add(txtPrecioDolar);
		
		txtPuntos = new JTextField();
		txtPuntos.setColumns(10);
		txtPuntos.setBounds(132, 130, 167, 26);
		panel.add(txtPuntos);
		
		lbl_Nombre = new JLabel("Nombre:");
		lbl_Nombre.setBounds(0, 19, 124, 26);
		panel.add(lbl_Nombre);
		
		lbl_PrecioLocal = new JLabel("Precio en $");
		lbl_PrecioLocal.setBounds(0, 56, 124, 26);
		panel.add(lbl_PrecioLocal);
		
		lbl_precioDolar = new JLabel("Precio en USD:");
		lbl_precioDolar.setBounds(0, 93, 124, 26);
		panel.add(lbl_precioDolar);
		
		lbl_Puntos = new JLabel("Puntos:");
		lbl_Puntos.setBounds(0, 130, 124, 26);
		panel.add(lbl_Puntos);
		
		btn_GuardarProducto = new JButton("Guardar");
		btn_GuardarProducto.setBounds(66, 270, 89, 23);
		panel.add(btn_GuardarProducto);
		
		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setBounds(165, 270, 89, 23);
		panel.add(btn_Cancelar);
		

		

		
		JLabel lbl_Estado = new JLabel("Estado");
		lbl_Estado.setBounds(0, 204, 124, 26);
		panel.add(lbl_Estado);
		
		JCBoxEstado = new JComboBox<String>();
		JCBoxEstado.setBounds(132, 207, 167, 26);
		panel.add(JCBoxEstado);
		
		JCBoxEstado.addItem("Activo");
		JCBoxEstado.addItem("Inactivo");

		this.setVisible(false);
	}
	
	public static VentanaEditarProducto getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(VentanaEditarProducto iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtPrecioLocal() {
		return txtPrecioLocal;
	}

	public void setTxtPrecioLocal(JTextField txtPrecioLocal) {
		this.txtPrecioLocal = txtPrecioLocal;
	}

	public JTextField getTxtPrecioDolar() {
		return txtPrecioDolar;
	}

	public void setTxtPrecioDolar(JTextField txtPrecioDolar) {
		this.txtPrecioDolar = txtPrecioDolar;
	}

	public JTextField getTxtPuntos() {
		return txtPuntos;
	}

	public void setTxtPuntos(JTextField txtPuntos) {
		this.txtPuntos = txtPuntos;
	}

	public JButton getBtnGuardarProducto() {
		return btn_GuardarProducto;
	}

	public void setBtnEditarProducto(JButton btn_guardarProducto) {
		this.btn_GuardarProducto = btn_guardarProducto;
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

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public void mostrarErrorCampos() {
		JOptionPane.showMessageDialog(new JFrame(), "Campos ingresados inválidos", "Dialog",
		        JOptionPane.ERROR_MESSAGE);
	}

	public void cerrar()
	{

		this.dispose();
	}
}
