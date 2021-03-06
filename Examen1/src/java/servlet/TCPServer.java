/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

/**
 *
 * @author luismendoza
 */

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPServer {
    
    public TCPServer(){
        // Constructor vacío
    }
    
    public static void main(String[] args) {
        try{
            int serverPort = 7855; 
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                System.out.println("Waiting for messages..."); 
                Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
                Connection c = new Connection(clientSocket);
                c.start();
            }
        } catch(IOException e) {
            System.out.println("Listen :"+ e.getMessage());
        }
    }
   
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            out =new DataOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
         } catch(IOException e)  {
             System.out.println("Connection:"+e.getMessage());
         }
    }

    @Override
    public void run(){
        try {			                 // an echo server
            String data = in.readUTF();
            
            while(data != null){
                System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
                System.out.println("Message: " + data);
                // Modificar respuesta
                data = data + "'";
                
                out.writeUTF(data);
                data = in.readUTF();
            }
            
        } 
        catch(EOFException e) {
            System.out.println("EOF:"+e.getMessage());
        } 
        catch(IOException e) {
            System.out.println("IO:"+e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
