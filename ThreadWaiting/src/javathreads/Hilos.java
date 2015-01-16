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
public class Hilos {
    
    public static void main(String[] args){
        //
        HelloThread hilo1 = new HelloThread();
        Thread hilo2 = new Thread(new HelloRunnable());
        hilo1.start();
        hilo2.start();
        
        try{
            hilo1.join();
            hilo2.join();
        }catch(InterruptedException ex){
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Por fin terminó.");
    }
    
}
