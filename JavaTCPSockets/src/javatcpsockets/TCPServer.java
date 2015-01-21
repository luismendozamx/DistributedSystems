/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JGUTIERRGARC
 */

import java.net.*;
import java.io.*;


public class TCPServer {
    
    public static void main (String args[]) {
        try{
            int serverPort = 7896; 
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
            in = new DataInputStream(clientSocket.getInputStream());
            out =new DataOutputStream(clientSocket.getOutputStream());
         } catch(IOException e)  {
             System.out.println("Connection:"+e.getMessage());
         }
    }

    @Override
    public void run(){
        try {			                 // an echo server
            String data = in.readUTF();	 
            int data2 = in.readInt();
            System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
            System.out.println("Message: " + data);
            out.writeUTF(data);
            System.out.println("Message: " + data2);
            out.writeInt(data2);
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


