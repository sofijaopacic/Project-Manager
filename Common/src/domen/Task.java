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
public class Task extends AbstractDomain{
    
   private int id;
   private String name;
   private String description;
   private boolean deleted;

    public Task() {
        id=0;
        deleted=false;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean isDeleted()
    {
        return deleted;
    }

    public void setDeleted(boolean deleted)
    {
        this.deleted = deleted;
    }
 

    @Override
    public String toString() {
        return  name == null ? "<nije izabran>" : name;
    }

    @Override
    public String getTableName()
    {
        return "zadaci";
    }

    @Override
    public String getColumnNamesForInsert()
    {
        return "id,naziv,opis,obrisan";
    }

    @Override
    public String getInsertValues()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("null,")
                .append("'").append(name).append("',")
                .append("'").append(description).append("',")
                .append(deleted);
        return sb.toString();
    }

    @Override
    public String getUpdateValues()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
   
    
}
