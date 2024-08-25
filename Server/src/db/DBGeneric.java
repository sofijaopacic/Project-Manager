/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.AbstractDomain;
import java.sql.PreparedStatement;

/**
 *
 * @author Sofija
 */
public class DBGeneric implements DBBase<AbstractDomain>
{

    @Override
    public AbstractDomain read(int id) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(AbstractDomain param) throws Exception
    {
        PreparedStatement ps;
        if (param.getId() == 0)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(param.getTableName())
                    .append(" (").append(param.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(param.getInsertValues())
                    .append(")");

            ps = GetPreparedStatement(sb.toString());

        } else
        {
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(param.getTableName())
                    .append(" SET ").append(param.getUpdateValues());
            ps = GetPreparedStatement(sb.toString());

        }
        executeSQL(ps);

        return true;
    }

    @Override
    public void delete(int id) throws Exception
    {
        
    }

    @Override
    public void undelete(int id) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
