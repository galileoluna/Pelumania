package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VentanaAltaProfesional extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JComboBox comboSucuOrig;
	private JComboBox comboSucuTrans;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaProfesional frame = new VentanaAltaProfesional();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAltaProfesional() {
		setTitle("Profesional");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 35, 158, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 79, 158, 14);
		contentPane.add(lblApellido);
		
		JLabel lblSucursalOrigen = new JLabel("Sucursal Origen");
		lblSucursalOrigen.setBounds(10, 130, 158, 14);
		contentPane.add(lblSucursalOrigen);
		
		JLabel lblSucursakTransferencia = new JLabel("Sucursal Transferencia");
		lblSucursakTransferencia.setBounds(10, 185, 158, 14);
		contentPane.add(lblSucursakTransferencia);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(198, 32, 158, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(198, 76, 158, 20);
		contentPane.add(txtApellido);
		
		comboSucuTrans = new JComboBox();
		comboSucuTrans.setBounds(198, 177, 158, 22);
		contentPane.add(comboSucuTrans);
		
		comboSucuOrig = new JComboBox();
		comboSucuOrig.setBounds(198, 126, 158, 22);
		contentPane.add(comboSucuOrig);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(308, 227, 89, 23);
		contentPane.add(btnAgregar);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}
	
	public JTextField getTxtApellido() 
	{
		return txtApellido;
	}
	
	public JComboBox getComboSucOrig() 
	{
		return comboSucuOrig;
	}
	
	public JComboBox getComboSucTrans() 
	{
		return comboSucuTrans;
	}
	
}
