package dto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

public class BackUpDTO {
	private static String rutaMysqlExp="C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump";
	private static String rutaMysqlImp="C:/Program Files/MySQL/MySQL Server 8.0/bin/mysql";
	private static String confBdd="-u root -proot pelumania2";
	private static String nombreBackSql="BackUpSql/Backup_Pelumania.sql";
	


	public static boolean exportar() {

		   try {
		      Process p = Runtime
		            .getRuntime()
		            .exec(rutaMysqlExp+" "+confBdd);

		      InputStream is = p.getInputStream();
		      FileOutputStream fos = new FileOutputStream(nombreBackSql);
		      byte[] buffer = new byte[1000];

		      int leido = is.read(buffer);
		      while (leido > 0) {
		         fos.write(buffer, 0, leido);
		         leido = is.read(buffer);
		      }

		      fos.close();
		      return true;

		   } catch (Exception e) {
		      e.printStackTrace();
		      return false;
		   }
		}
	
	public static boolean importar() {
		System.out.println("llegue al importar dto");
		   try {
		      Process p = Runtime
		            .getRuntime()
		            .exec(rutaMysqlImp+" "+confBdd);
		      System.out.println("llegue al importar dto2");
		      OutputStream os = p.getOutputStream();
		      FileInputStream fis = new FileInputStream(nombreBackSql);
		      byte[] buffer = new byte[1000];
		      System.out.println("llegue al importar dto3");
		      int leido = fis.read(buffer);
		      while (leido > 0) {
		    	  System.out.println("llegue al importar dto4");
		         os.write(buffer, 0, leido);
		         leido = fis.read(buffer);
		      }
		      System.out.println("llegue al importar dto5");
		      os.flush();
		      os.close();
		      fis.close();
		      return true;

		   } catch (Exception e) {
		      e.printStackTrace();
		      return false;
		   }
		}
	
	public static String getNombreBackSql() {
		return nombreBackSql;
	}
}
