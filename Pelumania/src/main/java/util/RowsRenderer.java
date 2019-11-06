package util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RowsRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	private int columna ;

	
	public static final Color rojo = new Color(225,64,68);
	public static final Color verde = new Color(129,152,48);
	public static final Color azul = new Color(22,138,197);
	public static final Color amarillo = new Color(248,214,115);
	public static final Color naranja = new Color(239,169,74);
	public static final Color gris = new Color(128,128,128);
	public static final Color cian= new Color (80,250,230);

/*Le pasas al constructor el numero de columna por el que queres que pinte*/

public RowsRenderer(int Colpatron)
{
    this.columna = Colpatron;
}


@Override
public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
{        
	
    setBackground(Color.white);
    table.setForeground(Color.black);
    super.getTableCellRendererComponent(table, value, selected, focused, row, column);
    
    if(table.getValueAt(row,columna).equals("Activa"))
    {
    	this.setBackground(verde);
    }
    	if(table.getValueAt(row,columna).equals("Reprogramar"))
	{
    		this.setBackground(amarillo);
	}
    	if(table.getValueAt(row,columna).equals("En Curso"))
	{
    		this.setBackground(naranja);
	}
    	if(table.getValueAt(row,columna).equals("Cancelada"))
	{
    		this.setBackground(rojo);
	}
    	if(table.getValueAt(row,columna).equals("Vencida"))
	{
    		this.setBackground(gris);
	}
    	if(table.getValueAt(row,columna).equals("Finalizada"))
	{
    		this.setBackground(azul);
	}
		if(table.getValueAt(row,columna).equals("Fiado"))
	{
    		this.setBackground(cian);
	}
  
	return this;
    }
}
