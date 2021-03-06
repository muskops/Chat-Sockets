/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author iTzFiliSalsa
 */
public class Cliente extends javax.swing.JFrame {

    Socket socket = null;
    BufferedReader lector = null;
    PrintWriter escritor = null;
    String usuario = "Default";
    int port = 9000;
    String menviado;
    /**
     * Creates new form Cliente
     */
    public Cliente() {

        initComponents();
        setSize(400, 600);
        setTitle("cliente");
        setLocationRelativeTo(null);
        Thread principal = new Thread(new Runnable() {
            public void run() {
                try {
                    socket = new Socket("localhost", port);
                    leer();
                    escribir();
                } catch (Exception ex) {
                    System.out.println("Error " + ex);
                }
            }
        });
        principal.start();
    }

    public void leer() {
        Thread leer = new Thread(new Runnable() {
            public void run() {
                try {
                    lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (true) {
                        String mrecibido = lector.readLine();
                        texto.append(mrecibido+"\n");
                    }
                } catch (Exception ex) {
                    System.out.println("Error " + ex);
                }
            }
        });
        leer.start();
    }

    public void escribir() {
        Thread escribir = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    escritor = new PrintWriter(socket.getOutputStream(), true);
                    enviar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            port = Integer.parseInt(puerto.getText());
                            if (NServidor.getText().equals("")) {
                                usuario = "default";
                            } else {
                                usuario = NServidor.getText();
                            }
                            menviado = tenviado.getText();
                            texto.append(usuario + ": " + menviado + "\n\n");
                            escritor.println(usuario + ": " + menviado + "\n");
                            tenviado.setText("");
                        }
                    });
                } catch (Exception ex) {
                    System.out.println("Error " + ex);
                }
            }
        });
        escribir.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NServidor = new javax.swing.JTextField();
        puerto = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        texto = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tenviado = new javax.swing.JTextArea();
        enviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        NServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NServidorActionPerformed(evt);
            }
        });
        getContentPane().add(NServidor);
        NServidor.setBounds(40, 20, 100, 30);
        getContentPane().add(puerto);
        puerto.setBounds(230, 20, 100, 30);

        texto.setColumns(20);
        texto.setLineWrap(true);
        texto.setRows(5);
        texto.setWrapStyleWord(true);
        texto.setBorder(null);
        texto.setOpaque(false);
        jScrollPane4.setViewportView(texto);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(40, 80, 290, 340);

        tenviado.setColumns(20);
        tenviado.setLineWrap(true);
        tenviado.setRows(5);
        tenviado.setWrapStyleWord(true);
        tenviado.setBorder(null);
        jScrollPane3.setViewportView(tenviado);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(70, 430, 130, 60);

        enviar.setText("Enviar");
        getContentPane().add(enviar);
        enviar.setBounds(240, 450, 63, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NServidorActionPerformed

    }//GEN-LAST:event_NServidorActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NServidor;
    private javax.swing.JButton enviar;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField puerto;
    private javax.swing.JTextArea tenviado;
    private javax.swing.JTextArea texto;
    // End of variables declaration//GEN-END:variables
}
