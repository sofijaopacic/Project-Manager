/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import controller.EmployeeController;
import controller.ProjectController;
import controller.TaskController;
import db.LogBroker;
import domen.Constants;
import domen.Employee;
import domen.Log;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author Sofija
 */
public class ClientThread extends Thread
{
    
    private Socket socket;
    private EmployeeController employeeController;
    private TaskController taskController;
    private ProjectController projectController;
    private LogBroker logBroker;
    private boolean running;
    private Employee logged;
    
    public ClientThread(Socket socket)
    {
        this.socket = socket;
        employeeController = new EmployeeController();
        taskController = new TaskController();
        projectController = new ProjectController();
        running = true;
        logged=null;
        logBroker= new LogBroker();
    }
    
    public boolean isRunning()
    {
        return running;
    }
    
    public void setRunning(boolean running)
    {
        this.running = running;
        try
        {
            socket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run()
    {
        
        while (running)
        {
            ClientRequest request = getRequest();
            
            if (request == null)
            {
                return;
            }
            switch (request.getOperation())
            {
                case Constants.OPERATION_LOGIN:
                    logged=(Employee) employeeController.OnLogin(request).getResponse();
                    sendResponse(employeeController.OnLogin(request));
                    break;
                case Constants.GET_EMPLOYEES:
                    sendResponse(employeeController.OnGetEmployees(request));
                    break;
                case Constants.SAVE_EMPLOYEE:
                    sendResponse(employeeController.OnSaveEmployee(request));
                    break;
                case Constants.DELETE_EMPLOYEE:
                    sendResponse(employeeController.OnDeleteEmployee(request));
                    break;
                case Constants.UNDELETE_EMPLOYEE:
                    sendResponse(employeeController.OnUndeleteEmployee(request));
                    break;
                case Constants.READ_TASK:
                    sendResponse(taskController.OnReadTask(request));
                    break;
                case Constants.SAVE_TASK:
                    sendResponse(taskController.OnSaveTask(request));
                    break;
                case Constants.GET_TASK_LIST:
                    sendResponse(taskController.OnGetTaskList(request));
                    break;
                case Constants.DELETE_TASK:
                    sendResponse(taskController.OnDeleteTask(request));
                    break;
                case Constants.UNDELETE_TASK:
                    sendResponse(taskController.OnUndeleteTask(request));
                    break;
                case Constants.SAVE_PROJECT:
                    sendResponse(projectController.OnSaveProject(request));
                    break;
                case Constants.GET_PROJECT_LIST:
                    sendResponse(projectController.OnGetProjectList(request));
                    break;
                case Constants.UNDELETE_PROJECT:
                    sendResponse(projectController.OnUndeleteProject(request));
                    break;
                case Constants.DELETE_PROJECT:
                    sendResponse(projectController.OnDeleteProject(request));
                    break;
                case Constants.READ_PROJECT:
                    sendResponse(projectController.OnReadProject(request));
                    break;
                default:
                    sendResponse(new ServerResponse(null, "Nepoznata komanda", false));
            }
            
            if(logged!=null){
                Log log= new Log();
                log.setEmployee(logged);
                log.setCommandID(request.getOperation());
                try
                {
                    logBroker.save(log);
                } catch (Exception ex)
                {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
    private ClientRequest getRequest()
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (ClientRequest) ois.readObject();
        } catch (IOException | ClassNotFoundException | NullPointerException ex)
        {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void sendResponse(ServerResponse response)
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(response);
            oos.flush();
        } catch (IOException ex)
        {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
