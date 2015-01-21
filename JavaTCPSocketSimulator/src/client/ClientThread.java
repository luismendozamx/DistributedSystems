/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author luismendoza
 */
public class ClientThread extends Thread {
    
    private TCPClient client;
    
    public ClientThread(){
        this.client = new TCPClient("localhost", 7896);
    }
    
    @Override
    public void run(){
        int random = (int)(Math.random() * 4);
        client.writeIntToServer(random);
        client.readDataFromServer();
        client.closeSocket();
    }
}
