/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoalpha;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 *
 * @author GALESSIA
 */
public class Jugador extends javax.swing.JFrame {

    /**
     * Creates new form Jugador
     */
    public Jugador() {
        initComponents();
    }
    
    public Jugador(String aux){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jCheckBox1.setText("Casilla 1");

        jCheckBox2.setText("Casilla 2");

        jCheckBox3.setText("Casilla 3");

        jCheckBox4.setText("Casilla 4");

        jCheckBox5.setText("Casilla 5");

        jCheckBox6.setText("Casilla 6");

        jCheckBox7.setText("Casilla 7");

        jCheckBox8.setText("Casilla 8");

        jCheckBox9.setText("Casilla 9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox7)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox8)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox9))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox4)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox5)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox2)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox3)))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox2))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox9))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Conexion con= new Conexion();
        con.start();
    }//GEN-LAST:event_formWindowOpened

    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Jugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jugador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jugador().setVisible(true);
            }
        });
    }
    
    private class Conexion extends Thread{

        public Conexion() {
        }
       
        public void rellenaCasillas(String aux){
            if (aux.equals("1")){
                jCheckBox1.setSelected(true);
            }
            if (aux.equals("2")){
                jCheckBox2.setSelected(true);
            }
            if (aux.equals("3")){
                jCheckBox3.setSelected(true);
            }
            if (aux.equals("4")){
                jCheckBox4.setSelected(true);
            }
            if (aux.equals("5")){
                jCheckBox5.setSelected(true);
            }
            if (aux.equals("6")){
                jCheckBox6.setSelected(true);
            }
            if (aux.equals("7")){
                jCheckBox7.setSelected(true);
            }
            if (aux.equals("8")){
                jCheckBox8.setSelected(true);
            }
            if (aux.equals("9")){
                jCheckBox9.setSelected(true);
            }
        }
        
        public void deseleccionaCasillas (){
            jCheckBox1.setSelected(false);
            jCheckBox2.setSelected(false);
            jCheckBox3.setSelected(false);
            jCheckBox4.setSelected(false);
            jCheckBox5.setSelected(false);
            jCheckBox6.setSelected(false);
            jCheckBox7.setSelected(false);
            jCheckBox8.setSelected(false);
            jCheckBox9.setSelected(false);
        }
        
        public void run(){
            Thread hilo = new Thread(new Conexion());
            boolean ciclo = true; 
            String num;
            MulticastSocket s =null;
            while (ciclo == true){
                try {
                    InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group 
                    s = new MulticastSocket(6789);
                    s.joinGroup(group); 

                    byte[] buffer = new byte[1000];
                    for(int i=0; i< 3; i++) {
                        System.out.println("Waiting for messages");
                        DatagramPacket messageIn = 
                            new DatagramPacket(buffer, buffer.length);
                        s.receive(messageIn);
                        System.out.println("Message: " + new String(messageIn.getData())+ " from: "+ messageIn.getAddress());
                        num = new String(messageIn.getData()).trim();
                            rellenaCasillas(num);
                        Thread.sleep(1000);
                        if (!hilo.isAlive()){
                            deseleccionaCasillas();
                        }
                    }
                    s.leaveGroup(group);		
                }
                catch (SocketException e){
                    System.out.println("Socket: " + e.getMessage());
                }
                catch (IOException e){
                    System.out.println("IO: " + e.getMessage());
                } catch (InterruptedException ex) {
                    System.out.println("Interrupt: " + ex.getMessage());
                }
                finally {
                   if(s != null) s.close();
                }
            }
        }       
    }
        
        
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    // End of variables declaration//GEN-END:variables
}
