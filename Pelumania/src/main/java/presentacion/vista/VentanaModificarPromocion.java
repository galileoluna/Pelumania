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
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class VentanaModificarPromocion extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField descripcion;
	private JComboBox estado;
	private JButton btnAgregar;
	private JDateChooser dateFechaInic;
	private JDateChooser dateFechaFin;
	private static VentanaModificarPromocion INSTANCE;
	private JTextField descuento;


	private JTextField puntos;

	
	public static VentanaModificarPromocion getInstance( )
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaModificarPromocion(); 	
			return new VentanaModificarPromocion();
		}
		else
			return INSTANCE;
	}

	private VentanaModificarPromocion() 
	{
		super();
		setTitle("Alta Promocion");
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/barber-scissors.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 610, 364);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 574, 303);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Descripcion");
		lblNombre.setBounds(10, 11, 113, 14);
		panel.add(lblNombre);
		
		descripcion = new JTextField();
		descripcion.setBounds(188, 8, 376, 23);
		panel.add(descripcion);
		descripcion.setColumns(10);
		
		JLabel lblApellido = new JLabel("Fecha Inicio:");
		lblApellido.setBounds(10, 56, 113, 14);
		panel.add(lblApellido);
		
		
		btnAgregar= new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAgregar.setBounds(429, 269, 135, 23);
		panel.add(btnAgregar);
		
		JLabel labelComboOrig = new JLabel("Fecha Fin");
		labelComboOrig.setBounds(10, 112, 113, 14);
		panel.add(labelComboOrig);
		
		JLabel labeSucTran = new JLabel("Puntos");
		labeSucTran.setBounds(10, 187, 164, 14);
		panel.add(labeSucTran);
				
		estado = new JComboBox();
		estado.setBounds(188, 222, 164, 22);
		estado.addItem("Activo");
		estado.addItem("Inactivo");
		panel.add(estado);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 226, 164, 14);
		panel.add(lblEstado);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setBounds(10, 155, 164, 14);
		panel.add(lblDescuento);
		
		dateFechaInic = new JDateChooser();
		dateFechaInic.setBounds(188, 50, 164, 20);
		panel.add(dateFechaInic);
		
		dateFechaFin = new JDateChooser();
		dateFechaFin.setBounds(188, 106, 164, 20);
		panel.add(dateFechaFin);
		
		descuento = new JTextField();
		descuento.setColumns(10);
		descuento.setBounds(188, 152, 164, 20);
		panel.add(descuento);
		
		puntos = new JTextField();
		puntos.setColumns(10);
		puntos.setBounds(188, 184, 164, 20);
		panel.add(puntos);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public JTextField getDescripcion() 
	{
		return descripcion;
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}
	
	public JTextField getDescuento() {
		return descuento;
	}


	public JTextField getPuntos() {
		return puntos;
	}

	
	public JComboBox getEstado() {
		return estado;
	}
	
	public JDateChooser getDateFechaInic() {
		return dateFechaInic;
	}

	public JDateChooser getDateFechaFin() {
		return dateFechaFin;
	}

	public void cerrar()
	{
		this.descripcion.setText(null);
		this.puntos.setText(null);
		this.descuento.setText(null);
		this.dispose();
	}
}

