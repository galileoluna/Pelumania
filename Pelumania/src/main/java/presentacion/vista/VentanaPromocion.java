package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.PromocionDTO;

import javax.swing.JButton;

import persistencia.conexion.Conexion;
import java.awt.Toolkit;

public class VentanaPromocion{
	
	private static VentanaPromocion INSTANCE;
	private JFrame frmPromocion;
	private JTable tablaPromocion;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnEditar;
	private JButton btnAsignarServicio;
	private DefaultTableModel modelPromocion;
	private  String[] nombreColumnas = {"Decripcion","Fecha Inicio","Fecha Fin","Descuento","Puntos","Estado"};

	public VentanaPromocion() 
	{
		super();
		initialize();
	}
	public static VentanaPromocion getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPromocion(); 	
			return new VentanaPromocion();
		}
		else
			return INSTANCE;
	}

	private void initialize() 
	{
		frmPromocion = new JFrame();
		frmPromocion.setTitle("Promociones");
		frmPromocion.setBounds(100, 100, 1001, 300);
		frmPromocion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPromocion.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 975, 262);
		frmPromocion.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 786, 182);
		panel.add(spPersonas);
		
		modelPromocion = new DefaultTableModel(null,nombreColumnas);
		tablaPromocion = new JTable(modelPromocion);
		
		tablaPromocion.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPromocion.getColumnModel().getColumn(0).setResizable(false);
		tablaPromocion.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPromocion.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaPromocion);
		
		btnAgregar = new JButton("Agregar Promocion");
		btnAgregar.setBounds(111, 228, 153, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar Promocion");
		btnEditar.setBounds(357, 228, 153, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar Promocion");
		btnBorrar.setBounds(608, 228, 159, 23);
		panel.add(btnBorrar);

		
		btnAsignarServicio = new JButton("Asignar Servicio");
		btnAsignarServicio.setBounds(806, 85, 159, 23);
		panel.add(btnAsignarServicio);
		
	}
	
	public void show()
	{
		this.frmPromocion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmPromocion.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}
 
	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
		
	public JButton getBtnEditar() 
	{
		return btnEditar;
	}
	
	public DefaultTableModel getmodelPromocion() 
	{
		return modelPromocion;
	}
	
	public JTable gettablaPromocion()
	{
		return tablaPromocion;
	}

	public String[] getNombreColumnas(){
		return nombreColumnas;
	}
	
	public JButton getbtnAsignarServicio() {
		return this.btnAsignarServicio;
	}

	public void llenarTabla(List<PromocionDTO> promociones) {
		this.getmodelPromocion().setRowCount(0); //Para vaciar la tabla
		this.getmodelPromocion().setColumnCount(0);
		this.getmodelPromocion().setColumnIdentifiers(this.getNombreColumnas());

		for (PromocionDTO p : promociones)		{ 
			String descripcion = p.getDescripcion();
			Date FechaInicio = p.getFechaInicio();
			Date FechaFin=p.getFechaFin();
			Double descuento=p.getDescuento();
			int puntos=p.getPuntos();
			String estado=p.getEstado();
			Object[] fila = {descripcion, FechaInicio,FechaFin,descuento,puntos,estado};
			this.getmodelPromocion().addRow(fila);
		}
		
	} 
}
