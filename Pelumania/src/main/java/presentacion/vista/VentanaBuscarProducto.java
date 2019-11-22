package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

import dto.ClienteDTO;
import dto.ProductoDTO;
import util.PropertyManager;

public class VentanaBuscarProducto  extends JFrame {

	private JPanel contentPane;
	private static VentanaBuscarProducto INSTANCE;

	private JButton btn_Cancelar;

	//configuracion de idioma
	private Locale locale = new Locale (PropertyManager.leer("configuracion", "idioma"), PropertyManager.leer("configuracion", "pais"));
	private ResourceBundle idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);
	
	
	private String[]  nombreColumnas = {this.idioma.getString("nombre"), this.idioma.getString("precio.pesos"),
										this.idioma.getString("precio.dolares"), this.idioma.getString("puntos")};
	private JTable tablaClientes;
	private DefaultTableModel modelClientes;

	JComboBox<String> CBoxBuscarPor;

	private JButton btnSeleccionarProducto;
	private TableRowSorter rowSorter;
	private JTextComponent txtFiltro;

	public static VentanaBuscarProducto getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaBuscarProducto();
			return new VentanaBuscarProducto();
		} else {
			return INSTANCE;
		}
	}
	
	private VentanaBuscarProducto()
	{
		super();

		setTitle(this.idioma.getString("producto"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 735, 318);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 699, 257);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBuscarPor = new JLabel(this.idioma.getString("filtrar"));
		lblBuscarPor.setBounds(23, 11, 133, 23);
		panel.add(lblBuscarPor);

		JScrollPane spClientes = new JScrollPane();
		spClientes.setBounds(23, 79, 666, 143);
		panel.add(spClientes);

		modelClientes = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
					return false;
			}
		};

		tablaClientes = new JTable(modelClientes);

		tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaClientes.getColumnModel().getColumn(0).setResizable(false);
		tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(10);
		tablaClientes.getColumnModel().getColumn(1).setResizable(false);
		tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaClientes.getColumnModel().getColumn(2).setResizable(false);
		tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(10);
		tablaClientes.getColumnModel().getColumn(3).setResizable(false);

		spClientes.setViewportView(tablaClientes);

		btnSeleccionarProducto = new JButton(idioma.getString("seleccionar"));
		btnSeleccionarProducto.setBounds(571, 233, 118, 23);
		panel.add(btnSeleccionarProducto);
		
		
		
		
		//////////////////////////////////////////////////////
		
		///                 PARA FILTRAR  
		
		////////////////////////////////////
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(112, 11, 147, 23);
		panel.add(txtFiltro);
		((JTextField) txtFiltro).setColumns(10);
		
		rowSorter = new TableRowSorter<>(tablaClientes.getModel());
		this.tablaClientes.setRowSorter(rowSorter);
		
		//para filtrar
		txtFiltro.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtFiltro.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtFiltro.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
		
		
		///////////////////////////////////////////////
		
		///               TERMINO FILTRAR
		
		/////////////////////////////////////////////

		this.setVisible(false);
	}

	public static VentanaBuscarProducto getINSTANCE() {
		return INSTANCE;
	}


	public JButton getBtnSeleccionarProducto() {
		return btnSeleccionarProducto;
	}

	public void setBtnSeleccionarCliente(JButton btnSeleccionarCliente) {
		this.btnSeleccionarProducto = btnSeleccionarCliente;
	}

	public JButton getBtn_Cancelar() {
		return btn_Cancelar;
	}

	public void setBtn_Cancelar(JButton btn_Cancelar) {
		this.btn_Cancelar = btn_Cancelar;
	}

	public JTable getTablaClientes() {
		return tablaClientes;
	}

	public void setTablaClientes(JTable tablaClientes) {
		this.tablaClientes = tablaClientes;
	}

	public DefaultTableModel getModelClientes() {
		return modelClientes;
	}

	public void setModelClientes(DefaultTableModel modelClientes) {
		this.modelClientes = modelClientes;
	}

	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}

	public void llenarTabla(List<ProductoDTO> productos) {
		this.getModelClientes().setRowCount(0); //Para vaciar la tabla
		this.getModelClientes().setColumnCount(0);
		this.getModelClientes().setColumnIdentifiers(this.getNombreColumnas());

		for (ProductoDTO p: productos)
		{
			String nombre = p.getNombre();
			BigDecimal precioPesos = p.getPrecioLocal();
			BigDecimal precioDolares = p.getPrecioDolar();
			int puntos = p.getPuntos();
			
			Object[] fila = {nombre, precioPesos,precioDolares , puntos};
			this.getModelClientes().addRow(fila);
		}

	}
	
	private String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void cerrar()
	{
		this.dispose();
	}
}