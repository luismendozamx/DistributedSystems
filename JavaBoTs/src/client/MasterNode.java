/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.Task;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luismendoza
 */
public class MasterNode {
    
    public static void main(String[] args){
        
        Task[] tareasImagenes = {
            new Task("T1", "Imagenes", 5000),
            new Task("T2", "Imagenes", 10000),
            new Task("T3", "Imagenes", 15000),
            new Task("T4", "Imagenes", 20000),
            new Task("T5", "Imagenes", 30000),
            new Task("T6", "Imagenes", 5000),
            new Task("T7", "Imagenes", 10000),
            new Task("T8", "Imagenes", 15000),
            new Task("T9", "Imagenes", 20000),
            new Task("T10", "Imagenes", 30000)
        };
        
        Task[] tareasMineria = {
            new Task("T11", "Mineria", 5000),
            new Task("T12", "Mineria", 10000),
            new Task("T13", "Mineria", 15000),
            new Task("T14", "Mineria", 20000),
            new Task("T15", "Mineria", 30000),
            new Task("T16", "Mineria", 5000),
            new Task("T17", "Mineria", 10000),
            new Task("T18", "Mineria", 15000),
            new Task("T19", "Mineria", 20000),
            new Task("T20", "Mineria", 30000),
            new Task("T21", "Mineria", 5000),
            new Task("T22", "Mineria", 10000),
            new Task("T23", "Mineria", 15000),
            new Task("T24", "Mineria", 20000),
            new Task("T25", "Mineria", 30000),
            new Task("T26", "Mineria", 5000),
            new Task("T27", "Mineria", 10000),
            new Task("T28", "Mineria", 15000),
            new Task("T29", "Mineria", 20000),
            new Task("T30", "Mineria", 30000),
        };
        
        Task[] tareasBioinformatica = {
            new Task("T31", "Bioinformatica", 5000),
            new Task("T32", "Bioinformatica", 10000),
            new Task("T33", "Bioinformatica", 15000),
            new Task("T34", "Bioinformatica", 20000),
            new Task("T35", "Bioinformatica", 30000),
            new Task("T36", "Bioinformatica", 5000),
            new Task("T37", "Bioinformatica", 10000),
            new Task("T38", "Bioinformatica", 15000),
            new Task("T39", "Bioinformatica", 20000),
            new Task("T40", "Bioinformatica", 30000),
            new Task("T41", "Bioinformatica", 5000),
            new Task("T42", "Bioinformatica", 10000),
            new Task("T43", "Bioinformatica", 15000),
            new Task("T44", "Bioinformatica", 20000),
            new Task("T45", "Bioinformatica", 30000),
        };
        
        // Crear threads con arreglos de tareas
        ComputeThread imagenesThread = new ComputeThread(tareasImagenes, "Imagenes");
        ComputeThread mineriaThread = new ComputeThread(tareasMineria, "Mineria");
        ComputeThread bioThread = new ComputeThread(tareasBioinformatica, "Bioinformatica");
        
        // Now time
        long now = System.currentTimeMillis();
        System.out.println("Current time in millis: " + now);
        
        // Start them
        imagenesThread.start();
        mineriaThread.start();
        bioThread.start();
        
        long finish = System.currentTimeMillis();
        System.out.println("Finish time in millis: " + finish);
        
        long totalTime = finish - now;
        System.out.println("Current time in millis: " + totalTime);
        
        
    }
    
    
    private static class ComputeThread extends Thread {
        
        private Task[] taskArray;
        private String taskType;
        
        public ComputeThread(Task[] taskArray, String taskType) {
            this.taskArray = taskArray;
            this.taskType = taskType;
        }
        
        @Override
        public void run() {
            // your code here
            for (int i = 0; i < taskArray.length; i++) {
                try {
                    Thread.sleep(taskArray[i].getLength());
                } catch (InterruptedException ex) {
                    Logger.getLogger(MasterNode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        public String getTaskType(){
            return this.taskType;
        }
    } 
}
