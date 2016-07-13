/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * A class to store the buttons' design and functions in the GUI.
 */
public class Buttons extends JPanel implements ActionListener {

    static int count = 0;                                                         //counter for all cells
    static int sCount = 0;                                                        //counter for small cells
    static int lCount = 12;                                                       //counter for large cells
    static double currentCharge = 0;
    static double totalCharge = 0;
    private final JButton buttonArray[] = new JButton[8];
    private final JButton button1 = new JButton("Add a Box");
    private final JButton button2 = new JButton("Add a Tube");
    private final JButton button3 = new JButton("Add a Envelope");
    private final JButton button4 = new JButton("Clear All");
    private final JButton button5 = new JButton("Current Charge");
    private final JButton button6 = new JButton("Total Charge");
    private final JButton button7 = new JButton("Save");
    private final JButton button8 = new JButton("Load");

    static Parcel parcel[] = new Parcel[16];

    /**
     * A constructor to create the buttons in GUI
     */
    public Buttons() {
        buttonArray[0] = button1;
        buttonArray[1] = button2;
        buttonArray[2] = button3;
        buttonArray[3] = button4;
        buttonArray[4] = button5;
        buttonArray[5] = button6;
        buttonArray[6] = button7;
        buttonArray[7] = button8;

        setLayout(new GridLayout(8, 0));
        for (int i = 0; i < 8; i++) {
            buttonArray[i].addActionListener(this);
            add(buttonArray[i]);
        }

        setVisible(true);
    }

