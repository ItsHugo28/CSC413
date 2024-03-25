/*************************************************
 File: UserLogin.java
 By: Hugo Gomez
 Date: 3/25/2024
 Description: This is used to login to the user's
 bank account
 *************************************************/

import java.awt.event.*;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class UserLogin extends javax.swing.JFrame implements ActionListener {


    public UserLogin() {
        initComponents();
        setVisible(true);
    }
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");



        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel1))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                                        .addComponent(jTextField2)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(154, 154, 154)
                                                .addComponent(jButton1)))
                                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(40, 40, 40))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String username = jTextField1.getText();
        String password = jTextField2.getText();

        if (!username.isEmpty() && !password.isEmpty()) {
            HashMap<String, String> hm = new AccountDAOConcrete().validateLogin(username, password);

            if (hm != null && !hm.isEmpty()) {
                int accountId = getAccountId(username, password);
                if (accountId != -1) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    setVisible(false);
                    new HomePage(accountId);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to fetch account ID. Please try again.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter both username and password.");
        }
    }

    private int getAccountId(String username, String password) {

        int accountId = AccountDAOConcrete.getAccountId(username, password);
        return accountId;
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        UserLogin userLogin = new UserLogin();

    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserLogin().setVisible(true);
            }
        });
    }
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
}