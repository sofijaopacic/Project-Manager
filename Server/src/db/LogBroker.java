/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Log;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 *
 * @author Sofija
 */
public class LogBroker implements DBBase<Log>
{
    PreparedStatement ps;

    @Override
    public Log read(int id) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(Log param) throws Exception
    {
        ps=GetPreparedStatement("INSERT INTO log VALUES (null,?,?,?)");
        ps.setInt(1, param.getEmployee().getId());
        ps.setInt(2, param.getCommandID());
        ps.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
       
        
        executeSQL(ps);
        
        return true;
    }

    @Override
    public void delete(int id) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void undelete(int id) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
