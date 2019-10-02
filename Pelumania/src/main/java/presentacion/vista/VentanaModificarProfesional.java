package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Sistema;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaModificarProfesional extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JComboBox comboSucOrig;
	private JComboBox comboSucTran;
	private JButton btnAgregar;
	private static VentanaModificarProfesional INSTANCE;
	private JComboBox estado;
	private JLabel lblEstado;

	
	public static VentanaModificarProfesional getInstance( )
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaModificarProfesional(); 	
			return new VentanaModificarProfesional();
		}
		else
			return INSTANCE;
	}

	private VentanaModificarProfesional() 
	{
		super();
		setTitle("Editar Profesional");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 374, 266);
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
		
		
		btnAgregar= new JButton("Guardar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAgregar.setBounds(76, 232, 189, 23);
		panel.add(btnAgregar);
		
		JLabel labelComboOrig = new JLabel("Sucursal origen");
		labelComboOrig.setBounds(10, 111, 113, 14);
		panel.add(labelComboOrig);
		
		JLabel labeSucTran = new JLabel("Sucursal Transferencia");
		labeSucTran.setBounds(10, 154, 164, 14);
		panel.add(labeSucTran);
		
		comboSucOrig = new JComboBox();
		comboSucOrig.setBounds(188, 107, 164, 22);
		comboSucOrig.addItem(1);
		comboSucOrig.addItem(2);
		comboSucOrig.addItem(3);
		panel.add(comboSucOrig);
		
		comboSucTran = new JComboBox();
		comboSucTran.setBounds(188, 150, 164, 22);
		comboSucTran.addItem("--");
		comboSucTran.addItem(1);
		comboSucTran.addItem(2);
		comboSucTran.addItem(3);
		panel.add(comboSucTran);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 197, 164, 14);
		panel.add(lblEstado);
		
		estado= new JComboBox();
		estado.setBounds(188, 189, 164, 22);
		estado.addItem("Activo");
		estado.addItem("Inactivo");
		panel.add(estado);
		

		
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

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}
	
	public JComboBox getComboOrig() {
		return comboSucOrig;
	}
	
	public JComboBox getComboTran() {
		return comboSucTran;
	}
	
	public JComboBox getEstado() {
		return estado;
	}

	public void cerrar()
	{
		this.txtNombre.setText(null);
		this.txtApellido.setText(null);
		this.dispose();
	}
}

