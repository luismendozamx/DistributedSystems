/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.ComputeClient;
import interfaces.Compute;
import interfaces.Credential;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luismendoza
 */
public class ComputeServer implements Compute {
    
    public static void main(String[] args){
        System.setProperty("java.security.policy","/Users/luismendoza/Sandbox/ITAM/DistributedSystems/JavaRMI/src/server/server.policy");
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        try {
            LocateRegistry.createRegistry(1099);
            
            String name = "Compute";
            ComputeServer engine = new ComputeServer();
            Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);
            
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            
            
        } catch (RemoteException ex) {
            Logger.getLogger(ComputeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ComputeServer() throws RemoteException {
        super();
    }
    
    @Override
    public double square(int number, Credential credential) throws RemoteException {
        if(credential.getId() == 1)
            return (number*number);
        else
            return -1;
    }
    
    @Override
    public double power(int num1, int num2, Credential credential) throws RemoteException {
        if(credential.getId() == 1)
            return (java.lang.Math.pow(num1, num2));
        else
            return -1;
    }
    
}
