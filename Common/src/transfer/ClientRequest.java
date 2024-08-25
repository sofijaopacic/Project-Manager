/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Sofija
 */
public class ClientRequest implements Serializable
{
    
    private int operation;
    private Object parameter;

    public ClientRequest()
    {
    }

    public ClientRequest(int operation, Object parameter)
    {
        this.operation = operation;
        this.parameter = parameter;
    }

    public int getOperation()
    {
        return operation;
    }

    public void setOperation(int operation)
    {
        this.operation = operation;
    }

    public Object getParameter()
    {
        return parameter;
    }

    public void setParameter(Object parameter)
    {
        this.parameter = parameter;
    }
    
    
    
    
    
}