    /**
     *
     * Different functions will be performed when the buttons are clicked.<br>
     *
     * <br>1. Add a Box: Allow users to create a box by filling in the related
     * information (box’s id number, delivery zone, width, length and
     * height).<br>
     * <br>2. Add a Tube: Allow users to create a tube by filling in the related
     * information (tube’s id number, delivery zone, and length).<br>
     * <br>3. Add a Envelope: Allow users to create an envelope by filling in
     * the related information (envelope’s id number, delivery zone and
     * size).<br>
     * <br>4. Clear All: Clears all items from the application and resets the
     * totals to zero.<br>
     * <br>5. Current Charge: Display the charge associated with the parcels
     * currently in the despatch depot.<br>
     * <br>6. Total Charge: Display the total charge for all parcels that have
     * been sent (or are currently in) the depot since the application started,
     * or the last‘Clear All’or‘Load’button event.<br>
     * <br>7. Save: Allow users to save all parcels currently in the depot into
     * a dat file by inputting the file name.<br>
     * <br>8. Load: Allow users to load the parcel details into the application
     * by inputting the name of an existing dat file.
     *
     * @param e the GUI button clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (count < 16) {

            if (e.getSource() == buttonArray[0]) {                              //Add a Box

                BoxPanel b = new BoxPanel();
                if (b.result != JOptionPane.CANCEL_OPTION && b.result != JOptionPane.CLOSED_OPTION) {
                    try {
                        if (("1".equals(b.zoneBox.getSelectedItem().toString())) || ("2".equals(b.zoneBox.getSelectedItem().toString()))) {
                            if (sCount < 12) {
                                parcel[sCount] = b.getBox();
                                Cells.cell[sCount].setIcon(parcel[sCount].getImage());
                                totalCharge += parcel[sCount].getCharge();
                                sCount++;
                                count++;
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Small cells are full.");
                            }
                        } else if ("3".equals(b.zoneBox.getSelectedItem().toString())) {
                            if (lCount - 12 < 4) {
                                parcel[lCount] = b.getBox();
                                Cells.lCell[lCount - 12].setIcon(parcel[lCount].getImage());
                                totalCharge += parcel[lCount].getCharge();
                                lCount++;
                                count++;
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Large cells are full.");
                            }
                        }
                    } catch (NullPointerException | NumberFormatException n) {

                    }
                }

            } else if (e.getSource() == buttonArray[1]) {                       //Add a Tube

                TubePanel t = new TubePanel();
                if (t.result != JOptionPane.CANCEL_OPTION && t.result != JOptionPane.CLOSED_OPTION) {
                    try {
                        if (Double.parseDouble(t.TFlength.getText()) < 1.5) {
                            if (sCount < 12) {
                                parcel[sCount] = t.getTube();
                                Cells.cell[sCount].setIcon(parcel[sCount].getImage());
                                totalCharge += parcel[sCount].getCharge();
                                sCount++;
                                count++;
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Small cells are full.");
                            }
                        } else if (Double.parseDouble(t.TFlength.getText()) > 2.5) {
                            JOptionPane.showMessageDialog(new JFrame(), "Tube's size should not be larger than 2.5 metres.");
                        } else {
                            if (lCount - 12 < 4) {
                                parcel[lCount] = t.getTube();
                                Cells.lCell[lCount - 12].setIcon(parcel[lCount].getImage());
                                totalCharge += parcel[lCount].getCharge();
                                lCount++;
                                count++;
                            } else {
                                JOptionPane.showMessageDialog(new JFrame(), "Large cells are full.");
                            }
                        }
                    } catch (NullPointerException | NumberFormatException n) {

                    }
                }

            } else if (e.getSource() == buttonArray[2]) {                       //Add an Envelope

                EnvelopePanel en = new EnvelopePanel();
                if (en.result != JOptionPane.CANCEL_OPTION && en.result != JOptionPane.CLOSED_OPTION) {
                    try {
                        if (null != en.sizeBox.getSelectedItem().toString()) {
                            switch (en.sizeBox.getSelectedItem().toString()) {
                                case "S":
                                    if (sCount < 12) {
                                        parcel[sCount] = en.getEnvelope();
                                        Cells.cell[sCount].setIcon(parcel[sCount].getImage());
                                        totalCharge += parcel[sCount].getCharge();
                                        sCount++;
                                        count++;
                                    } else {
                                        JOptionPane.showMessageDialog(new JFrame(), "Small cells are full.");
                                    }
                                    break;
                                case "M":
                                    if (sCount < 12) {
                                        parcel[sCount] = en.getEnvelope();
                                        Cells.cell[sCount].setIcon(parcel[sCount].getImage());
                                        totalCharge += parcel[sCount].getCharge();
                                        sCount++;
                                        count++;
                                    } else {
                                        JOptionPane.showMessageDialog(new JFrame(), "Small cells are full.");
                                    }
                                    break;
                                case "L":
                                    if (lCount - 12 < 4) {
                                        parcel[lCount] = en.getEnvelope();
                                        Cells.lCell[lCount - 12].setIcon(parcel[lCount].getImage());
                                        totalCharge += parcel[lCount].getCharge();
                                        lCount++;
                                        count++;
                                    } else {
                                        JOptionPane.showMessageDialog(new JFrame(), "Large cells are full.");
                                    }
                                    break;
                            }
                        }
                    } catch (NullPointerException | NumberFormatException n) {

                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(new JFrame(), "All cells are full.");
        }

        if (e.getSource() == buttonArray[3]) {                                  //Clear All

            count = 0;
            sCount = 0;
            lCount = 12;
            totalCharge = 0;
            for (int i = 0; i < 12; i++) {
                Cells.cell[i].setIcon(null);
            }
            for (int i = 0; i < 4; i++) {
                Cells.lCell[i].setIcon(null);
            }
            parcel = new Parcel[16];

        } else if (e.getSource() == buttonArray[4]) {                           //Current Charge

            currentCharge = 0;
            for (int i = 0; i < 16; i++) {
                if (parcel[i] != null) {
                    currentCharge += parcel[i].getCharge();
                }
            }
            JOptionPane.showMessageDialog(new JFrame(), "Current Charge: £" + currentCharge);

        } else if (e.getSource() == buttonArray[5]) {                           //Total Charge

            JOptionPane.showMessageDialog(new JFrame(), "Total Charge: £" + totalCharge);

        } else if (e.getSource() == buttonArray[6]) {                           //Save

            String fileName = JOptionPane.showInputDialog("Save As", "File Name (No need to input the file type.)");
            if ((fileName != null) && (fileName.length() > 0)) {
                try {
                    FileOutputStream fo = new FileOutputStream(fileName + ".dat");
                    ObjectOutputStream oos = new ObjectOutputStream(fo);
                    for (int i = 0; i < 16; i++) {
                        oos.writeObject(parcel[i]);
                    }
                    oos.close();
                    fo.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Buttons.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Buttons.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Please input file name.");
            }

        } else if (e.getSource() == buttonArray[7]) {                           //Load

            String fileName = JOptionPane.showInputDialog("Load File", "File Name (No need to input the file type.)");
            if ((fileName != null) && (fileName.length() > 0)) {
                try {
                    FileInputStream fi = new FileInputStream(fileName + ".dat");
                    ObjectInputStream ois = new ObjectInputStream(fi);
                    count = 0;
                    sCount = 0;
                    lCount = 12;
                    totalCharge = 0;
                    for (int i = 0; i < 12; i++) {
                        Cells.cell[i].setIcon(null);
                    }
                    for (int i = 0; i < 4; i++) {
                        Cells.lCell[i].setIcon(null);
                    }
                    parcel = new Parcel[16];
                    for (int i = 0; i < 16; i++) {
                        parcel[i] = (Parcel) ois.readObject();
                        if (i < 12) {
                            if (parcel[i] != null) {
                                Cells.cell[i].setIcon(parcel[i].getImage());
                                totalCharge += parcel[i].getCharge();
                                sCount++;
                                count++;
                            }
                        } else {
                            if (parcel[i] != null) {
                                Cells.lCell[i - 12].setIcon(parcel[i].getImage());
                                totalCharge += parcel[i].getCharge();
                                lCount++;
                                count++;
                            }
                        }
                    }
                    ois.close();
                    fi.close();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), "File not found!", "Error", JOptionPane.WARNING_MESSAGE);
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(Buttons.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Please input file name.");
            }

        }
    }
}
