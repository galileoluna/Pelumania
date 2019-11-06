package presentacion.vista;


import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAgregarCategoriaMovimientosCaja extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaAgregarCategoriaMovimientosCaja INSTANCE;

	private JTextField txtNombreCategoria;

	private JLabel lblNombreCategoria;
	private JLabel lblTipoMovimiento;
	private JLabel lblEstado;
	

	private JButton btnAgregarCategoria;
	private JButton btnCancelar;
	private JComboBox<String> comboEstado;
	private JButton btnEditarCategoria;
	private JComboBox<String> comboTipoMovimiento;
	private JLabel lblImagen;
	
	public static VentanaAgregarCategoriaMovimientosCaja getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaAgregarCategoriaMovimientosCaja();
		}
			return INSTANCE;
	}

	private VentanaAgregarCategoriaMovimientosCaja()
	{
		super();
		
		
		setTitle("Nueva categoria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 346, 400);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 309, 351);
		contentPane.add(panel);
		panel.setLayout(null);

		txtNombreCategoria = new JTextField();
		txtNombreCategoria.setBounds(132, 127, 167, 26);
		panel.add(txtNombreCategoria);
		txtNombreCategoria.setColumns(10);

		lblNombreCategoria = new JLabel("Nombre Categoria");
		lblNombreCategoria.setBounds(10, 127, 124, 26);
		panel.add(lblNombreCategoria);

		lblTipoMovimiento = new JLabel("Tipo de movimiento");
		lblTipoMovimiento.setBounds(10, 186, 124, 26);
		panel.add(lblTipoMovimiento);

		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 242, 124, 26);
		panel.add(lblEstado);
		
		btnEditarCategoria = new JButton("Editar");
		btnEditarCategoria.setBounds(10, 317, 124, 23);
		panel.add(btnEditarCategoria);

		btnAgregarCategoria = new JButton("Agregar");
		btnAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregarCategoria.setBounds(10, 317, 124, 23);
		panel.add(btnAgregarCategoria);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(175, 317, 124, 23);
		panel.add(btnCancelar);
		
		comboEstado = new JComboBox<String>();
		comboEstado.setBounds(132, 244, 167, 23);
		panel.add(comboEstado);
		
		comboTipoMovimiento = new JComboBox<String>();
		comboTipoMovimiento.setBounds(132, 189, 167, 23);
		panel.add(comboTipoMovimiento);
		
		 
		lblImagen =  new JLabel();
		lblImagen.setBounds(10, 11, 99, 87);		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("imagenes/categoria.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
		lblImagen.setIcon(imageIcon);
		panel.add(lblImagen);
		
		this.setVisible(false);
	}
	public static VentanaAgregarCategoriaMovimientosCaja getINSTANCE() {
		return INSTANCE;
	}

	public JTextField getTxtNombre() {
		return txtNombreCategoria;
	}

	public void setTxtNombreSucursal(JTextField txtNombreSucursal) {
		this.txtNombreCategoria = txtNombreSucursal;
	}

public JButton getBtnAgregarCategoria() {
		return btnAgregarCategoria;
	}

	public void setBtnAgregarSucursal(JButton btn_AgregarSucursal) {
		this.btnAgregarCategoria = btn_AgregarSucursal;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btn_Cancelar) {
		this.btnCancelar = btn_Cancelar;
	}

	public JComboBox<String> getComboEstado() {
		return comboEstado;
	}

	public void setComboEstado(JComboBox<String> comboEstado) {
		this.comboEstado = comboEstado;
	}

	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public void mostrarErrorCampos() {
		JOptionPane.showMessageDialog(new JFrame(), "Campos ingresados inválidos", "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}

	public void limpiarCampos() {
		this.getTxtNombre().setText(null);

	}
	
	public void cerrar(){
		this.dispose();
	}

	public JButton getBtnEditarCategoria() {
		return this.btnEditarCategoria;
		
	}

	public void mostrarErrorRepetido() {
		JOptionPane.showMessageDialog(new JFrame(), "Ya existe una categoria con ese nombre", "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarExitoEditar() {
			JOptionPane.showMessageDialog(new JFrame(), "La categoria fue editado con éxito","Dialog",JOptionPane.INFORMATION_MESSAGE);
	}

	public void mostrarExitoAgregar() {
		JOptionPane.showMessageDialog(new JFrame(), "La categoria fue agregada con éxito","Dialog",JOptionPane.INFORMATION_MESSAGE);		
	}

	public JComboBox<String> getComboTipoMovimiento() {
		return this.comboTipoMovimiento;
	}

	public void setComboTipoMovimiento(JComboBox<String> comboTipoMovimiento) {
		this.comboTipoMovimiento = comboTipoMovimiento;
	}

	public void mostrarErrorNombre() {
		JOptionPane.showMessageDialog(new JFrame(), "El nombre es inválido", "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarErrorCategoriaEnUso() {
		// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(new JFrame(), "La categoría ya fue utilizada, no puede editar el tipo", "Dialog",
					JOptionPane.ERROR_MESSAGE);
	}
}