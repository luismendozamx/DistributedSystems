package server;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPServer {
    
    public static void main (String args[]) {
        try{
            int serverPort = 7896; 
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true) {
                //System.out.println("Waiting for messages..."); 
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
    ObjectOutputStream out;
    Socket clientSocket;
    Integer[] lista = new Integer[1000];
    ArrayList<Long> responseTime = new ArrayList<Long>();
    
    public Connection (Socket aClientSocket) {
        for (int i = 0; i < lista.length; i++) {
            lista[i] = 0;
        }
                
        try {
            clientSocket = aClientSocket;
            out =new ObjectOutputStream(clientSocket.getOutputStream());
            in = new DataInputStream(clientSocket.getInputStream());
         } catch(IOException e)  {
             //System.out.println("Connection:"+e.getMessage());
         }
    }
    
    public void parseDataToArray(String data){
        String id = "";
        String res = "";
        boolean flag = true;
        
        for(int i=0; i < data.length(); i++){
            if( data.charAt(i) != '-' && flag){
                id = id + data.charAt(i);
            }else if(data.charAt(i) != '-'){
                res = res + data.charAt(i);
            }else{
                flag = false;
            }
        }
        
        int rId = Integer.parseInt(id);
        int rRes = Integer.parseInt(res);
        
        if( rRes == 5)
            lista[rId] = lista[rId] + 1;
    }
    
    public boolean winner(int n){
        boolean res = false;
        int i = 0;
        
        while (!res && i < lista.length) {
            if(lista[i] == n){
                res = true;
                //System.out.println("Winner: "+i);
            }
            i++;
        }
        
        return res;
    }

    @Override
    public void run(){
        try {			                 // an echo server
            long startTime, totalTime, readTime, currentTime;
            startTime = System.currentTimeMillis();
            String data = in.readUTF();	 
            //Person data = (Person) in.readObject();
            
            while(data != null && !winner(200)){
                //System.out.println("Message received from: " + clientSocket.getRemoteSocketAddress());
                //System.out.println("Message: " + data);               
                //out.writeObject(data);
                currentTime = System.currentTimeMillis();
                data = (String) in.readUTF();
                parseDataToArray(data);
                readTime = System.currentTimeMillis() - currentTime;
                responseTime.add(readTime);
            }
            totalTime = System.currentTimeMillis() - startTime;
            clientSocket.close();
            System.out.println("" + totalTime);
        } 
        catch(EOFException e) {
            //System.out.println("EOF:"+e.getMessage());
        } 
        catch(IOException e) {
            //System.out.println("IO:"+e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e){
                //System.out.println(e);
            }
        }
    }
}


