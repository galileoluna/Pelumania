package main;

import org.apache.log4j.BasicConfigurator;

import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;
import presentacion.controlador.ControladorLogin;
import presentacion.vista.Login;

public class Main {
	
    public static void main( String[] args )
    {
    	BasicConfigurator.configure();
        Sistema sistema = new Sistema(new DAOSQLFactory());
        Login login = new Login();
        ControladorLogin controladorLog= new ControladorLogin(login,sistema);
    } 
} 
