/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import domen.Constants;
import domen.ProjectSimple;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sofija
 */
public class TableModelProjects extends AbstractTableModel
{
    
    List<ProjectSimple> projects;
    String[] columns={"Status","Naziv projekta","Vođa projekta"};

    public TableModelProjects()
    {
        projects= new LinkedList<>();
    }
    
    

    @Override
    public int getRowCount()
    {
        return projects.size();
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
        ProjectSimple p=projects.get(rowIndex);
        
        switch (columnIndex)
        {
            case 0:
                return p.isActive() ? Constants.PROJECT_ACTIVE : Constants.PROJECT_FINISHED;
            case 1:
                return p.getName();
            case 2:
                return p.getLeaderName();
            default:
                return "";
        }
    }

    void setData(List<ProjectSimple> projekti)
    {
        this.projects=projekti;
    }

    ProjectSimple getProjekat(int red)
    {
        return projects.get(red);
    }
    

    
    
    
}
