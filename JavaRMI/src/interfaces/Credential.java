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
public class Credential implements Serializable {
    
    private int id;
    private String name;
    
    public Credential(){
        id = 1;
        name = "Nombre";
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
}
