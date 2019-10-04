package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import util.TextPrompt;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;


public class VentanaAgregarCita extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAgregarCita INSTANCE;
	
	private JButton btn_AgregarCita;
	private JButton btn_Cancelar;
	
	public static VentanaAgregarCita getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaAgregarCita(); 	
			return new VentanaAgregarCita();
		}
		else
			return INSTANCE;
	}

	/**
	 * 
	 */
	private VentanaAgregarCita() 
	{
		super();
		
		setTitle("Nueva Cita");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 346, 354);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 309, 304);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btn_AgregarCita = new JButton("Agregar");
		btn_AgregarCita.setBounds(66, 270, 89, 23);
		panel.add(btn_AgregarCita);
		
		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setBounds(165, 270, 89, 23);
		panel.add(btn_Cancelar);
		
		this.setVisible(false);
	}
	
	
	public static VentanaAgregarCita getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(VentanaAgregarCita iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public void setBtnAgregarServicio(JButton btn_AgregarServicio) {
		this.btn_AgregarCita = btn_AgregarServicio;
	}

	public JButton getBtn_Cancelar() {
		return btn_Cancelar;
	}

	public void setBtn_Cancelar(JButton btn_Cancelar) {
		this.btn_Cancelar = btn_Cancelar;
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public void mostrarErrorCampos() {
		JOptionPane.showMessageDialog(new JFrame(), "Campos ingresados inválidos", "Dialog",
		        JOptionPane.ERROR_MESSAGE);
	}

	public void cerrar()
	{

		this.dispose();
	}
}