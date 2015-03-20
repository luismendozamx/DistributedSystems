/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import server.Person;

/**
 *
 * @author luismendoza
 */
public class ClientThread extends Thread {
    
    private TCPClient client;
    private int n;
    private int id;
    
    public ClientThread(){
        this.client = new TCPClient("localhost", 7896);
        this.n = 100;
    }
    
    public ClientThread(int n, int id){
        this.client = new TCPClient("localhost", 7896);
        this.n = n;
        this.id = id;
    }
    
    public int generateRandom(){
        return (int)(Math.random()*9);
    }
    
    @Override
    public void run(){
        //long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            client.writeDataToServer(this.id + "-" + generateRandom());
        }
        //long totalTime = System.currentTimeMillis() - startTime;
        //System.out.println("Timepo transcurrido: " + totalTime);
        //client.writeIntToServer(5);
        client.closeSocket();
    }
}
