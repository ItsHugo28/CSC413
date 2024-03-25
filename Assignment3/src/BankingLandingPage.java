/*************************************************
 File: BankignLandingPage.java
 By: Hugo Gomez
 Date: 3/25/2024
 Description: This is the landing page for the bank
 and allows the user to start banking, where they login
 and view their account details, withdraw, depost, or
 delete their account or update
 *************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class BankingLandingPage extends javax.swing.JFrame implements ActionListener {

    BankingLandingPage() {

        getContentPane().setBackground(Color.GRAY);
        setLayout(null);
        setResizable(true);
        setTitle("Banking Application");

        JLabel windowHeading = new JLabel("Banking App");
        windowHeading.setBounds(80, 50, 1200, 60);
        windowHeading.setFont(new Font("Calibri", Font.PLAIN, 60));
        windowHeading.setForeground(Color.LIGHT_GRAY);
        add(windowHeading);

        ImageIcon iIcon = new ImageIcon(ClassLoader.getSystemResource("icons/emp.jpg"));
        Image anImage = iIcon.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon iIcon2 = new ImageIcon(anImage);
        JLabel theLabel = new JLabel(iIcon2);
        theLabel.setBounds(50, 130, 1000, 600);
        add(theLabel);

        JButton openAppButton = new javax.swing.JButton("Start Banking");
        openAppButton.setBounds(400, 550, 200, 50);
        openAppButton.addActionListener(this);
        theLabel.add(openAppButton);


        setSize(1200, 800);
        setLocation(100, 100);
        setVisible(true);

    }

    public static void main(String[] args) {
        new BankingLandingPage();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        UserLogin userLogin = new UserLogin();
    }
}
