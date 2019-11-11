package presentacion.vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;

import util.PropertyManager;

public class VentanaCambioContrasenia
{
	private static VentanaCambioContrasenia INSTANCE;
	private JFrame frmCambiarContra;
	private JButton btnAgregar;
	private DefaultTableModel modelCambio;
	private JLabel lblContraseniaActual;
	private JLabel lblContraseniaNueva;
	private JLabel lblRepetirContasea;
	private JLabel nombreUser;
	private JPasswordField contraVieja;
	private JPasswordField contraNueva;
	private JPasswordField contraNueva2;
	private Locale locale;
	private ResourceBundle idioma;

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
		}
		else
			return INSTANCE;
	}

	private void initialize() 
	{
		locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
				PropertyManager.leer("configuracion", "pais"));
		idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
		
		frmCambiarContra = new JFrame();
		frmCambiarContra.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/barber-scissors.png"));
		frmCambiarContra.setTitle(idioma.getString("usuarios.cambiar.password"));
		frmCambiarContra.setBounds(100, 100, 373, 311);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmCambiarContra.setLocation(dim.width/2-frmCambiarContra.getSize().width/2, dim.height/2-frmCambiarContra.getSize().height/2);
		
		frmCambiarContra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCambiarContra.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 347, 261);
		frmCambiarContra.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		btnAgregar = new JButton(idioma.getString("guardar"));
		btnAgregar.setBounds(121, 226, 153, 23);
		panel.add(btnAgregar);
		
		JLabel lblUser = new JLabel(idioma.getString("login.usuario"));
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUser.setBounds(10, 11, 95, 24);
		panel.add(lblUser);
		
		lblContraseniaActual = new JLabel(idioma.getString("usuarios.password.actual"));
		lblContraseniaActual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContraseniaActual.setBounds(10, 71, 186, 24);
		panel.add(lblContraseniaActual);
		
		lblContraseniaNueva = new JLabel(idioma.getString("usuarios.password.nueva"));
		lblContraseniaNueva.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContraseniaNueva.setBounds(10, 126, 199, 24);
		panel.add(lblContraseniaNueva);
		
		lblRepetirContasea = new JLabel(idioma.getString("usuarios.password.repetir"));
		lblRepetirContasea.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRepetirContasea.setBounds(10, 181, 199, 24);
		panel.add(lblRepetirContasea);
		
		nombreUser = new JLabel(idioma.getString("usuarios.nombre"));
		nombreUser.setFont(new Font("Tahoma", Font.BOLD, 13));
		nombreUser.setBounds(120, 11, 228, 24);
		panel.add(nombreUser);
		
		contraVieja = new JPasswordField();
		contraVieja.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contraVieja.setBounds(206, 74, 128, 20);
		panel.add(contraVieja);
		contraVieja.setColumns(10);
		
		contraNueva = new JPasswordField();
		contraNueva.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contraNueva.setColumns(10);
		contraNueva.setBounds(206, 129, 128, 20);
		panel.add(contraNueva);
		
		contraNueva2 = new JPasswordField();
		contraNueva2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contraNueva2.setColumns(10);
		contraNueva2.setBounds(206, 184, 128, 20);
		panel.add(contraNueva2);
		
	}
	
	public void show()
	{
		this.frmCambiarContra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmCambiarContra.setVisible(true);
	}
	
	public JButton getBtnguardar() 
	{
		return btnAgregar;
	}
	
		
	public JLabel getNombreUser() {
		return nombreUser;
	}
	public void setNombreUser(JLabel nombreUser) {
		this.nombreUser = nombreUser;
	}
	public JPasswordField getContraVieja() {
		return contraVieja;
	}
	public JPasswordField getContraNueva() {
		return contraNueva;
	}
	public JPasswordField getContraNueva2() {
		return contraNueva2;
	}
	public void cerrar() {
		this.contraNueva.setText("");
		this.contraNueva2.setText("");
		this.contraVieja.setText("");
		this.nombreUser.setText("");
		frmCambiarContra.dispose();
	}
	public void mostrarErrorPassNoCoincide() {
		JOptionPane.showMessageDialog(null, idioma.getString("usuarios.error.password.nueva"), idioma.getString("usuarios.error.password.nueva"), JOptionPane.ERROR_MESSAGE);		
	}
	public void mostrarExito() {
		JOptionPane.showMessageDialog(null, idioma.getString("usuarios.password.exito"), "Dialog", JOptionPane.INFORMATION_MESSAGE);		
	}
	
	public void mostrarErrorPassActual() {
		JOptionPane.showMessageDialog(null, idioma.getString("usuarios.error.password.actual"), idioma.getString("usuarios.error.password.nueva"), JOptionPane.ERROR_MESSAGE);		
	}
}