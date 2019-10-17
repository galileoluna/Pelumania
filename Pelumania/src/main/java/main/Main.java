package main;

import org.apache.log4j.BasicConfigurator;

import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;

public class Main {
	
    public static void main( String[] args )
    {
    	BasicConfigurator.configure();
        Sistema sistema = new Sistema(new DAOSQLFactory());
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, sistema);
    }
 
} 
