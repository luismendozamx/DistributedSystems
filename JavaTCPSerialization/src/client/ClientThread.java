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
    
    public ClientThread(){
        this.client = new TCPClient("localhost", 7896);
        this.n = 100;
    }
    
    public ClientThread(int n){
        this.client = new TCPClient("localhost", 7896);
        this.n = n;
    }
    
    @Override
    public void run(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Person persona = new Person(2, "Luis", "Mendoza");
            client.writeObjectToServer(persona);
            Person respuesta = client.readObjectFromServer();
        }
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Timepo transcurrido: " + totalTime);
        client.writeIntToServer(5);
        client.closeSocket();
    }
}
