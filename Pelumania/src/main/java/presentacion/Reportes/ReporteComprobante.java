package presentacion.Reportes;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import dto.CitaDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteComprobante
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	private Logger log = Logger.getLogger(ReporteComprobante.class);
	private String rutaPDF;

	//Recibe la lista de personas para armar el reporte
    public ReporteComprobante(CitaDTO cita)
    {
    	
    	List<CitaDTO> coleccion = new ArrayList<CitaDTO>();
    	coleccion.add(cita);
    	//Hardcodeado
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));		
    	try		{
    		String ruta = "src" + File.separator + "main" + File.separator + "java" + File.separator +  "presentacion" + File.separator + "reportes" + File.separator + "ReporteComprobante.jasper";
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile(ruta);
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
					new JRBeanCollectionDataSource(coleccion));
    		log.info("Se cargó correctamente el reporte");
		}
		catch( JRException ex ) 
		{
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteComprobante.Jasper", ex);
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
