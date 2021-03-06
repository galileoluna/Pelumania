package util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import dto.CitaDTO;
import dto.UsuarioDTO;
import modelo.Sistema;
import presentacion.Reportes.ReporteComprobante;

public class MailService {

	public static void enviarComprobanteCita(Sistema sistema, CitaDTO cita, String destinatario) {
		
		String asunto = getAsunto(cita);
		String cuerpoHTML = getCuerpo();
		
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "pelumaniaoficial@gmail.com";  //Para la dirección nomcuenta@gmail.com
	    String clave = "Pelumania01";
	    
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", clave);    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);

	        //html
		    MimeBodyPart messageBodyPart = new MimeBodyPart();
		    messageBodyPart.setContent(cuerpoHTML,"text/html");
		   
		    //creamos un multiparte
		    Multipart multiParte = new MimeMultipart();
		    multiParte.addBodyPart(messageBodyPart);
            
            //PDF
		    BodyPart adjuntoPDF = new MimeBodyPart();
		    ReporteComprobante comprobante = new ReporteComprobante(cita);
		    comprobante.exportarPDF();
                        
		    //si la ruta del pdf no es nula lo agregamos al mail
            if (!comprobante.getRutaPDF().equals("")){
            	adjuntoPDF.setDataHandler(new DataHandler(new FileDataSource(comprobante.getRutaPDF())));
            	adjuntoPDF.setFileName(comprobante.getReporteLleno().getName() + ".pdf");           
            }
            
           if (!comprobante.getRutaPDF().equals("")){
               multiParte.addBodyPart(adjuntoPDF);
           }

            message.setContent(multiParte);
	        
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}

	private static String getCuerpo() {
		
		return "<p>Estimado cliente</p>" +
		"<p><strong>a continuacion se adjunta un comprobante de su cita</strong></p>" +
		"<p>Muchas gracias!</p>" +
		"<p><em>Atte Pelumanía</em></p>";
	}

	private static String getAsunto(CitaDTO cita) {
		return "Comprobante de cita Nº " + cita.getIdCita() +" - pelumania";
		
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	/////////////// RECUPERAR PASSWORD
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public static void enviarPassProvisorio(UsuarioDTO usuario) {
		
		String asunto = getAsuntoPassProvisorio();
		String cuerpoHTML = getCuerpoPassProvisiorio(usuario);
		
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "pelumaniaoficial@gmail.com";  //Para la dirección nomcuenta@gmail.com
	    String clave = "Pelumania01";
	    
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", clave);    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, usuario.getMail());   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);

	        //html
		    MimeBodyPart messageBodyPart = new MimeBodyPart();
		    messageBodyPart.setContent(cuerpoHTML,"text/html");
		   
		    //creamos un multiparte
		    Multipart multiParte = new MimeMultipart();
		    multiParte.addBodyPart(messageBodyPart);
            
            message.setContent(multiParte);
	        
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}
	
	private static String getAsuntoPassProvisorio() {
		return "Recuperación de clave - pelumania";

	}

	private static String getCuerpoPassProvisiorio(UsuarioDTO usuario) {
		return "<p>Hola "+ usuario.getNombre() + " " + usuario.getApellido() + " " + "Tu nueva contraseña provisoria para Pelumanía es</p>" +
				"<p><strong>"+ usuario.getContrasenia() + "</strong></p>" +
				"<p>Por favor, cambiala al iniciar sesión</p>" +
				"<p><em>Atte Pelumanía</em></p>";
		
		} 
	

	//////////////////////////////////////////////////////////////////////////////////////////
	
	/////////////// RECORDATORIO
	
	//////////////////////////////////////////////////////////////////////////////////////////

	
public static void enviarRecordatorioCita(Sistema sistema, CitaDTO cita, String destinatario) {
		
		String asunto = getAsunto(cita);
		String cuerpoHTML = getRecordatorio();
		
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "pelumaniaoficial@gmail.com";  //Para la dirección nomcuenta@gmail.com
	    String clave = "Pelumania01";
	    
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", clave);    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);

	        //html
		    MimeBodyPart messageBodyPart = new MimeBodyPart();
		    messageBodyPart.setContent(cuerpoHTML,"text/html");
		   
		    //creamos un multiparte
		    Multipart multiParte = new MimeMultipart();
		    multiParte.addBodyPart(messageBodyPart);
            
            //PDF
		    BodyPart adjuntoPDF = new MimeBodyPart();
		    ReporteComprobante comprobante = new ReporteComprobante(cita);
		    comprobante.exportarPDF();
                        
		    //si la ruta del pdf no es nula lo agregamos al mail
            if (!comprobante.getRutaPDF().equals("")){
            	adjuntoPDF.setDataHandler(new DataHandler(new FileDataSource(comprobante.getRutaPDF())));
            	adjuntoPDF.setFileName(comprobante.getReporteLleno().getName() + ".pdf");           
            }
            
           if (!comprobante.getRutaPDF().equals("")){
               multiParte.addBodyPart(adjuntoPDF);
           }

            message.setContent(multiParte);
	        
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, clave);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}
	
	private static String getRecordatorio() {
	
	return "<p>Estimado cliente</p>" +
	"<p><strong>Recurde que tiene una cita pendiente en 48hs </strong></p>" +
	"<p>Muchas gracias!</p>" +
	"<p><em>Atte Pelumanía</em></p>";
	}

}
