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
	Color gris = new Color(155,155,155);
	
    setBackground(Color.white);
    table.setForeground(Color.black);
    super.getTableCellRendererComponent(table, value, selected, focused, row, column);
    
    if(table.getValueAt(row,columna).equals("Inactivo"))
    	{
        this.setBackground(gris);
    	}
  
	return this;
  
}
  }