package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.PropertyManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;


public class VentanaBaseDeDatos extends JFrame {
	
	private JFrame frmBdd;
	private static VentanaBaseDeDatos INSTANCE;
	private JPanel contentPane;
	
	private Locale locale;
	private ResourceBundle idioma;

	public VentanaBaseDeDatos() 
	{
		super();
		initialize();
	}
	public static VentanaBaseDeDatos getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaBaseDeDatos(); 	
			return new VentanaBaseDeDatos();
		}
		else
			return INSTANCE;
	}


	public void initialize() {
		// configuracion de idioma
		locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
				PropertyManager.leer("configuracion", "pais"));
		
		idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
		
		frmBdd = new JFrame();
		frmBdd.setAutoRequestFocus(false);
		frmBdd.setType(Type.POPUP);
		frmBdd.setTitle(this.idioma.getString("gestion.bdd"));
		frmBdd.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/bdd.png"));
		frmBdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBdd.setBounds(100, 100, 408, 218);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmBdd.setLocation(dim.width/2-frmBdd.getSize().width/2, dim.height/2-frmBdd.getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmBdd.getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 372, 159);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(this.idioma.getString("esperar"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(70, 11, 185, 45);
		panel.add(lblNewLabel);
		
		JLabel lblUnaVezFinalizado = new JLabel(this.idioma.getString("informar.fin"));
		lblUnaVezFinalizado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUnaVezFinalizado.setBounds(10, 56, 297, 45);
		panel.add(lblUnaVezFinalizado);
		
		JLabel lblEstoPuedeTardar = new JLabel(this.idioma.getString("demora"));
		lblEstoPuedeTardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstoPuedeTardar.setBounds(10, 103, 297, 45);
		panel.add(lblEstoPuedeTardar);
	
	}
	
	public void show()
	{
		this.frmBdd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmBdd.setVisible(true);
	}
	
	
	
	public void cerrar()
	{
		
		this.frmBdd.dispose();
	}
	
	public void mostrarExitoImportar() {
		JOptionPane.showMessageDialog(null, this.idioma.getString("exito.importar.bdd"));
	}
	
	public void mostrarExitoExportar() {
		JOptionPane.showMessageDialog(null, this.idioma.getString("exito.exportar.bdd"));
	}
	public void mostrarError() {
		JOptionPane.showMessageDialog(new JFrame(),this.idioma.getString("error.generico"), "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}
}
