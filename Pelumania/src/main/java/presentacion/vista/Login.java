package presentacion.vista;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTextField;

public class Login extends JFrame {

	private JFrame frmLogin;
	private static Login INSTANCE;
	private JPanel contentPane;
	private JButton btnIniciar;
	private JLabel lblNombre;
	private JTextField user;
	private JTextField pass;

	public Login() 
	{
		super();
		initialize();
	}
	public static Login getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new Login(); 	
			return new Login();
		}
		else
			return INSTANCE;
	}


	public void initialize() {
		
		frmLogin = new JFrame();
		frmLogin.setTitle("Login Pelumania");
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/index.png"));
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setBounds(100, 100, 418, 268);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmLogin.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmpleado = new JLabel("Sistema de turnos de peluqeria");
		lblEmpleado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmpleado.setBounds(10, 16, 241, 14);
		contentPane.add(lblEmpleado);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 41, 392, 177);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDias = new JLabel("Usuario:");
		lblDias.setBounds(10, 40, 132, 14);
		panel.add(lblDias);
		
		btnIniciar = new JButton("Iniciar Sesion");
		btnIniciar.setBounds(119, 143, 132, 23);
		panel.add(btnIniciar);
		
		JLabel lblContrasea = new JLabel("Contraseña: ");
		lblContrasea.setBounds(10, 99, 132, 14);
		panel.add(lblContrasea);
		
		user = new JTextField();
		user.setBounds(153, 37, 169, 20);
		panel.add(user);
		user.setColumns(10);
		
		pass = new JTextField();
		pass.setColumns(10);
		pass.setBounds(153, 96, 169, 20);
		panel.add(pass);
	
		
		lblNombre = new JLabel("");
		lblNombre.setBounds(112, 16, 139, 14);
		contentPane.add(lblNombre);
	}
	
	public void show()
	{
		this.frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmLogin.setVisible(true);
	}
	
	public JTextField getPass() {
		return this.pass;
	}
	
	public JTextField getUser() {
		return this.user;
	}
	
}
