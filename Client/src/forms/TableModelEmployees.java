/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import domen.Employee;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Sofija
 */
public class TableModelEmployees extends AbstractTableModel
{

    List<Employee> emplyoees;
    String[] columns =
    {
        "Prezime", "Ime"
    };

    public TableModelEmployees()
    {
        emplyoees = new LinkedList<>();
    }

    public TableModelEmployees(List<Employee> zaposleni)
    {
        this.emplyoees = zaposleni;
    }

    @Override
    public int getRowCount()
    {
        return emplyoees.size();
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
        Employee z = emplyoees.get(rowIndex);

        switch (columnIndex)
        {
            case 0:
                return z.getSurname();
            case 1:
                return z.getName();

            default:
                return "";
        }
    }

    void setData(List<Employee> zaposleni)
    {
        this.emplyoees = zaposleni;
    }

    Employee getZaposleni(int red)
    {
        return emplyoees.get(red);
    }

}
