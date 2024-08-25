/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.EmployeeBroker;
import domen.Employee;
import java.sql.SQLException;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author Sofija
 */
public class EmployeeController
{

    EmployeeBroker db = new EmployeeBroker();
    ServerResponse response = new ServerResponse();

    public ServerResponse OnLogin(ClientRequest request)
    {
        try
        {
            response.setResponse(db.find((String[]) request.getParameter()));
            response.setSuccess(true);
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());

        }

        return response;

    }

    public ServerResponse OnGetEmployees(ClientRequest request)
    {
        try
        {
            response.setResponse(db.getList((boolean) request.getParameter()));
            response.setSuccess(true);
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());

        }

        return response;
    }

    public ServerResponse OnSaveEmployee(ClientRequest request)
    {
        try
        {
            response.setResponse(db.save((Employee) request.getParameter()));
            response.setSuccess(true);
        } catch (SQLException ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            response.setErrorCode(ex.getErrorCode());
        }
        return response;
    }

    public ServerResponse OnDeleteEmployee(ClientRequest request)
    {
        try
        {
            db.delete((int) request.getParameter());
            response.setSuccess(true);
        } catch (Exception ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());

        }

        return response;
    }

    public ServerResponse OnUndeleteEmployee(ClientRequest request)
    {
        try
        {
            db.undelete((int) request.getParameter());
            response.setSuccess(true);
        } catch (Exception ex)
        {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());

        }

        return response;
    }
}
