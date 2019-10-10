package presentacion.vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.HorarioDTO;
import dto.ProfesionalDTO;
import dto.PromocionDTO;

import javax.swing.JButton;

import persistencia.conexion.Conexion;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JComboBox;

public class VentanaPromosionesVigentes{
	private static VentanaPromosionesVigentes INSTANCE;
	private JFrame frmServProf;
	private JTable tablaServicioPromocion;
	private DefaultTableModel modelPromVigen;
	private  String[] nombreColumnas = {"Decripcion","Fecha Inicio","Fecha Fin","Descuento","Puntos"};

	public VentanaPromosionesVigentes() 
	{
		super();
		initialize();
	}
	public static VentanaPromosionesVigentes getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPromosionesVigentes(); 	
			return new VentanaPromosionesVigentes();
		}
		else
			return INSTANCE;
	}

	private void initialize() 
	{
		frmServProf = new JFrame();
		frmServProf.setTitle("Promociones Vigentes");
		frmServProf.setBounds(100, 100, 520, 442);
		frmServProf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServProf.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 484, 381);
		frmServProf.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 10, 464, 360);
		panel.add(spPersonas);
		
		modelPromVigen = new DefaultTableModel(null,nombreColumnas);
		tablaServicioPromocion = new JTable(modelPromVigen);
		
		tablaServicioPromocion.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaServicioPromocion.getColumnModel().getColumn(0).setResizable(false);
		
		
		spPersonas.setViewportView(tablaServicioPromocion);
		
	}
	
	public void show()
	{
		this.frmServProf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frmServProf.setVisible(true);
	}

	public DefaultTableModel getModelPromVigente() 
	{
		return modelPromVigen;
	}
		
	public JTable getTablServicioPromocion()
	{
		return tablaServicioPromocion;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
	
	public void llenarTabla(List<PromocionDTO> promociones) {
		this.getModelPromVigente().setRowCount(0); //Para vaciar la tabla
		this.getModelPromVigente().setColumnCount(0);
		this.getModelPromVigente().setColumnIdentifiers(this.getNombreColumnas());

		for (PromocionDTO p : promociones)		{ 
			String descripcion = p.getDescripcion();
			Date FechaInicio = p.getFechaInicio();
			Date FechaFin=p.getFechaFin();
			Double descuento=p.getDescuento();
			int puntos=p.getPuntos();
			Object[] fila = {descripcion, FechaInicio,FechaFin,descuento,puntos};
			this.getModelPromVigente().addRow(fila);
		}
			
	} 
}
