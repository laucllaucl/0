/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica2;

import javax.swing.*;
import java.awt.*;

/**
 *
 * A class to store the design of a form for filling in the tube's details.
 */
public class TubePanel {

    String zBoxOption[] = {"1", "2", "3"};
    JTextField TFid = new JTextField(12);
    JComboBox zoneBox = new JComboBox(zBoxOption);
    JTextField TFlength = new JTextField(12);
    JPanel bPanel = new JPanel();
    Tube a;
    int result;

    /**
     * A constructor to create a form for filling in the tube's details.
     */
    public TubePanel() {

        bPanel.setLayout(new GridLayout(0, 1));
        bPanel.setSize(250, 400);
        bPanel.setLocation(500, 300);
        bPanel.setVisible(true);

        JLabel TFi = new JLabel("ID No. :");
        bPanel.add(TFi);
        bPanel.add(TFid);

        JLabel TFz = new JLabel("Zone :");
        bPanel.add(TFz);
        bPanel.add(zoneBox);

        JLabel TFl = new JLabel("Length :");
        bPanel.add(TFl);
        bPanel.add(TFlength);

        result = JOptionPane.showConfirmDialog(null, bPanel, "Add a tube", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                char c = zoneBox.getSelectedItem().toString().charAt(0);
                a = new Tube(Integer.parseInt(TFid.getText()), c, Double.parseDouble(TFlength.getText()));
            } catch (NullPointerException | NumberFormatException n) {
               JOptionPane.showMessageDialog(new JFrame(), "Please input integers in all the fields (except the length of tube)!");
            }
        }
    }

    /**
     * A method to get a tube object which is just created
     * @return a tube object
     */
    public Tube getTube() {
        Tube x = a;
        return x;
    }

}
