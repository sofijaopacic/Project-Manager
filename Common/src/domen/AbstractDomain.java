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
public abstract class AbstractDomain implements Serializable
{
    public abstract String getTableName();
    public abstract String getColumnNamesForInsert();
    public abstract String getInsertValues();
    public abstract String getUpdateValues();
    public abstract int getId();
    public abstract void setId(int id);
   
}
