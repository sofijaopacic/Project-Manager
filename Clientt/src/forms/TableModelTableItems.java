/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import domen.*;
import java.util.LinkedList;

/**
 *
 * @author Sofija
 */
public class TableModelTableItems extends AbstractTableModel
{

    List<ProjectItem> items;
    String[] columns =
    {
        "Ime i prezime", "Zadatak"
    };

    public TableModelTableItems()
    {
        items = new LinkedList<>();

    }

    @Override
    public int getRowCount()
    {
        return items.size();
    }

    @Override
    public int getColumnCount()
    {
        return columns.length;
    }

    @Override
    public String getColumnName(int column)
    {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        ProjectItem item = items.get(rowIndex);

        switch (columnIndex)
        {
            case 0:

                if(item.getEmployee()!=null) return item.getEmployee().toString();

            case 1:
                if(item.getTask()!=null) return item.getTask().toString();
            default:
                return "";
        }

    }

    void setData(List<ProjectItem> items)
    {
        this.items = items;
        fireTableDataChanged();
    }

    
   

}
