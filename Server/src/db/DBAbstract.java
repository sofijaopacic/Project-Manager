/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package db;

/**
 *
 * @author Sofija
 */
public interface DBAbstract<T>
{
   T read(int id) throws Exception;
   boolean save(T param) throws Exception;
   void delete(int id) throws Exception;
   void undelete(int id) throws Exception;
    
}
