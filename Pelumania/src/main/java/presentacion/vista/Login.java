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

public class Login {

	private JFrame frmLogin;
	private JPanel contentPane;
	private JButton btnIniciar;
	private JLabel lblNombre;
	private JTextField user;
	private JPasswordField pass;
	private JButton btnRecuperarPass;
	public Login() 
	{
		super();
		initialize();
	}

	public void initialize() {
		
		frmLogin = new JFrame();
		frmLogin.setTitle("Login Pelumania");
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/index.png"));
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setBounds(100, 100, 421, 239);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmLogin.setLocation(dim.width/2-frmLogin.getSize().width/2, dim.height/2-frmLogin.getSize().height/2);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmLogin.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmpleado = new JLabel("Sistema de turnos de peluqeria");
		lblEmpleado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmpleado.setBounds(10, 11, 241, 19);
		contentPane.add(lblEmpleado);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 41, 395, 149);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDias = new JLabel("Usuario:");
		lblDias.setBounds(10, 11, 132, 14);
		panel.add(lblDias);
		
		btnIniciar = new JButton("Iniciar Sesion");
		btnIniciar.setBounds(10, 115, 160, 23);
		panel.add(btnIniciar);
		
		JLabel lblContrasea = new JLabel("Contraseña: ");
		lblContrasea.setBounds(10, 55, 132, 14);
		panel.add(lblContrasea);
		
		user = new JTextField();
		user.setFont(new Font("Tahoma", Font.PLAIN, 15));
		user.setBounds(215, 6, 170, 20);
		panel.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pass.setColumns(10);
		pass.setBounds(215, 55, 170, 20);
		panel.add(pass);
		
		btnRecuperarPass = new JButton("Recuperar contraseña");
		btnRecuperarPass.setBounds(215, 115, 170, 23);
		panel.add(btnRecuperarPass);
	
		
		lblNombre = new JLabel("");
		lblNombre.setBounds(112, 16, 139, 14);
		contentPane.add(lblNombre);
		
		frmLogin.setVisible(true);
	}
	
	public void show()
	{
		this.frmLogin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frmLogin.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(
						null, "¿Deseas cerrar Pelumanía?",
						"Confirmación", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frmLogin.setVisible(true);
	}
	
	public void cerrar() {
		frmLogin.dispose();
	}

	
	public JPasswordField getPass() {
		return this.pass;
	}
	
	public JTextField getUser() {
		return this.user;
	}
	
	public JButton getIniciar() {
		return this.btnIniciar;
	}
	
	public JButton getRecuperarPass() {
		return this.btnRecuperarPass;
	}

	public void mostrarEnvioMail() {
			JOptionPane.showMessageDialog(new JFrame(), "Revisá tu casilla de mail","Dialog",JOptionPane.INFORMATION_MESSAGE);	
	}

	public void mostarErrorUsuarioInvalido() {
			JOptionPane.showMessageDialog(new JFrame(), "El nombre de usuario no existe", "Dialog",
					JOptionPane.ERROR_MESSAGE);
	}
}
