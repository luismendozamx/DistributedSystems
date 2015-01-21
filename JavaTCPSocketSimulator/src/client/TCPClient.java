package client;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {
    
    private String ip;
    private int port;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    
    public TCPClient(String ip, int port){
        this.ip = ip;
        this.port = port;
        initTCPClient();
    }
    
    private void initTCPClient(){
        try {
            this.socket = new Socket(this.ip, this.port);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readDataFromServer(){
        try {
            this.input = new DataInputStream(this.socket.getInputStream());
            String message = this.input.readUTF();
            System.out.println("Received: "+ message);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void readIntFromServer(){
        try {
            this.input = new DataInputStream(this.socket.getInputStream());
            int message = this.input.readInt();
            System.out.println("Received: "+ message);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeDataToServer(String data){
        try {
            this.output = new DataOutputStream(this.socket.getOutputStream());
            this.output.writeUTF(data);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeIntToServer(int data){
        try {
            this.output = new DataOutputStream(this.socket.getOutputStream());
            this.output.writeInt(data);
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void closeSocket(){
        try {
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
