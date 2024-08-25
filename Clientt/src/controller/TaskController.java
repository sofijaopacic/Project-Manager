/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Constants;
import domen.Task;
import java.util.List;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author Sofija
 */
public class TaskController
{

    ClientRequest request = new ClientRequest();  

    public Task read(int id) throws Exception
    {
        request.setOperation(Constants.READ_TASK);
        request.setParameter(id);
        Communication.getInstance().send(request);
        ServerResponse response = Communication.getInstance().receive();
        Task t = (Task) response.getResponse();
        if (t == null)
        {
            throw new Exception(response.getMessage());
        }
        return t;
    }

    public boolean save(Task task) throws Exception
    {
        if (task.getName().isEmpty() || task.getDescription().isEmpty())
        {
            throw new Exception("Greška, morate uneti naziv i opis!");
        } else
        {
            request.setOperation(Constants.SAVE_TASK);
            request.setParameter(task);
            Communication.getInstance().send(request);
            ServerResponse response = Communication.getInstance().receive();
            return response.isSuccess();
        }
    }

    public List<Task> getList(boolean deleted) throws Exception
    {
        request.setOperation(Constants.GET_TASK_LIST);
        request.setParameter(deleted);
        Communication.getInstance().send(request);
        ServerResponse response = Communication.getInstance().receive();
        if(!response.isSuccess()){
            throw new Exception(response.getMessage());
        }
        return (List<Task>) response.getResponse();
    }

    public void delete(int id) throws Exception
    {
        request.setOperation(Constants.DELETE_TASK);
        request.setParameter(id);
        Communication.getInstance().send(request);
        ServerResponse response = Communication.getInstance().receive();
        if(!response.isSuccess()){
            throw new Exception(response.getMessage());
        }
    }

    public boolean undelete(int id)
    {
        try
        {
            request.setOperation(Constants.UNDELETE_TASK);
            request.setParameter(id);
            Communication.getInstance().send(request);
            ServerResponse response = Communication.getInstance().receive();
            return response.isSuccess();
        } catch (Exception ex)
        {
        }
        return false;
    }
}
