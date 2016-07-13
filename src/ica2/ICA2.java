/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica2;

import java.awt.*;
import javax.swing.*;

/**
 *
 * A class to store the GUI design of the whole application.
 */
public class ICA2 extends JPanel {

    /**
     * To create the GUI of the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame mainWnd = new JFrame();
        mainWnd.setSize(600, 600);
        mainWnd.setTitle("Delivery Bay");
        mainWnd.setLayout(new BorderLayout());
        mainWnd.add(new Buttons(), BorderLayout.EAST);
        mainWnd.add(new Cells(), BorderLayout.CENTER);
        mainWnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWnd.setVisible(true);
        
    }

}
