/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;




class AddressBook {
    private Person [] myAddressBook;
    private final int size = 5;
    
    public AddressBook(){
      myAddressBook = new Person[size];
      fillAddressBook();
    }

    
    private void fillAddressBook(){
        String[] names = {"Juan", "Jose", "Luis", "Agustin", "Jesus"};
        String[] lastNames = {"Perez", "Incera", "Mendoza", "Flores", "Gonzalez"};
        for (int i=0; i<size; i++){
            myAddressBook[i] = new Person(i, names[i%5], lastNames[i%5]);
        }
    }

    public Person getRecord(int key) {
      if (key<size) 
        return myAddressBook[key];
      else {
        return new Person ();
      }
    }
  
  
} 