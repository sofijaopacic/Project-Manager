/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sofija
 */
public class DBBroker {
    
    protected static DBBroker instanca;
    protected Connection konekcija;

    public DBBroker() throws SQLException {
        konekcija= DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarski","root","");
        konekcija.setAutoCommit(false);
    }

    public static DBBroker getInstanca() throws SQLException {
        if(instanca==null){
            instanca=new DBBroker();
        }
        return instanca;
    }
    
    public ResultSet selectSQL(PreparedStatement ps) throws SQLException{
        return ps.executeQuery();
    }
    
    public void executeSQL(PreparedStatement ps) throws SQLException{
        ps.execute();
        konekcija.commit();
    }
    
    

    
}
