
package presentacion.vista;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import util.PropertyManager;

import javax.swing.JRadioButton;

public class VentanaIdioma extends JFrame {

	private static final long serialVersionUID = 1L;
	private static VentanaIdioma INSTANCE;
	private JPanel contentPane;
	private JButton btnAceptar;
	private JLabel lblBandera;
	private JRadioButton radioButtonIngles;
	private JRadioButton radioButtonEspaniol;
	private ResourceBundle idioma;
	
	private VentanaIdioma()
	{
		super();
		initialize();
	}

	public static VentanaIdioma getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaIdioma();
			return INSTANCE;
		} else {
			return INSTANCE;
		}
	}

	private void initialize()
	{
		
		//configuracion de idioma
		Locale locale = new Locale (PropertyManager.leer("configuracion", "idioma"), PropertyManager.leer("configuracion", "pais"));
		this.idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
	
		
		setTitle(this.idioma.getString("idioma.cambiar"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 336, 280);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 320, 242);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnAceptar = new JButton(this.idioma.getString("aceptar"));
		btnAceptar.setBounds(140, 203, 151, 23);
		panel.add(btnAceptar);
		
		JLabel lblSeleccionarIdioma = new JLabel(this.idioma.getString("idioma.cambiar"));
		lblSeleccionarIdioma.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSeleccionarIdioma.setBounds(32, 11, 273, 23);
		panel.add(lblSeleccionarIdioma);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 36, 273, 2);
		panel.add(separator);
		
		radioButtonIngles = new JRadioButton(this.idioma.getString("idioma.ingles"));
		radioButtonIngles.setBounds(32, 79, 109, 23);
		radioButtonIngles.setActionCommand("ingles");
		radioButtonIngles.addActionListener(l -> actionPerformed(l));
		panel.add(radioButtonIngles);
		
		
		radioButtonEspaniol = new JRadioButton(this.idioma.getString("idioma.espaniol"));
		radioButtonEspaniol.setBounds(32, 135, 109, 23);
		radioButtonEspaniol.setMnemonic(KeyEvent.VK_B);
		radioButtonEspaniol.setActionCommand("espaniol");
		radioButtonEspaniol.addActionListener(l -> actionPerformed(l));
		radioButtonEspaniol.setSelected(true);
		
		panel.add(radioButtonEspaniol);
		
		
		ButtonGroup group = new ButtonGroup();
	    group.add(radioButtonIngles);
	    group.add(radioButtonEspaniol);
	    
	    
	    
		lblBandera =  new JLabel();
		lblBandera.setBounds(147, 59, 109, 113);		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("imagenes/espaniol.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		lblBandera.setIcon(imageIcon);
		panel.add(lblBandera);

		
		this.setVisible(false);
	}
	
	public void mostrarVentana() {
		this.setVisible(true);
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void mostrar() {
		this.setVisible(true);

	}
	public void limpiarInputs()
	{
		
	}
	
	public void cerrar(){
		this.dispose();
	}
	
	public void actionPerformed(ActionEvent e) {
		lblBandera.setIcon(new ImageIcon(new ImageIcon("imagenes/"+ e.getActionCommand() + ".png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		
	}

	public JRadioButton getRadioButtonIngles() {
		return radioButtonIngles;
	}

	public JRadioButton getRadioButtonEspaniol() {
		return radioButtonEspaniol;
	}

	public void mostrarExito() {
		JOptionPane.showMessageDialog(new JFrame(), this.idioma.getString("idioma.exito"),"Dialog",JOptionPane.INFORMATION_MESSAGE);
	}
}
