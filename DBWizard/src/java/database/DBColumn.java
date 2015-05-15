/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author luismendoza
 */
public class DBColumn {
    public String name;
    public String type;
    
    public DBColumn(String name, String type){
        this.name = name;
        this.type = type;
    }   
}
