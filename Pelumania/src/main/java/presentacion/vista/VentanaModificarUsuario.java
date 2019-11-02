package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaModificarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JComboBox comboSucu;
	private JComboBox comboPerm;
	private JComboBox estado;
	private JButton btnAgregar;
	private static VentanaModificarUsuario INSTANCE;
	private JTextField textUser;
	private JPasswordField textPass;
	private JTextField textMail;

	
	public static VentanaModificarUsuario getInstance( )
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaModificarUsuario(); 	
			return new VentanaModificarUsuario();
		}
		else
			return INSTANCE;
	}

	private VentanaModificarUsuario() 
	{
		super();
		setTitle("Alta Usuario");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/usuario.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 468);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 374, 407);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 113, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(188, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 56, 113, 14);
		panel.add(lblApellido);

		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(188, 53, 164, 20);
		panel.add(txtApellido);
		
		
		btnAgregar= new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAgregar.setBounds(84, 373, 189, 23);
		panel.add(btnAgregar);
		
		JLabel labelComboOrig = new JLabel("Sucursal");
		labelComboOrig.setBounds(10, 288, 113, 14);
		panel.add(labelComboOrig);
		
		JLabel labeSucTran = new JLabel("Permisos");
		labeSucTran.setBounds(10, 238, 164, 14);
		panel.add(labeSucTran);
		
		comboSucu = new JComboBox();
		comboSucu.setBounds(188, 280, 164, 22);
		panel.add(comboSucu);
		
		comboPerm = new JComboBox();
		comboPerm.setBounds(188, 234, 164, 22);
		comboPerm.addItem("--");
		panel.add(comboPerm);
		
		estado = new JComboBox();
		estado.setBounds(188, 323, 164, 22);
		estado.addItem("Activo");
		estado.addItem("Inactivo");
		panel.add(estado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 327, 164, 14);
		panel.add(lblEstado);
		
		JLabel lblNombreUsuario = new JLabel("Nombre Usuario");
		lblNombreUsuario.setBounds(10, 109, 113, 14);
		panel.add(lblNombreUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(10, 151, 113, 14);
		panel.add(lblContrasea);
		
		JLabel lblMail = new JLabel("E-Mail");
		lblMail.setBounds(10, 195, 113, 14);
		panel.add(lblMail);
		
		textUser = new JTextField();
		textUser.setColumns(10);
		textUser.setBounds(188, 106, 164, 20);
		panel.add(textUser);
		
		textPass = new JPasswordField();
		textPass.setColumns(10);
		textPass.setBounds(188, 148, 164, 20);
		panel.add(textPass);
		
		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(188, 192, 164, 20);
		panel.add(textMail);
		
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

	public JTextField getTxtApellido() {
		return txtApellido;
	}
	
	public JTextField getTxtUser() {
		return textUser;
	}
	
	public JPasswordField getTxtPass() {
		return textPass;
	}
	
	public JTextField getTxtMail() {
		return textMail;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}
	
	public JComboBox getComboSucu() {
		return comboSucu;
	}
	
	public JComboBox getComboPerm() {
		return comboPerm;
	}
	
	public JComboBox getEstado() {
		return estado;
	}

	public void cerrar()
	{
		this.dispose();
	}
}