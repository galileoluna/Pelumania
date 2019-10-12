package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.TextPrompt;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;



public class VentanaCaja extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaCaja INSTANCE;
	private JComboBox<String> comboTipoMovimiento;
	private JLabel lbl_Categoria;
	private JComboBox<String> comboCategoria;
	private JTextField txtDescripcion;
	private JPanel panel_egreso;
	private JLabel lbl_profesional;
	private JComboBox<String> comboProfesional;
	private JButton btnAgregar;
	
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
		setBounds(100, 100, 451, 453);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 435, 415);
		contentPane.add(panel);
		panel.setLayout(null);

		lbl_Categoria = new JLabel("Tipo de Movimiento");
		lbl_Categoria.setBounds(10, 33, 124, 26);
		panel.add(lbl_Categoria);
		
		comboTipoMovimiento = new JComboBox<String>();
		comboTipoMovimiento.addItem("Ingreso");
		comboTipoMovimiento.addItem("Egreso");
		comboTipoMovimiento.setBounds(169, 35, 204, 23);
		panel.add(comboTipoMovimiento);
		
		JLabel lbl_Categoria = new JLabel("Categoria");
		lbl_Categoria.setBounds(10, 81, 124, 26);
		panel.add(lbl_Categoria);
		
		comboCategoria = new JComboBox<String>();
		comboCategoria.setBounds(169, 84, 204, 23);
		comboCategoria.addItem("Insumos");
		panel.add(comboCategoria);
		
		panel_egreso = new JPanel();
		panel_egreso.setBounds(0, 119, 437, 185);
		panel.add(panel_egreso);
		panel_egreso.setLayout(null);
		//ocultamos por defecto los campos que son
		//especicos de un egreso
		panel_egreso.setVisible(false);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(10, 65, 385, 78);
		panel_egreso.add(txtDescripcion);
		txtDescripcion.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(10, 21, 87, 16);
		panel_egreso.add(lblDescripcion);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(152, 377, 140, 26);
		panel.add(btnAgregar);
		
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
}