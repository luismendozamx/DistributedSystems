/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javathreads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luismendoza
 */
public class HelloRunnable implements Runnable {
    
    public void run(){
        for(int i = 0; i< 100; i++){
            System.out.println("Hola Runnable " + i + " " + Thread.currentThread().getName());
            
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(HelloRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            
    }
    
}
