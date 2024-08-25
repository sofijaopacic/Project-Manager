/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.ProjectBroker;
import domen.Project;
import java.sql.SQLException;
import java.util.List;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author Sofija
 */
public class ProjectController
{
    
    ProjectBroker db = new ProjectBroker();
    ServerResponse response = new ServerResponse();
    
    public ServerResponse OnSaveProject(ClientRequest request)
    {
        try
        {
            response.setResponse(db.save((Project) request.getParameter()));
            response.setSuccess(true);
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        
        return response;
    }
    
    public ServerResponse OnGetProjectList(ClientRequest request)
    {
        List<Object> params = (List<Object>) request.getParameter();
        
        try
        {
            boolean deleted = (boolean) params.get(0);
            int employeeID = (int) params.get(1);
            int taskID = (int) params.get(2);
            boolean active = (boolean) params.get(3);
            
            response.setResponse(db.getList(deleted, employeeID, taskID, active));
            response.setSuccess(true);
            
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        
        return response;
    }
    
    public ServerResponse OnUndeleteProject(ClientRequest request)
    {
        try
        {
            db.undelete((int) request.getParameter());
            response.setSuccess(true);
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        
        return response;
    }
    
    public ServerResponse OnDeleteProject(ClientRequest request)
    {
        try
        {
            db.delete((int) request.getParameter());
            response.setSuccess(true);
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        
        return response;
    }
    
    public ServerResponse OnReadProject(ClientRequest request)
    {
        try
        {
            response.setResponse(db.read((int) request.getParameter()));
            response.setSuccess(true);
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
        }
        
        return response;
        
    }
    
}
