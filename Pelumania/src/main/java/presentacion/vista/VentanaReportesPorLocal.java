package presentacion.vista;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dto.ClienteDTO;

public class VentanaReportesPorLocal extends JFrame{

	private static final long serialVersionUID = 1L;
	private static VentanaReportesPorLocal INSTANCE;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	
	private JTextField buscador;
	private JButton btnBuscar;
	private JComboBox variableBuscar;
	
	private JPanel contentPane;
	
	
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtApellido;
	private JTextField txtMail;
	private VentanaReportesPorLocal()
	{
		super();
		initialize();
	}

	public static VentanaReportesPorLocal getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaReportesPorLocal();
			return new VentanaReportesPorLocal();
		} else {
			return INSTANCE;
		}
	}


	private void initialize()
	{
		setTitle("Caja");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 413, 561);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 397, 512);
		contentPane.add(panel);
		panel.setLayout(null);
		
		setVisible(true);

	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public void setBtnAgregar(JButton btnAgregar) {
		this.btnAgregar = btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	public JComboBox getVariableBuscar() {
		return variableBuscar;
	}
	
	public JTextField getBuscador() {
		return buscador;
	}

	public JTextField getTxtNombre()
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono()
	{
		return txtTelefono;
	}

	public JTextField getTxtApellido() {
		return txtApellido;
	}

	public void setTxtApellido(JTextField txtApellido) {
		this.txtApellido = txtApellido;
	}

	public JTextField getTxtMail() {
		return txtMail;
	}

	public void setTxtMail(JTextField txtMail) {
		this.txtMail = txtMail;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public void setTxtTelefono(JTextField txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public void mostrar() {
		this.setVisible(true);

	}
	public void limpiarInputs()
	{
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.txtApellido.setText(null);
		this.txtMail.setText(null);
	}

}