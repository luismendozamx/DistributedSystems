/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threadwaiting;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luismendoza
 */
public class ThreadWaiting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        long time = 9000;
        long start = System.currentTimeMillis();
        
        PrintingThread print = new PrintingThread("MainThread");
        
        print.start();
        System.out.println("Iniciando...");
        while(print.isAlive()){
            try {
                print.join(10);
                if(System.currentTimeMillis() - start > time && print.isAlive()){
                    print.interrupt();
                    //print.join();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadWaiting.class.getName()).log(Level.SEVERE, null, ex);
            }              
        }
        System.out.println("...Fin");
    }
    
}
