/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sofija
 */
public class EmployeeBroker implements DBBase<Employee>
{
    PreparedStatement ps;

    public EmployeeBroker()
    {
    }
    
    @Override
     public Employee read(int id) throws SQLException
    {
        ps = GetPreparedStatement("SELECT * FROM zaposleni WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = selectSQL(ps);
        Employee z = null;
        if (rs.next())
        {
            z = getRecord(rs);
        }
        return z;
    }

    public List<Employee> getList(boolean selected) throws SQLException
    {
        ps=GetPreparedStatement("SELECT * FROM zaposleni WHERE obrisan=? ORDER BY prezime, ime");
        ps.setBoolean(1, selected);

        ResultSet rs = selectSQL(ps);
        
        List<Employee> zaposleni=new LinkedList<>();
        
        while(rs.next()){
            Employee z=getRecord(rs);
            zaposleni.add(z);
        }
        
        return zaposleni;
    }
    
    @Override
     public boolean save(Employee zaposleni) throws SQLException
    {
        if(zaposleni.getId()==0)
        {
            ps=GetPreparedStatement("INSERT INTO zaposleni VALUES (null,?,?,?,?,?,?)");
        }
        else
        {
            ps=GetPreparedStatement("UPDATE zaposleni SET ime=?, prezime=?, username=?, tip=?,obrisan=?, password=? WHERE id=?");
            ps.setInt(7, zaposleni.getId());
        }
        ps.setString(1, zaposleni.getName());
        ps.setString(2, zaposleni.getSurname());
        ps.setString(3, zaposleni.getUsername());
        ps.setInt(4, zaposleni.getUserType());
        ps.setBoolean(5, zaposleni.isDeleted());
        ps.setString(6, zaposleni.getPassword());
        
        executeSQL(ps);
   
        return true;
    }

    private Employee getRecord(ResultSet rs) throws SQLException
    {
        Employee z= new Employee();
        
        z.setId(rs.getInt("id"));
        z.setName(rs.getString("ime"));
        z.setSurname(rs.getString("prezime"));
        z.setUsername(rs.getString("username"));
        z.setUserType(rs.getInt("tip"));
        z.setPassword(rs.getString("password"));
        
        return z;
    }

    @Override
   public void delete(int id) throws Exception
    {
        ProjectBroker projectDB= new ProjectBroker();
        projectDB.isEmployeeEngaged(id);
        
        ps = GetPreparedStatement("UPDATE zaposleni SET obrisan=true WHERE id=?");

        ps.setInt(1, id);
        
        executeSQL(ps);
    }

    @Override
    public void undelete(int id) throws SQLException
    {
        ps = GetPreparedStatement("UPDATE zaposleni SET obrisan=false WHERE id=?");

        ps.setInt(1, id);
        
        executeSQL(ps);
    }

    public Employee find(String[] data) throws SQLException 
    {
        
        ps = GetPreparedStatement("SELECT * FROM zaposleni WHERE username=? and password=?");
        ps.setString(1, data[0]);
        ps.setString(2, data[1]);
        ResultSet rs = selectSQL(ps);
        Employee z = null;
        if (rs.next())
        {
            z = getRecord(rs);
        }
        return z;
    }
   
    
}
