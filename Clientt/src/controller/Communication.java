/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

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
public class Communication
{

    private static Communication instance;
    private Socket socket;

    public static Communication getInstance() throws Exception
    {
        if (instance == null)
        {
            try
            {
                instance = new Communication();
            } catch (IOException ex)
            {
                throw new Exception("Ne mogu se povezati sa serverom!");
            }
        }
        return instance;
    }

    public Communication() throws IOException
    {
        socket = new Socket("localhost", 9000);
    }

    public ServerResponse receive() throws Exception
    {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        return (ServerResponse) ois.readObject();

    }

    public void send(ClientRequest request) throws Exception
    {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(request);
        oos.flush();

    }
}
