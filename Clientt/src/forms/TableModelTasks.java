/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import domen.Task;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sofija
 */
public class TableModelTasks extends AbstractTableModel
{

    private List<Task> tasks;
    private String[] columns =
    {
        "Naziv", "Opis"
    };

  
    public TableModelTasks()
    {
        tasks = new LinkedList<>();
    }

    public void setData(List<Task> tasks)
    {
        this.tasks = tasks;
    }

    @Override
    public int getRowCount()
    {
        return tasks.size();
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
        Task z = tasks.get(rowIndex);
        switch (columnIndex)
        {
            case 0:
                return z.getName();
            case 1:
                return z.getDescription();
            default:
                return 0;
        }
    }

    public Task getZadatak(int red)
    {
        return tasks.get(red);
    }

    void addZadatak(Task z)
    {
        tasks.add(z);
    }

}
