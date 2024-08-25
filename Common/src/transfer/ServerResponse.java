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
public class ServerResponse implements Serializable
{
    
    private Object response;
    private String message;
    private boolean success;
    private int errorCode;

    public ServerResponse()
    {
        success=true;
        errorCode=0;
    }
    
     public ServerResponse(Object response, String message, boolean success)
    {
        this.response = response;
        this.message = message;
        this.success = success;
    }

    public ServerResponse(Object response, String message, boolean success, int errorCode)
    {
        this.response = response;
        this.message = message;
        this.success = success;
        this.errorCode=errorCode;
    }

    public Object getResponse()
    {
        return response;
    }

    public void setResponse(Object response)
    {
        this.response = response;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }
    
    
    
    
}
