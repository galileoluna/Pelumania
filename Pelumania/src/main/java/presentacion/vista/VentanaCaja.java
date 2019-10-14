package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;



public class VentanaCaja extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaCaja INSTANCE;
	private JComboBox<String> comboTipoMovimiento;
	private JLabel lblTipoMovimiento;
	private JComboBox<String> comboCategoria;
	private JPanel panel_egreso;
	private JLabel lbl_profesional;
	private JComboBox<String> comboProfesional;
	private JButton btnAgregar;
	private JLabel lblPrecioEgreso;
	private JTextField txtPrecioPesos;
	private JLabel lblCita;
	private JButton buttonBuscarCita;
	private JComboBox comboTipoPago;
	private JTextArea txtDescripcion;
	private JTextField txtPrecioDolar;
	private JButton btnCancelar;
	
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
		setBounds(100, 100, 413, 561);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 397, 512);
		contentPane.add(panel);
		panel.setLayout(null);

		lblTipoMovimiento = new JLabel("Tipo de Movimiento");
		lblTipoMovimiento.setBounds(10, 136, 124, 26);
		panel.add(lblTipoMovimiento);
		
		comboTipoMovimiento = new JComboBox<String>();
		comboTipoMovimiento.addItem("Ingreso");
		comboTipoMovimiento.addItem("Egreso");
		comboTipoMovimiento.setBounds(182, 138, 172, 23);
		panel.add(comboTipoMovimiento);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 187, 124, 26);
		panel.add(lblCategoria);
		
		JLabel lblTipoPago = new JLabel("Tipo de Pago");
		lblTipoPago.setToolTipText("");
		lblTipoPago.setBounds(10, 239, 85, 14);
		panel.add(lblTipoPago);
		
		comboTipoPago = new JComboBox();
		comboTipoPago.setBounds(182, 235, 172, 23);
		panel.add(comboTipoPago);
		
		comboCategoria = new JComboBox<String>();
		comboCategoria.setBounds(182, 186, 172, 23);
		panel.add(comboCategoria);
		
		panel_egreso = new JPanel();
		panel_egreso.setBounds(10, 269, 397, 195);
		panel.add(panel_egreso);
		panel_egreso.setLayout(null);
		//ocultamos por defecto los campos que son
		//especicos de un egreso
		panel_egreso.setVisible(false);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(10, 24, 87, 16);
		panel_egreso.add(lblDescripcion);
		
		txtPrecioPesos = new JTextField();
		txtPrecioPesos.setBounds(173, 85, 170, 26);
		panel_egreso.add(txtPrecioPesos);
		txtPrecioPesos.setColumns(10);
		
		lblPrecioEgreso = new JLabel("Monto en ARS");
		lblPrecioEgreso.setBounds(10, 85, 124, 26);
		panel_egreso.add(lblPrecioEgreso);
		
		JLabel lblMontoEnUsd = new JLabel("Monto en USD");
		lblMontoEnUsd.setBounds(10, 141, 124, 26);
		panel_egreso.add(lblMontoEnUsd);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setToolTipText("De ser necesario, ingrese una descripción");
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtDescripcion.setBounds(173, 6, 170, 67);
		panel_egreso.add(txtDescripcion);
		
		txtPrecioDolar = new JTextField();
		txtPrecioDolar.setColumns(10);
		txtPrecioDolar.setBounds(173, 141, 170, 26);
		panel_egreso.add(txtPrecioDolar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(52, 475, 124, 26);
		panel.add(btnAgregar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(218, 475, 124, 26);
		panel.add(btnCancelar);
		
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
		
		
		
		JLabel lblImagen =  new JLabel();
		lblImagen.setBounds(10, 11, 124, 98);		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("imagenes/caja.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		lblImagen.setIcon(imageIcon);
		panel.add(lblImagen);
		
		
		
		
		
		
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
		this.txtPrecioDolar.setText(null);
		this.txtPrecioPesos.setText(null);
		this.txtDescripcion.setText(null);
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

	public JComboBox getComboTipoCambio() {
		return comboTipoPago;
	}

	public void setComboTipoPago(JComboBox comboTipoPago) {
		this.comboTipoPago = comboTipoPago;
	}

	public JTextField getTxtPrecioPesos() {
		return txtPrecioPesos;
	}

	public JTextField getTxtPrecioDolar() {
		return txtPrecioDolar;
	}

	public void mostrarErrorPrecio() {
		JOptionPane.showMessageDialog(new JFrame(), "precio inválido", "Dialog",
				JOptionPane.ERROR_MESSAGE);		
	}

	public void mostrarExito() {
		JOptionPane.showMessageDialog(new JFrame(), "La operacion fue registrada con exito", "Dialog",
				JOptionPane.INFORMATION_MESSAGE);		
		
	}

	public JButton getBtnCancelar() {
		return this.btnCancelar;
	}
}