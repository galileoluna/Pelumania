package presentacion.Reportes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import dto.CitaDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.conexion.Conexion;

public class ReporteComprobante
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	private Logger log = Logger.getLogger(ReporteComprobante.class);
	private String rutaPDF;
//
//	//Recibe la lista de personas para armar el reporte
    public ReporteComprobante(CitaDTO cita)
    {
    	try {
    		
    		String ruta = "src" + File.separator + "main" + File.separator + "java" + File.separator +  "presentacion" + File.separator + "reportes" + File.separator + "comprobanteCita.jasper";
    		String rutaSubreporte = "src" + File.separator + "main" + File.separator + "java" + File.separator +  "presentacion" + File.separator + "reportes" + File.separator + "serviciosTurno.jasper";
    		
    		JasperReport subreporte = (JasperReport) JRLoader.loadObjectFromFile(rutaSubreporte);
			reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idCita", cita.getIdCita());
			
			reporteLleno = JasperFillManager.fillReport(ruta, parametros, Conexion.getConexion().getSQLConexion());
    		
			log.info("Se cargó correctamente el reporte");
    	} catch (JRException e) {
    		log.error("Ocurrió un error mientras se cargaba el archivo ReporteComprobante.Jasper", e);
    		JOptionPane.showMessageDialog(null, "Ha ocurrido un error al cargar el reporte: "+e);
			e.printStackTrace();
		}
    }       
    
    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
    public void exportarPDF(){
		try {
			File file = new File("c:\\reportes\\");
			 file.mkdirs();
			String nombrePDF = "c:\\reportes\\"+ this.reporteLleno.getName() +".pdf";
			JasperExportManager.exportReportToPdfFile(this.reporteLleno,  nombrePDF);
			log.info("El reporte se exporto a PDF correctamente");
			this.rutaPDF = nombrePDF;
			
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

    
    public void ComprobanteCita(Integer idCita) {
    	try {
    		
    		String ruta = "src" + File.separator + "main" + File.separator + "java" + File.separator +  "presentacion" + File.separator + "reportes" + File.separator + "comprobanteCita.jasper";
    		String rutaSubreporte = "src" + File.separator + "main" + File.separator + "java" + File.separator +  "presentacion" + File.separator + "reportes" + File.separator + "serviciosTurno.jasper";
    		
    		JasperReport subreporte = (JasperReport) JRLoader.loadObjectFromFile(rutaSubreporte);
			reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("idCita", idCita);
			
			reporteLleno = JasperFillManager.fillReport(ruta, parametros, Conexion.getConexion().getSQLConexion());
    		
			log.info("Se cargó correctamente el reporte");
    	} catch (JRException e) {
    		log.error("Ocurrió un error mientras se cargaba el archivo ReporteComprobante.Jasper", e);
    		JOptionPane.showMessageDialog(null, "Ha ocurrido un error al cargar el reporte: "+e);
			e.printStackTrace();
		}
    }
    
	public JasperReport getReporte() {
		return reporte;
	}

	public void setReporte(JasperReport reporte) {
		this.reporte = reporte;
	}

	public JasperViewer getReporteViewer() {
		return reporteViewer;
	}

	public void setReporteViewer(JasperViewer reporteViewer) {
		this.reporteViewer = reporteViewer;
	}

	public JasperPrint getReporteLleno() {
		return reporteLleno;
	}

	public void setReporteLleno(JasperPrint reporteLleno) {
		this.reporteLleno = reporteLleno;
	}

	public String getRutaPDF() {
		return rutaPDF;
	}

}
