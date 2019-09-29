package main;

import modelo.Sistema;
import persistencia.dao.mariadb.DAOSQLFactory;
import presentacion.controlador.Controlador;
import presentacion.vista.Vista;

public class Main {
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Sistema sistema = new Sistema(new DAOSQLFactory());
        Vista vista = new Vista();
        
        Controlador controlador = new Controlador(vista, sistema);
    }

}
