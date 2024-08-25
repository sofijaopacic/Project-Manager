/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Task;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sofija
 */
public class TaskBroker implements DBBase<Task>
{

    PreparedStatement ps;

    public TaskBroker()
    {
    }

    @Override
    public Task read(int id) throws SQLException
    {
        ps = GetPreparedStatement("SELECT * FROM zadaci WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = selectSQL(ps);
        Task z = null;
        if (rs.next())
        {
            z = getRecord(rs);
        }
        return z;
    }

    @Override
    public boolean save(Task zadatak) throws SQLException
    {

        if (zadatak.getId() == 0)
        {
            ps = GetPreparedStatement("INSERT INTO zadaci VALUES(null,?,?,?)");

        } else
        {
            ps = GetPreparedStatement("UPDATE zadaci SET naziv=?, opis=?, obrisan=? WHERE id=?");
            ps.setInt(4, zadatak.getId());

        }
        ps.setString(1, zadatak.getName());
        ps.setString(2, zadatak.getDescription());
        ps.setBoolean(3, zadatak.isDeleted());

        executeSQL(ps);

        return true;
    }

    public List<Task> getList(boolean obrisan) throws SQLException
    {

        ps = GetPreparedStatement("SELECT * FROM zadaci WHERE obrisan=? ORDER BY naziv");
        ps.setBoolean(1, obrisan);

        ResultSet rs = selectSQL(ps);

        List<Task> zadaci = new LinkedList<>();

        while (rs.next())
        {
            Task z = getRecord(rs);

            zadaci.add(z);
        }

        return zadaci;

    }

    private Task getRecord(ResultSet rs) throws SQLException
    {
        Task z = new Task();
        z.setId(rs.getInt("id"));
        z.setName(rs.getString("naziv"));
        z.setDescription(rs.getString("opis"));
        z.setDeleted(rs.getBoolean("obrisan"));
        return z;
    }

    @Override
    public void delete(int id) throws Exception
    {
        ProjectBroker projectDB= new ProjectBroker();
        projectDB.isTaskEngaged(id);
        
        ps = GetPreparedStatement("UPDATE zadaci SET obrisan=true WHERE id=?");

        ps.setInt(1, id);
        
        executeSQL(ps);
    }

    @Override
    public void undelete(int id) throws SQLException
    {
        ps = GetPreparedStatement("UPDATE zadaci SET obrisan=false WHERE id=?");

        ps.setInt(1, id);
        
        executeSQL(ps);
    }

}
