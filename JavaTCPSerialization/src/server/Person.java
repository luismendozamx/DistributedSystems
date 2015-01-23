/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.io.Serializable;
/**
 *
 * @author JGUTIERRGARC
 */
public class Person implements Serializable{
    private int id;
    private String name;
    private String lastName;
    
    public Person(){
        id=0;
        name="";
        lastName = "";
    }
    public Person(int id, String name, String lastName){
        this.id= id;
        this.name= name;
        this.lastName = lastName;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = lastName;
    }
    
}
