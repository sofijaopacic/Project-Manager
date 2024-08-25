/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sofija
 */
public interface DBBase<T> extends DBAbstract<T>{
    
//    public DBBroker GetInstance()
  //  {
     //   return DBBroker.getInstanca();
   // }
    
    default public PreparedStatement GetPreparedStatement(String upit) throws SQLException
    {
        DBBroker db=DBBroker.getInstanca();
        Connection k=DBBroker.getInstanca().konekcija;
        return DBBroker.getInstanca().konekcija.prepareStatement(upit);
    }
    
    
   default public PreparedStatement GetPreparedStatement2(String upit) throws SQLException
    {
        return DBBroker.getInstanca().konekcija.prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
    }

   default public ResultSet selectSQL(PreparedStatement ps) throws SQLException
    {
        return DBBroker.getInstanca().selectSQL(ps);
    }
    
   default public void executeSQL(PreparedStatement ps) throws SQLException
    {
        DBBroker.getInstanca().executeSQL(ps);
    }
}
