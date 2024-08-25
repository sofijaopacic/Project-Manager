/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author Sofija
 */
public class ProjectSimple extends AbstractDomain
{
    int projectID;
    String projectName;
    String leaderName;
    boolean active;

    public int getId()
    {
        return projectID;
    }

    public void setId(int projekatID)
    {
        this.projectID = projekatID;
    }

    public String getName()
    {
        return projectName;
    }

    public void setName(String nazivProjekta)
    {
        this.projectName = nazivProjekta;
    }

    public String getLeaderName()
    {
        return leaderName;
    }

    public void setLeaderName(String vodjaProjekta)
    {
        this.leaderName = vodjaProjekta;
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
    public String getTableName()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getColumnNamesForInsert()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInsertValues()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateValues()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
}
