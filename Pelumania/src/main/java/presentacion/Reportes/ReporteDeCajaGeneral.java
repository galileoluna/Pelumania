package presentacion.Reportes;

import java.io.File;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
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

public class ReporteDeCajaGeneral {
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	private Logger log = Logger.getLogger(ReporteComprobante.class);
	//Recibe la lista de personas para armar el reporte
    public ReporteDeCajaGeneral(List<MovimientoCajaDTO> caja, Timestamp desde, Timestamp hasta)
    {
    	if(caja.size()>0) {
    		System.out.println(caja.get(0).getIdCliente());
    	}
    	List<MovimientoCajaDTO> coleccion =caja;
    	
    	//Hardcodeado
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Desde", desde);
		parametersMap.put("Hasta", hasta);
		
		//ordenamos los movimientos para luego seperarlos por ingreso y egreso
		Collections.sort(coleccion, new Comparator<MovimientoCajaDTO>() {

			@Override
			public int compare(MovimientoCajaDTO movimiento1, MovimientoCajaDTO movimiento2) {

				String mov1 = movimiento1.getIdCategoria() + "";
				String mov2 = movimiento2.getIdCategoria() + "";
				
				int resultado = mov1.compareTo(mov2);

				return resultado;
			}

		});
				
    	try		{
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile("src" + File.separator + "main" + File.separator + "java" + File.separator +  "presentacion" + File.separator + "reportes" + File.separator + "ReporteDeCajaGeneral.jasper" );
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
					new JRBeanCollectionDataSource(coleccion));
    		log.info("Se cargó correctamente el reporte");
		}
		catch( JRException ex ) 
		{
			log.error("Ocurrió un error mientras se cargaba el archivo ReporteDeCajaGeneral.Jasper", ex);
		}
    }       
    
    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
}

