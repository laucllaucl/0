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
 * A class to store the design of a form for filling in the box's details.
 */
public class BoxPanel extends JPanel {

    String zBoxOption[] = {"1", "2", "3"};
    JTextField BFid = new JTextField(12);
    JComboBox zoneBox = new JComboBox(zBoxOption);
    JTextField BFwidth = new JTextField(12);
    JTextField BFlength = new JTextField(12);
    JTextField BFheight = new JTextField(12);
    JPanel bPanel = new JPanel();
    Box a;
    int result;

    /**
     * A constructor to create a form for filling in the box's details.
     */
    public BoxPanel() {

        bPanel.setLayout(new GridLayout(0, 1));
        bPanel.setSize(250, 400);
        bPanel.setLocation(500, 300);
        bPanel.setVisible(true);

        JLabel BFi = new JLabel("ID No. :");
        bPanel.add(BFi);
        bPanel.add(BFid);

        JLabel BFz = new JLabel("Zone :");
        bPanel.add(BFz);
        bPanel.add(zoneBox);

        JLabel BFw = new JLabel("Width :");
        bPanel.add(BFw);
        bPanel.add(BFwidth);

        JLabel BFl = new JLabel("Length :");
        bPanel.add(BFl);
        bPanel.add(BFlength);

        JLabel BFh = new JLabel("Height :");
        bPanel.add(BFh);
        bPanel.add(BFheight);

        result = JOptionPane.showConfirmDialog(null, bPanel, "Add a box", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                char c = zoneBox.getSelectedItem().toString().charAt(0);
                a = new Box(Integer.parseInt(BFid.getText()), c, Integer.parseInt(BFwidth.getText()), Integer.parseInt(BFlength.getText()), Integer.parseInt(BFheight.getText()));
            } catch (NullPointerException | NumberFormatException n) {
                JOptionPane.showMessageDialog(new JFrame(), "Please input integers in all the fields (except the length of tube)!");
            }
        }
    }

    /**
     * A method to get a box object which is just created
     * @return a box object
     */
    public Box getBox() {
        Box x = a;
        return x;
    }

}
