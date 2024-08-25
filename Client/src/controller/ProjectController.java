/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Constants;
import domen.Project;
import domen.ProjectSimple;
import java.util.LinkedList;
import java.util.List;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author Sofija
 */
public class ProjectController
{

    ClientRequest request = new ClientRequest();

    public boolean save(Project project) throws Exception
    {
        String errors = "";

        if (project.getName().isEmpty())
        {
            errors += "\nMorate uneti naziv projekta.";
        }
        if (project.getLeaderID() == 0)
        {
            errors += "\nMorate uneti vodju projekta.";
        }

        if (!errors.isEmpty())
        {
            throw new Exception(errors);
        }

        request.setOperation(Constants.SAVE_PROJECT);
        request.setParameter(project);
        Communication.getInstance().send(request);
        ServerResponse response = Communication.getInstance().receive();

        return response.isSuccess();

    }

    public List<ProjectSimple> getList(boolean deleted, int employeeID, int taskID, boolean active) throws Exception
    {
        request.setOperation(Constants.GET_PROJECT_LIST);
        List<Object> params = new LinkedList<>();
        params.add(deleted);
        params.add(employeeID);
        params.add(taskID);
        params.add(active);
        request.setParameter(params);
        Communication.getInstance().send(request);
        ServerResponse response = Communication.getInstance().receive();
        if (response.isSuccess())
        {
            return (List<ProjectSimple>) response.getResponse();
        }

        throw new Exception("Došlo je do greške!");
    }

    public boolean undelete(int id)
    {
        try
        {
            request.setOperation(Constants.UNDELETE_PROJECT);
            request.setParameter(id);
            Communication.getInstance().send(request);
            ServerResponse response = Communication.getInstance().receive();
            return response.isSuccess();
        } catch (Exception ex)
        {
        }
        return false;
    }

    public void delete(int id) throws Exception
    {
        request.setOperation(Constants.DELETE_PROJECT);
        request.setParameter(id);
        Communication.getInstance().send(request);
        ServerResponse response = Communication.getInstance().receive();
    }

    public Project read(int projectID) throws Exception
    {
        request.setOperation(Constants.READ_PROJECT);
        request.setParameter(projectID);
        Communication.getInstance().send(request);
        ServerResponse response = Communication.getInstance().receive();

        if (response.isSuccess())
        {
            return (Project) response.getResponse();
        }

        throw new Exception("Došlo je do greške!");
    }

}
