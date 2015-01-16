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
public class PrintingThread extends Thread {
    
    private String name;
    
    public PrintingThread(String name){
        this.name = name;
    }
    
    public void run(){
        for(int i = 0; i < 4; i++){
            try {
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + " - " + i);
            } catch (InterruptedException ex) {
                //Logger.getLogger(PrintingThread.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(this.name + ": Qué onda, porque me interrumpen.");
            }
        }
    }
    
}
