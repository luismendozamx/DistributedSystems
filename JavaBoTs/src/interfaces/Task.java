/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.Serializable;

/**
 *
 * @author luismendoza
 */
public class Task implements Serializable {
    
    private String taskId;
    private String requirementId;
    private long length;
    private String output;
    
    // Constructor vacío
    public Task(){
        this.taskId = "0";
        this.requirementId = "0";
        this.length = 0;
        this.output = "";
    }
    
    // Constructor con parámetros
    public Task(String taskId, String requirementId, long length){
        this.taskId = taskId;
        this.requirementId = requirementId;
        this.length = length;
        this.output = "";
    }
    
    public String getTaskId(){
        return this.taskId;
    }
    
    public String getRequirementId(){
        return this.requirementId;
    }
    
    public long getLength(){
        return this.length;
    }
    
    public String getOutput(){
        return this.output;
    }
    
}
