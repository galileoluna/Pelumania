package presentacion.Reportes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import dto.MovimientoCajaDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.conexion.Conexion;

public class ReporteRankingVendedores {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	private Logger log = Logger.getLogger(ReporteComprobante.class);
	//Recibe la lista de personas para armar el reporte
    public ReporteRankingVendedores(Date desde, Date hasta)
    {
    	
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Desde", desde);
		parametersMap.put("Hasta", hasta);
		
    	try		{
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile("src" + File.separator + "main" + File.separator + "java" + File.separator +  "presentacion" + File.separator + "reportes" + File.separator + "ReporteRankingVentas.jasper" );
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
					Conexion.getConexion().getSQLConexion());
    		log.info("Se cargó correctamente el reporte");
		}
		catch( JRException ex ) 
		{
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteRankingVendedores.Jasper", ex);
		}
    }       
    
    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
}
