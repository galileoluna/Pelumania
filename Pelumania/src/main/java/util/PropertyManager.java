package util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertyManager {
	private static Properties p = new Properties();
	private static String carpetaProp = "idioma/";
	private static String extension = ".properties";
	
	public static String leer(String fileName,String clave) {
		FileInputStream is = null;
		System.out.println(fileName);
		try {
			is = new FileInputStream(carpetaProp+fileName+extension);
			p.load(is);
//			System.out.println("item: " + p.getProperty(clave)); 
			return p.getProperty(clave);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void escribir(String fileName, String clave, String valor) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(carpetaProp+fileName+extension);
//			System.out.println(clave +"- " + valor);
			p.setProperty(clave, valor);
			p.store(fos, "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
		
	public static HashMap<String,String> getProperty(String fileName) {
		FileInputStream is = null;
		HashMap<String,String> properMap = new HashMap<String, String>();
		try {
			is = new FileInputStream(carpetaProp+fileName+extension);
			p.load(is);
			Enumeration<Object> keys = p.keys();
			
			
			while (keys.hasMoreElements()){
			   Object key = keys.nextElement();
			   properMap.put((String)key, (String)p.get(key));
//			    System.out.println(key + " = "+ p.get(key));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return properMap;
	}
	
}//--> FIN
