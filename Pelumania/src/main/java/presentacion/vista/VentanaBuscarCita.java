package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dto.CitaDTO;

public class VentanaBuscarCita extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaBuscarCita INSTANCE;

	private JButton btn_Cancelar;
	private JTextField txtFiltro;
	private String[] nombreColumnas = {"Nro","Nombre", "Apellido", "Dia","HoraInicio","HoraFin","PrecioLocal","PrecioDolar"};
	private JTable tablaCitas;
	private DefaultTableModel modelCitas;
	private TableRowSorter<TableModel> rowSorter;

	private JButton btnSeleccionarCita;

	public static VentanaBuscarCita getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaBuscarCita();
			return new VentanaBuscarCita();
		} else {
			return INSTANCE;
		}
	}
	
	private VentanaBuscarCita()
	{
		super();
		
		setTitle("Buscar Cita");
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

		txtFiltro = new JTextField();
		txtFiltro.setBounds(188, 223, 147, 23);
		panel.add(txtFiltro);
		txtFiltro.setColumns(10);

		JScrollPane spCitas = new JScrollPane();
		spCitas.setEnabled(false);
		spCitas.setBounds(23, 11, 666, 174);
		panel.add(spCitas);

		modelCitas = new DefaultTableModel(null,nombreColumnas) {
			//Para que las celdas de la tabla no se puedan editar
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablaCitas = new JTable(modelCitas);
		tablaCitas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		

		tablaCitas.getColumnModel().getColumn(0).setPreferredWidth(10);
		tablaCitas.getColumnModel().getColumn(0).setResizable(false);
		tablaCitas.getColumnModel().getColumn(1).setPreferredWidth(10);
		tablaCitas.getColumnModel().getColumn(1).setResizable(false);
		tablaCitas.getColumnModel().getColumn(2).setPreferredWidth(10);
		tablaCitas.getColumnModel().getColumn(2).setResizable(false);
		tablaCitas.getColumnModel().getColumn(3).setPreferredWidth(100);
		tablaCitas.getColumnModel().getColumn(3).setResizable(false);
		tablaCitas.getColumnModel().getColumn(4).setPreferredWidth(10);
		tablaCitas.getColumnModel().getColumn(4).setResizable(false);
		tablaCitas.getColumnModel().getColumn(5).setPreferredWidth(10);
		tablaCitas.getColumnModel().getColumn(5).setResizable(false);

		spCitas.setViewportView(tablaCitas);

		btnSeleccionarCita = new JButton("Seleccionar");
		btnSeleccionarCita.setBounds(348, 223, 147, 23);
		panel.add(btnSeleccionarCita);
		
		rowSorter = new TableRowSorter<>(tablaCitas.getModel());
		this.tablaCitas.setRowSorter(rowSorter);
		
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
		
		this.setVisible(false);
	}

	public static VentanaBuscarCita getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(VentanaBuscarCita iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public JButton getBtnSeleccionarCita() {
		return btnSeleccionarCita;
	}

	public void setBtnSeleccionarCita(JButton btnSeleccionarCita) {
		this.btnSeleccionarCita = btnSeleccionarCita;
	}

	public JButton getBtn_Cancelar() {
		return btn_Cancelar;
	}

	public void setBtn_Cancelar(JButton btn_Cancelar) {
		this.btn_Cancelar = btn_Cancelar;
	}

	public JTable getTablaCitas() {
		return tablaCitas;
	}

	public void setTablaCitas(JTable tablaCitas) {
		this.tablaCitas = tablaCitas;
	}

	public DefaultTableModel getModelCitas() {
		return modelCitas;
	}

	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	public void llenarTabla(List<CitaDTO> citasEnTabla) {
		this.getModelCitas().setRowCount(0); //Para vaciar la tabla
		this.getModelCitas().setColumnCount(0);
		this.getModelCitas().setColumnIdentifiers(this.getNombreColumnas());

		for (CitaDTO cita : citasEnTabla)
		{
			int nro = cita.getIdCita();
			String nombre = cita.getNombre();
			String apellido = cita.getApellido();
			String dia = cita.getFecha().toString();
			String horaInicio = cita.getHoraInicio().toString();
			BigDecimal precioPesos = cita.getPrecioLocal();
			BigDecimal precioDolar = cita.getPrecioDolar();
			Object[] fila = {nro, nombre, apellido, dia, horaInicio, precioPesos, precioDolar};
			this.getModelCitas().addRow(fila);
		}

	}

	private String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void cerrar()
	{
		this.dispose();
	}

	public void mostrarErrorSinSeleccionar() {
			JOptionPane.showMessageDialog(new JFrame(), "Debe seleccionar una cita", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}		
