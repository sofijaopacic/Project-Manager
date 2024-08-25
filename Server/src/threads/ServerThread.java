/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sofija
 */
public class ServerThread extends Thread
{

    ServerSocket serverskiSoket;
    List<ClientThread> clients=new LinkedList<>();
    public ServerThread()
    {
    }

    @Override
    public void run()
    {
        try {
            serverskiSoket = new ServerSocket(9000);
            while (true) {
                Socket s = serverskiSoket.accept();
                System.out.println("Klijent se povezao!");
                ClientThread thread = new ClientThread(s);
                thread.start();
                clients.add(thread);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*public static void main(String[] args)
    {
        ServerThread st= new ServerThread();
        st.run();
    }*/

    public void close()
    {
        try
        {
            for(ClientThread t: clients){
                t.setRunning(false);
            }
            serverskiSoket.close();
            
        } catch (IOException ex)
        {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
