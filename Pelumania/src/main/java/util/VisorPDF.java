package util;

import javax.swing.JOptionPane;

public class VisorPDF {
	String archivo;
	
	public VisorPDF(String idioma) {
		if(idioma.compareToIgnoreCase("ingles")==0) {
			archivo="manual\\manualing.pdf";
		}else archivo="manual\\manualesp.pdf";
	}
	
	public void run() {
		try {
			Runtime.getRuntime().exec("Rundll32 url.dll, FileProtocolHandler "+ archivo);
		}
		catch(Exception e ){
			JOptionPane.showMessageDialog(null, "no se pudo encontrar el archivo");
		}
	}
}
