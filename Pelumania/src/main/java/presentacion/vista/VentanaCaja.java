package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.TextPrompt;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class VentanaCaja extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaCaja INSTANCE;
	private JComboBox<String> comboTipoMovimiento;
	private JLabel lblTipoMovimiento;
	private JComboBox<String> comboCategoria;
	private JTextField txtDescripcion;
	private JPanel panel_egreso;
	private JLabel lbl_profesional;
	private JComboBox<String> comboProfesional;
	private JButton btnAgregar;
	private JLabel lblPrecioEgreso;
	private JTextField txtPrecio;
	private JLabel lblCita;
	private JButton buttonBuscarCita;
	private JComboBox comboTipoPago;
	private JTextField textField;
	
	public static VentanaCaja getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaCaja();
			return new VentanaCaja();
		} else {
			return INSTANCE;
		}
	}

	private VentanaCaja()
	{
		super();

		setTitle("Caja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 413, 453);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 397, 415);
		contentPane.add(panel);
		panel.setLayout(null);

		lblTipoMovimiento = new JLabel("Tipo de Movimiento");
		lblTipoMovimiento.setBounds(10, 33, 124, 26);
		panel.add(lblTipoMovimiento);
		
		comboTipoMovimiento = new JComboBox<String>();
		comboTipoMovimiento.addItem("Ingreso");
		comboTipoMovimiento.addItem("Egreso");
		comboTipoMovimiento.setBounds(169, 35, 173, 23);
		panel.add(comboTipoMovimiento);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 81, 124, 26);
		panel.add(lblCategoria);
		
		comboCategoria = new JComboBox<String>();
		comboCategoria.setBounds(169, 84, 173, 23);
		panel.add(comboCategoria);
		
		panel_egreso = new JPanel();
		panel_egreso.setBounds(0, 172, 397, 195);
		panel.add(panel_egreso);
		panel_egreso.setLayout(null);
		//ocultamos por defecto los campos que son
		//especicos de un egreso
		panel_egreso.setVisible(false);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(173, 24, 165, 53);
		panel_egreso.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(10, 24, 87, 16);
		panel_egreso.add(lblDescripcion);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(173, 98, 164, 26);
		panel_egreso.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		lblPrecioEgreso = new JLabel("Monto en ARS");
		lblPrecioEgreso.setBounds(10, 98, 124, 26);
		panel_egreso.add(lblPrecioEgreso);
		
		JLabel lblMontoEnUsd = new JLabel("Monto en USD");
		lblMontoEnUsd.setBounds(10, 158, 124, 26);
		panel_egreso.add(lblMontoEnUsd);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(173, 158, 164, 26);
		panel_egreso.add(textField);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(130, 378, 160, 26);
		panel.add(btnAgregar);
		
		JLabel lblTipoPago = new JLabel("Tipo de Pago");
		lblTipoPago.setToolTipText("");
		lblTipoPago.setBounds(10, 134, 85, 14);
		panel.add(lblTipoPago);
		
		comboTipoPago = new JComboBox();
		comboTipoPago.setBounds(169, 131, 173, 20);
		panel.add(comboTipoPago);
		
//		lblCita = new JLabel("Cita");
//		lblCita.setBounds(10, 138, 99, 26);
//		panel.add(lblCita);
		
//		buttonBuscarCita = new JButton("Buscar");
//		buttonBuscarCita.setBounds(169, 140, 173, 26);
//		panel.add(buttonBuscarCita);
		
//		lbl_profesional = new JLabel("Profesional");
//		lbl_profesional.setBounds(10, 128, 124, 26);
//		panel.add(lbl_profesional);
//		
//		comboProfesional = new JComboBox<String>();
//		comboProfesional.setBounds(169, 129, 204, 23);
//		panel.add(comboProfesional);
//		
		this.setVisible(false);
	}

	public static VentanaCaja getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(VentanaCaja iNSTANCE) {
		INSTANCE = iNSTANCE;
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
		
	}
	
	public void cerrar(){
		this.dispose();
	}
	
	public JComboBox<String> getComboTipoMovimiento() {
		return comboTipoMovimiento;
	}
	
	public JPanel getPanel_egreso() {
		return panel_egreso;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}
	
	public String getTxtDescripcion() {
		return txtDescripcion.getText();
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JComboBox<String> getComboCategoria() {
		return comboCategoria;
	}

	public JComboBox getComboTipoPago() {
		return comboTipoPago;
	}

	public void setComboTipoPago(JComboBox comboTipoPago) {
		this.comboTipoPago = comboTipoPago;
	}
}