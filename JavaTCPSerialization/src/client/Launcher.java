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
public class Launcher {
    
    public static void main(String args[]){
        
        for (int i = 0; i < 100; i++) {
            ClientThread clientThread = new ClientThread(100);
            clientThread.start();
        }
        
    }
    
}
