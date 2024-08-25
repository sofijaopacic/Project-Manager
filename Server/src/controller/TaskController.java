/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.TaskBroker;
import domen.Task;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author Sofija
 */
public class TaskController
{
    TaskBroker db= new TaskBroker();
    ServerResponse response = new ServerResponse();

    public ServerResponse OnReadTask(ClientRequest request)
    {
        try
        {
            response.setResponse(db.read((int) request.getParameter()));
            response.setSuccess(true);
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }

    public ServerResponse OnSaveTask(ClientRequest request)
    {
        try
        {
            response.setResponse(db.save((Task) request.getParameter()));
            response.setSuccess(true);
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
        
    }

    public ServerResponse OnGetTaskList(ClientRequest request)
    {
        try
        {
            response.setResponse(db.getList((boolean) request.getParameter()));
            response.setSuccess(true);
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
        
    }

    public ServerResponse OnDeleteTask(ClientRequest request)
    {
         try
        {
            db.delete((int) request.getParameter());
            response.setSuccess(true);
        } catch (Exception ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
        
    }

    public ServerResponse OnUndeleteTask(ClientRequest request)
    {
         try
        {
            db.undelete((int) request.getParameter());
            response.setSuccess(true);
        } catch (Exception ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }
    
    
    
}
