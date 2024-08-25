/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Constants;
import domen.Employee;
import java.util.List;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author Sofija
 */
public class EmployeeController
{

    ClientRequest request = new ClientRequest();

    public List<Employee> getList(boolean deleted) throws Exception
    {
        request.setOperation(Constants.GET_EMPLOYEES);
        request.setParameter(deleted);
        Communication.getInstance().send(request);
        ServerResponse response = Communication.getInstance().receive();
        if (!response.isSuccess())
        {
            throw new Exception(response.getMessage());
        }
        return (List<Employee>) response.getResponse();
    }

    public boolean save(Employee employee) throws Exception
    {
        if (employee.getName().isEmpty() || employee.getSurname().isEmpty() || employee.getUsername().isEmpty())
        {
            throw new Exception("Greška, morate uneti ime, prezime i korisničko ime!");
        } else
        {
            request.setOperation(Constants.SAVE_EMPLOYEE);
            request.setParameter(employee);

            Communication.getInstance().send(request);
            ServerResponse response = Communication.getInstance().receive();
            if (!response.isSuccess())
            {
                throw new Exception(response.getMessage());
            }
        }
        return true;
    }

    public void delete(int id) throws Exception
    {
        request.setOperation(Constants.DELETE_EMPLOYEE);
        request.setParameter(id);

        Communication.getInstance().send(request);
        ServerResponse response = Communication.getInstance().receive();
        if (!response.isSuccess())
        {
            throw new Exception(response.getMessage());
        }
    }

    public boolean undelete(int id)
    {
        try
        {
            request.setOperation(Constants.UNDELETE_EMPLOYEE);
            request.setParameter(id);

            Communication.getInstance().send(request);
            ServerResponse response = Communication.getInstance().receive();

            return response.isSuccess();
        } catch (Exception ex)
        {
           
        }
         return false;
    }

    public Employee find(String[] data) throws Exception
    {
        request.setOperation(Constants.OPERATION_LOGIN);
        request.setParameter(data);
        Communication.getInstance().send(request);
        ServerResponse response = Communication.getInstance().receive();
        if (!response.isSuccess())
        {
            throw new Exception(response.getMessage());
        }
        return (Employee) response.getResponse();
    }

}
