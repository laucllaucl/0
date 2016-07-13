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
 * A class to store the design of a form for filling in the envelope's details.
 */
public class EnvelopePanel {

    String zBoxOption[] = {"1", "2", "3"};
    String sBoxOption[] = {"S", "M", "L"};
    JTextField EFid = new JTextField(12);
    JComboBox zoneBox = new JComboBox(zBoxOption);
    JComboBox sizeBox = new JComboBox(sBoxOption);
    JPanel ePanel = new JPanel();
    Envelope a;
    int result;

    /**
     * A constructor to create a form for filling in the envelope's details.
     */
    public EnvelopePanel() {

        ePanel.setLayout(new GridLayout(0, 1));
        ePanel.setSize(250, 400);
        ePanel.setLocation(500, 300);
        ePanel.setVisible(true);

        JLabel EFi = new JLabel("ID No. :");
        ePanel.add(EFi);
        ePanel.add(EFid);

        JLabel EFz = new JLabel("Zone :");
        ePanel.add(EFz);
        ePanel.add(zoneBox);

        JLabel EFs = new JLabel("Size :");
        ePanel.add(EFs);
        ePanel.add(sizeBox);

        result = JOptionPane.showConfirmDialog(null, ePanel, "Add an envelope", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                char c = zoneBox.getSelectedItem().toString().charAt(0);
                char d = sizeBox.getSelectedItem().toString().charAt(0);
                a = new Envelope(Integer.parseInt(EFid.getText()), c, d);
            } catch (NullPointerException | NumberFormatException n) {
                JOptionPane.showMessageDialog(new JFrame(), "Please input integers in all the fields (except the length of tube)!");
            }
        }
    }

    /**
     * A method to get an envelope object which is just created
     * @return an envelope object
     */
    public Envelope getEnvelope() {
        Envelope x = a;
        return x;
    }

}
