package presentacion.vista;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.Conexion;

import java.awt.Font;
import javax.swing.JTextField;

public class VentanaCambioContrasenia {

	private JFrame frmChange;
	private JPanel contentPane;
	private JButton btnIniciar;
	private JLabel lblNombre;
	private JLabel nombreUser;
	private static VentanaCambioContrasenia INSTANCE;
	private JPasswordField passVieja;
	private JPasswordField passNew;
	private JPasswordField passNew2;

	public VentanaCambioContrasenia() 
	{
		super();
		initialize();
	}

	public static VentanaCambioContrasenia getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaCambioContrasenia();
			return new VentanaCambioContrasenia();
		} else {
			return INSTANCE;
		}
	}

	public void initialize() {
		
		frmChange = new JFrame();
		frmChange.setTitle("Cambio de Contraseña");
		frmChange.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/index.png"));
		frmChange.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChange.setBounds(100, 100, 421, 271);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmChange.setLocation(dim.width/2-frmChange.getSize().width/2, dim.height/2-frmChange.getSize().height/2);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmChange.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmpleado = new JLabel("Ususario: ");
		lblEmpleado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmpleado.setBounds(10, 11, 241, 19);
		contentPane.add(lblEmpleado);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 41, 395, 180);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDias = new JLabel("Contraseña Actual:");
		lblDias.setBounds(10, 11, 132, 14);
		panel.add(lblDias);
		
		btnIniciar = new JButton("Guardar");
		btnIniciar.setBounds(125, 146, 160, 23);
		panel.add(btnIniciar);
		
		JLabel lblContrasea = new JLabel("Contraseña Nueva:");
		lblContrasea.setBounds(10, 55, 132, 14);
		panel.add(lblContrasea);
		
		passVieja = new JPasswordField();
		passVieja.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passVieja.setBounds(215, 6, 170, 20);
		panel.add(passVieja);
		passVieja.setColumns(10);
		
		passNew = new JPasswordField();
		passNew.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passNew.setColumns(10);
		passNew.setBounds(215, 55, 170, 20);
		panel.add(passNew);
		
		JLabel label = new JLabel("Contraseña Nueva:");
		label.setBounds(10, 104, 132, 14);
		panel.add(label);
		
		passNew2 = new JPasswordField();
		passNew2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passNew2.setColumns(10);
		passNew2.setBounds(215, 101, 170, 20);
		panel.add(passNew2);
	
		
		lblNombre = new JLabel("");
		lblNombre.setBounds(112, 16, 139, 14);
		contentPane.add(lblNombre);
		
		nombreUser = new JLabel("");
		nombreUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		nombreUser.setBounds(87, 11, 241, 19);
		contentPane.add(nombreUser);
		
		frmChange.setVisible(true);
	}
	


	public void cerrar() {
		this.passNew.setText("");
		this.passNew2.setText("");
		this.passVieja.setText("");
		frmChange.dispose();
	}
	
	public JPasswordField getPass() {
		return this.passNew;
	}
	
	public JPasswordField getPass2() {
		return this.passNew2;
	}
	
	public JPasswordField getPassVieja() {
		return this.passVieja;
	}
	
	public JButton getIniciar() {
		return this.btnIniciar;
	}
	
	public JButton getnGuardar() {
		return this.btnIniciar;
	}

	public JLabel getNombreUser() {
		return nombreUser;
	}

	public void setNombreUser(JLabel nombreUser) {
		this.nombreUser = nombreUser;
	}

}
