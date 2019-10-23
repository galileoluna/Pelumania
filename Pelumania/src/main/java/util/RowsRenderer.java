package util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RowsRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	private int columna ;

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
    Component cell = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
    
    if(table.getValueAt(row,columna).equals("Activa"))
    {
    	this.setBackground(Color.green);
    }
    	if(table.getValueAt(row,columna).equals("Reprogramar"))
	{
    		this.setBackground(Color.yellow);
	}
    	if(table.getValueAt(row,columna).equals("En Curso"))
	{
    		this.setBackground(Color.orange);
	}
    	if(table.getValueAt(row,columna).equals("Cancelada"))
	{
    		this.setBackground(Color.red);
	}
    	if(table.getValueAt(row,columna).equals("Vencida"))
	{
    		this.setBackground(Color.LIGHT_GRAY);
	}
    	if(table.getValueAt(row,columna).equals("Finalizada"))
	{
    		this.setBackground(Color.blue);
	}
  
	return this;
}
}
