/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Project;
import domen.ProjectSimple;
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
public class ProjectBroker implements DBBase<Project>
{

    PreparedStatement ps;

    @Override
    public Project read(int id) throws SQLException
    {
        ps = GetPreparedStatement("SELECT * FROM projekti WHERE id=? AND obrisan=false");
        ps.setInt(1, id);
        ResultSet rs = selectSQL(ps);

        Project p = new Project();
        ProjectItemsBroker items = new ProjectItemsBroker();

        if (rs.next())
        {
            p = getRecord(rs);
            p.setItems(items.readItems(id));

        }
        return p;
    }

    @Override
    public boolean save(Project projekat) throws SQLException
    {
        if (projekat.getId() == 0)
        {
            ps = GetPreparedStatement2("INSERT INTO projekti VALUES(null,?,?,?,?)");

        } else
        {
            ps = GetPreparedStatement("UPDATE projekti SET naziv=?, vodjaID=?, obrisan=?, aktivan=? WHERE id=?");
            ps.setInt(5, projekat.getId());

        }
        ps.setString(1, projekat.getName());
        ps.setInt(2, projekat.getLeaderID());
        ps.setBoolean(3, projekat.isDeleted());
        ps.setBoolean(4, projekat.isActive());

        executeSQL(ps);

        if (projekat.getId() == 0)
        {
            ResultSet rs = ps.getGeneratedKeys();
            if (!rs.next())
            {
                return false;
            }
            projekat.setId(rs.getInt(1));
        }

        ProjectItemsBroker itemsDB = new ProjectItemsBroker();

        itemsDB.delete(projekat.getId());

        for (ProjectItem item : projekat.getItems())
        {
            item.setProjectID(projekat.getId());
            itemsDB.save(item);
        }

        return true;
    }

    public List<ProjectSimple> getList(boolean obrisan, int employeeID, int taskID, boolean aktivan) throws SQLException
    {
        String employeeClause = "";
        String activeClause = "";
        String taskClause = "";

        if (aktivan)
        {
            activeClause = "AND (p.aktivan=?)";
        }

        if (employeeID != 0)
        {
            employeeClause = "AND (p.vodjaID=? OR ps.zaposleniID=? ) ";
        }

        if (taskID != 0)
        {
            if (employeeID == 0)
            {
                taskClause = "AND (ps.zadatakID is NULL OR ps.zadatakID=?) ";
            } else
            {
                taskClause = "AND (ps.zadatakID=? OR ps.zadatakID is NULL)AND (ps.zaposleniID=?) ";
            }
        }

        ps = GetPreparedStatement("SELECT DISTINCTROW p.id, p.naziv,p.aktivan, CONCAT(CONCAT(z.ime,' '),z.prezime) AS vodjaProjekta FROM projekti p "
                + "LEFT JOIN zaposleni z ON p.vodjaID=z.id "
                + "LEFT JOIN projekatstavke ps ON p.id=ps.projekatID "
                + "WHERE (ps.obrisan is NULL OR ps.obrisan=FALSE OR ps.id is NULL) AND (p.obrisan=?) " + activeClause + employeeClause + taskClause
                + "ORDER BY p.naziv");

        ps.setBoolean(1, obrisan);

        if (aktivan)
        {
            ps.setBoolean(2, true);
        }

        int employeeIndex = 0;

        if (employeeID != 0)
        {
            employeeIndex = aktivan ? 3 : 2;
            ps.setInt(employeeIndex, employeeID);
            ps.setInt(employeeIndex + 1, employeeID);
        }
        if (taskID != 0)
        {
            int paramIndex = employeeIndex + 2;
            ps.setInt(paramIndex, taskID);
            if (employeeID != 0)
            {
                ps.setInt(paramIndex + 1, employeeID);
            }
        }

      /*SELECT DISTINCTROW p.id, p.naziv,p.aktivan, CONCAT(CONCAT(z.ime,' '),z.prezime) AS vodjaProjekta FROM projekti p 
        LEFT JOIN zaposleni z ON p.vodjaID=z.id 
        LEFT JOIN projekatstavke ps ON p.id=ps.projekatID 
        WHERE (ps.obrisan IS NULL OR ps.obrisan=FALSE OR ps.id IS NULL) 
        AND (p.obrisan=0) 
        AND (p.aktivan=1)
        AND (p.vodjaID=1 OR ps.zaposleniID=1) 
        AND (ps.zadatakID=1 OR ps.zadatakID IS NULL)
        ORDER BY p.naziv*/
        
        ResultSet rs = selectSQL(ps);

        List<ProjectSimple> projekti = new LinkedList<>();

        while (rs.next())
        {
            projekti.add(getRecoredProjectSimple(rs));
        }

        return projekti;
    }

    private Project getRecord(ResultSet rs) throws SQLException
    {
        Project projekat = new Project();

        projekat.setId(rs.getInt("id"));
        projekat.setName(rs.getString("naziv"));
        projekat.setDeleted(rs.getBoolean("obrisan"));
        projekat.setLeaderID(rs.getInt("vodjaID"));
        projekat.setActive(rs.getBoolean("aktivan"));

        return projekat;

    }

    @Override
    public void delete(int id) throws SQLException
    {
        ps = GetPreparedStatement("UPDATE projekti SET obrisan=true WHERE id=?");

        ps.setInt(1, id);

        executeSQL(ps);
    }

    @Override
    public void undelete(int id) throws SQLException
    {
        ps = GetPreparedStatement("UPDATE projekti SET obrisan=false WHERE id=?");

        ps.setInt(1, id);

        executeSQL(ps);
    }

    boolean isEmployeeEngaged(int id) throws Exception
    {
        ps = GetPreparedStatement("SELECT DISTINCT p.naziv FROM projekti p INNER JOIN projekatstavke ps "
                + "ON p.id=ps.projekatID WHERE p.obrisan=false AND p.vodjaID=? OR ps.zaposleniID=? ORDER BY p.naziv");

        ps.setInt(1, id);
        ps.setInt(2, id);

        ResultSet rs = selectSQL(ps);
        String errors = "";

        while (rs.next())
        {
            errors += "\n " + rs.getString("naziv");
        }

        if (errors.isEmpty())
        {
            return false;
        }

        throw new Exception(errors);

    }

    boolean isTaskEngaged(int id) throws Exception
    {
        ps = GetPreparedStatement("SELECT p.naziv FROM projekti p INNER JOIN projekatstavke ps "
                + "ON p.id=ps.projekatID WHERE p.obrisan=false AND ps.zadatakID=? ORDER BY p.naziv");

        ps.setInt(1, id);

        ResultSet rs = selectSQL(ps);

        String errors = "";

        while (rs.next())
        {
            errors += "\n " + rs.getString("naziv");
        }

        if (errors.isEmpty())
        {
            return false;
        }

        throw new Exception(errors);

    }

    private ProjectSimple getRecoredProjectSimple(ResultSet rs) throws SQLException
    {
        ProjectSimple p = new ProjectSimple();
        p.setLeaderName(rs.getString("vodjaProjekta"));
        p.setName(rs.getString("naziv"));
        p.setId(rs.getInt("id"));
        p.setActive(rs.getBoolean("aktivan"));
        return p;

    }

}
