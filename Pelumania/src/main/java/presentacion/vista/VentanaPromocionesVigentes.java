package presentacion.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dto.PromocionDTO;
import util.PropertyManager;

public class VentanaPromocionesVigentes{
	private static VentanaPromocionesVigentes INSTANCE;
	private JFrame frmServProf;
	private JTable tablaServicioPromocion;
	private DefaultTableModel modelPromVigen;
	
	//configuracion de idioma
	private Locale locale = new Locale (PropertyManager.leer("configuracion", "idioma"), PropertyManager.leer("configuracion", "pais"));
	private ResourceBundle idioma = ResourceBundle.getBundle("presentacion/idioma/bundle", locale);

	private  String[] nombreColumnas = {idioma.getString("descripcion"),idioma.getString("inicio"),idioma.getString("fin"),idioma.getString("descuento"),idioma.getString("puntos")};

	public VentanaPromocionesVigentes() 
	{
		super();
		initialize();
	}
	public static VentanaPromocionesVigentes getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPromocionesVigentes(); 	
			return new VentanaPromocionesVigentes();
		}
		else
			return INSTANCE;
	}

	private void initialize() 
	{
		frmServProf = new JFrame();
		frmServProf.setTitle(idioma.getString("promocion.vigente"));
		frmServProf.setBounds(100, 100, 520, 442);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmServProf.setLocation(dim.width/2-frmServProf.getSize().width/2, dim.height/2-frmServProf.getSize().height/2);
		
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
