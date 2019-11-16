package util;

import javax.swing.JOptionPane;

public class VisorPDF {
	String archivo="manual\\cv.pdf";
	
	public void run() {
		try {
			Runtime.getRuntime().exec("Rundll32 url.dll, FileProtocolHandler "+ archivo);
		}
		catch(Exception e ){
			JOptionPane.showMessageDialog(null, "no se pudo encontrar el archivo");
		}
	}
}
