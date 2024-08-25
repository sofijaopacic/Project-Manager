/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.ProjectItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sofija
 */
public class ProjectItemsBroker implements DBBase<ProjectItem>
{

    PreparedStatement ps;

    public List<ProjectItem> readItems(int projekatID) throws SQLException
    {
        ps = GetPreparedStatement("SELECT * FROM projekatstavke WHERE projekatID=? AND obrisan=false");
        ps.setInt(1, projekatID);
        ResultSet rs = selectSQL(ps);
        
        TaskBroker tdb=new TaskBroker();
        EmployeeBroker edb= new EmployeeBroker();

        ProjectItem stavka;
        List<ProjectItem> stavke = new LinkedList<>();

        while (rs.next())
        {
            stavka = new ProjectItem();
            stavka.setId(rs.getInt("id"));
            stavka.setProjectID(rs.getInt("projekatID"));
            stavka.setTask(tdb.read(rs.getInt("zadatakID")));
            stavka.setEmployee(edb.read(rs.getInt("zaposleniID")));

            stavke.add(stavka);
        }

        return stavke;
    }

    @Override
    public boolean save(ProjectItem item) throws SQLException
    {
        if(item.getId()==0){
            ps=GetPreparedStatement("INSERT INTO projekatstavke VALUES (null,?,?,?,false)");
        }
        else{
            ps=GetPreparedStatement("UPDATE projekatstavke SET projekatID=?,zaposleniID=?, zadatakID=?,  obrisan=false WHERE id=?");
            ps.setInt(4, item.getId());
        }
        
        ps.setInt(1, item.getProjectID());
        ps.setInt(2, item.getEmployee().getId());
        ps.setInt(3, item.getTask().getId());
        
        executeSQL(ps);
        
        return true;
        
    }

    @Override
    public void delete(int id) throws SQLException
    {
        ps=GetPreparedStatement("UPDATE projekatstavke SET obrisan=true WHERE projekatID=?");
        ps.setInt(1, id);
        
        executeSQL(ps);
    }

    @Override
    public void undelete(int id) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ProjectItem read(int id) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
