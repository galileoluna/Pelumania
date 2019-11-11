package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dto.UsuarioDTO;
import util.PropertyManager;

public class VentanaUsuario extends JFrame {
	private static VentanaUsuario INSTANCE;
	private JTable tablaUsuario;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelUsuario;
	// configuracion de idioma
	private Locale locale = new Locale(PropertyManager.leer("configuracion", "idioma"),
				PropertyManager.leer("configuracion", "pais"));
	private ResourceBundle idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
	
	private  String[] nombreColumnas = { idioma.getString("nombre"), idioma.getString("login.usuario"), 
										idioma.getString("mail"), idioma.getString("usuarios.permisos") , 
										idioma.getString("sucursal"), idioma.getString("estado")};

	public VentanaUsuario()
	{
		super();
		initialize();
	}

	public static VentanaUsuario getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaUsuario();
			return new VentanaUsuario();
		} else {
			return INSTANCE;
		}
	}
	
	private void initialize()
	{
		setBounds(100, 100, 739, 406);
		setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/usuario.png"));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle(idioma.getString("usuarios.titulo"));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 713, 356);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane spSucursal = new JScrollPane();
		spSucursal.setBounds(10, 11, 693, 277);
		panel.add(spSucursal);

		modelUsuario = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaUsuario = new JTable(modelUsuario);


		tablaUsuario.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaUsuario.getColumnModel().getColumn(0).setResizable(false);
		tablaUsuario.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaUsuario.getColumnModel().getColumn(1).setResizable(false);
		tablaUsuario.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaUsuario.getColumnModel().getColumn(2).setResizable(false);
		tablaUsuario.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaUsuario.getColumnModel().getColumn(3).setResizable(false);
		

		spSucursal.setViewportView(tablaUsuario);

		btnAgregar = new JButton(idioma.getString("agregar"));
		btnAgregar.setBounds(172, 322, 89, 23);
		panel.add(btnAgregar);

		btnEditar = new JButton(idioma.getString("editar"));
		btnEditar.setBounds(282, 322, 89, 23);
		panel.add(btnEditar);

		btnBorrar = new JButton(idioma.getString("borrar"));
		btnBorrar.setBounds(394, 322, 89, 23);
		panel.add(btnBorrar);
	}
	public JTable gettablaUsuario() {
		return tablaUsuario;
	}

	public void settablaUsuario(JTable tablaUsuario) {
		this.tablaUsuario = tablaUsuario;
	}

	public DefaultTableModel getModelUsuario() {
		return modelUsuario;
	}

	public void setModelUsuario(DefaultTableModel modelUsuario) {
		this.modelUsuario = modelUsuario;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
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

	public void llenarTabla(List<UsuarioDTO> usuario) {
		this.getModelUsuario().setRowCount(0); //Para vaciar la tabla
		this.getModelUsuario().setColumnCount(0);
		this.getModelUsuario().setColumnIdentifiers(this.getNombreColumnas());

		for (UsuarioDTO s : usuario)
		{
			String nombre=s.getNombre()+" "+s.getApellido();
			String user=s.getNombreUsuario();
			String mail=s.getMail();
			String permiso=s.getRol();
			String sucursal=s.getSucursal();
			String estado=s.getEstado();
						
			Object[] fila = {nombre,user,mail,permiso,sucursal, estado};
			this.getModelUsuario().addRow(fila);
		}
	}
	public void mostrar() {
		setVisible(true);
	}

	public void cerrar() {
		this.dispose();
	}

	public int mostrarConfirmacionBorrar() {
		return JOptionPane.showOptionDialog(null, idioma.getString("borrar.confirmacion"), idioma.getString("confirmacion"), JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
	}


}
