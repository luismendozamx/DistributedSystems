/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author luismendoza
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {
    // Calculate the square of a number
    public double square ( int number, Credential c ) throws RemoteException;
    // Calculate the power of a number
    public double power ( int num1, int num2, Credential c) throws RemoteException;
}
