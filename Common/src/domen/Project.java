/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sofija
 */
public class Project extends AbstractDomain{
    
    private int id;
    private String name;
    private int leaderID;
    private List<ProjectItem> items;
    private boolean active;
    private boolean deleted;

    public Project() {
        id=0;
        deleted=false;
        active=true;
        leaderID= 0;
        items= new LinkedList<>();
        
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getLeaderID()
    {
        return leaderID;
    }

    public void setLeaderID(int leaderID)
    {
        this.leaderID = leaderID;
    }

    public List<ProjectItem> getItems()
    {
        return items;
    }

    public void setItems(List<ProjectItem> items)
    {
        this.items = items;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getTableName()
    {
        return "projekti";
    }

    @Override
    public String getColumnNamesForInsert()
    {
        return "id,naziv,obrisan,vodjaID,aktivan";
    }

    @Override
    public String getInsertValues()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("null,")
                .append("'").append(name).append("',")
                .append(deleted).append("',")
                .append(leaderID).append(",")
                .append(active);
        return sb.toString();
    }

    @Override
    public String getUpdateValues()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
}
