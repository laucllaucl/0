/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

/**
 *
 * A class to store the bay design and the mouse click events.
 */
public class Cells extends JPanel implements MouseListener {

    JPanel sMPanel = new JPanel();
    JPanel lPanel = new JPanel();

    static JLabel cell[] = new JLabel[12];
    private JLabel cell1 = new JLabel();
    private JLabel cell2 = new JLabel();
    private JLabel cell3 = new JLabel();
    private JLabel cell4 = new JLabel();
    private JLabel cell5 = new JLabel();
    private JLabel cell6 = new JLabel();
    private JLabel cell7 = new JLabel();
    private JLabel cell8 = new JLabel();
    private JLabel cell9 = new JLabel();
    private JLabel cell10 = new JLabel();
    private JLabel cell11 = new JLabel();
    private JLabel cell12 = new JLabel();

    static JLabel lCell[] = new JLabel[4];
    private JLabel lCell1 = new JLabel();
    private JLabel lCell2 = new JLabel();
    private JLabel lCell3 = new JLabel();
    private JLabel lCell4 = new JLabel();

    double tempCharge = 0;

    Cells() {

        setLayout(new GridLayout(2, 0));

        Border b = BorderFactory.createLineBorder(Color.BLUE, 3);

        cell[0] = cell1;
        cell[1] = cell2;
        cell[2] = cell3;
        cell[3] = cell4;
        cell[4] = cell5;
        cell[5] = cell6;
        cell[6] = cell7;
        cell[7] = cell8;
        cell[8] = cell9;
        cell[9] = cell10;
        cell[10] = cell11;
        cell[11] = cell12;

        lCell[0] = lCell1;
        lCell[1] = lCell2;
        lCell[2] = lCell3;
        lCell[3] = lCell4;

        sMPanel.setLayout(new GridLayout(3, 4));
        for (int i = 0; i < 12; i++) {
            sMPanel.add(cell[i]);
            cell[i].setBorder(b);
            cell[i].addMouseListener(this);
        }
        add(sMPanel);

        lPanel.setLayout(new GridLayout(1, 4));
        for (int i = 0; i < 4; i++) {
            lPanel.add(lCell[i]);
            lCell[i].setBorder(b);
            lCell[i].addMouseListener(this);
        }
        add(lPanel);
        this.addMouseListener(this);
    }

    /**
     *
     * Different functions will be performed when the mouse buttons are clicked
     * on the bays.<br>
     *
     * <br>1. Left Click: Display details of the parcel in the bay.<br>
     * <br>2. Right Click: Remove the parcel from the bay (after confirmation
     * from the user).<br>
     * <br>3. Middle Click: Change/update parcel attributes (id, zone, size
     * details) and update charge.<br>
     *
     * <br>If an empty cell (bay) is clicked, a message will be displayed to
     * state that the bay is empty.
     *
     * @param e the mouse button clicked
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == 1) {                                               //left click
            //check if the small bays are clicked
            for (int i = 0; i < 12; i++) {
                if (e.getSource() == cell[i]) {
                    if (Buttons.parcel[i] != null) {
                        JOptionPane.showMessageDialog(new JFrame(), Buttons.parcel[i].toString());
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "The bay is empty.");
                    }
                }
            }
            //check if the large bays are clicked
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == lCell[i]) {
                    if (Buttons.parcel[i + 12] != null) {
                        JOptionPane.showMessageDialog(new JFrame(), Buttons.parcel[i + 12].toString());
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "The bay is empty.");
                    }
                }
            }

        } else if (e.getButton() == 2) {                                        //middle clicked
            //check if the small bays are clicked
            for (int i = 0; i < 12; i++) {
                if (e.getSource() == cell[i]) {
                    if (Buttons.parcel[i] != null) {

                        if ("class ica2.Box".equals(Buttons.parcel[i].getClass().toString())) {//check if clicked parcel is a box
                            BoxPanel b = new BoxPanel();
                            if (b.result != JOptionPane.CANCEL_OPTION && b.result != JOptionPane.CLOSED_OPTION) {
                                try {
                                    if (("1".equals(b.zoneBox.getSelectedItem().toString())) || ("2".equals(b.zoneBox.getSelectedItem().toString()))) {
                                        b.getBox().getCharge();
                                        Buttons.totalCharge -= Buttons.parcel[i].getCharge();
                                        Buttons.parcel[i] = b.getBox();
                                        cell[i].setIcon(Buttons.parcel[i].getImage());
                                        Buttons.totalCharge += Buttons.parcel[i].getCharge();
                                    } else if ("3".equals(b.zoneBox.getSelectedItem().toString())) {
                                        if (Buttons.lCount - 12 < 4) {
                                            Buttons.totalCharge -= Buttons.parcel[i].getCharge();
                                            Buttons.parcel[Buttons.lCount] = b.getBox();
                                            lCell[Buttons.lCount - 12].setIcon(Buttons.parcel[Buttons.lCount].getImage());
                                            Buttons.totalCharge += Buttons.parcel[Buttons.lCount].getCharge();
                                            while (i < 11 && Buttons.parcel[i + 1] != null) {
                                                Buttons.parcel[i] = Buttons.parcel[i + 1];
                                                cell[i].setIcon(cell[i + 1].getIcon());
                                                i++;
                                            }
                                            Buttons.parcel[i] = null;
                                            cell[i].setIcon(null);
                                            Buttons.lCount++;
                                            Buttons.sCount--;
                                        } else {
                                            JOptionPane.showMessageDialog(new JFrame(), "Large cells are full.");
                                        }
                                    }
                                } catch (NullPointerException | NumberFormatException n) {
                                    if ("3".equals(b.zoneBox.getSelectedItem().toString())) {
                                        Buttons.totalCharge += Buttons.parcel[i].getCharge();
                                    }
                                }
                            }

                        } else if ("class ica2.Tube".equals(Buttons.parcel[i].getClass().toString())) {//check if clicked parcel is a tube
                            TubePanel t = new TubePanel();
                            if (t.result != JOptionPane.CANCEL_OPTION && t.result != JOptionPane.CLOSED_OPTION) {
                                try {
                                    if (Double.parseDouble(t.TFlength.getText()) < 1.5) {
                                        t.getTube().getCharge();
                                        Buttons.totalCharge -= Buttons.parcel[i].getCharge();
                                        Buttons.parcel[i] = t.getTube();
                                        cell[i].setIcon(Buttons.parcel[i].getImage());
                                        Buttons.totalCharge += Buttons.parcel[i].getCharge();
                                    } else if (Double.parseDouble(t.TFlength.getText()) > 2.5) {
                                        JOptionPane.showMessageDialog(new JFrame(), "Tube's size should not be larger than 2.5 metres.");
                                    } else {
                                        if (Buttons.lCount - 12 < 4) {
                                            Buttons.totalCharge -= Buttons.parcel[i].getCharge();
                                            Buttons.parcel[Buttons.lCount] = t.getTube();
                                            lCell[Buttons.lCount - 12].setIcon(Buttons.parcel[Buttons.lCount].getImage());
                                            Buttons.totalCharge += Buttons.parcel[Buttons.lCount].getCharge();
                                            while (i < 11 && Buttons.parcel[i + 1] != null) {
                                                Buttons.parcel[i] = Buttons.parcel[i + 1];
                                                cell[i].setIcon(cell[i + 1].getIcon());
                                                i++;
                                            }
                                            Buttons.parcel[i] = null;
                                            cell[i].setIcon(null);
                                            Buttons.lCount++;
                                            Buttons.sCount--;
                                        } else {
                                            JOptionPane.showMessageDialog(new JFrame(), "Large cells are full.");
                                        }
                                    }
                                } catch (NullPointerException | NumberFormatException n) {
                                    try {
                                        if (Double.parseDouble(t.TFlength.getText()) >= 1.5 && Double.parseDouble(t.TFlength.getText()) <= 2.5) {
                                            Buttons.totalCharge += Buttons.parcel[i].getCharge();
                                        }
                                    } catch (NullPointerException | NumberFormatException x) {
                                    }
                                }
                            }

                        } else if ("class ica2.Envelope".equals(Buttons.parcel[i].getClass().toString())) {//check if clicked parcel is an envelope
                            EnvelopePanel en = new EnvelopePanel();
                            if (en.result != JOptionPane.CANCEL_OPTION && en.result != JOptionPane.CLOSED_OPTION) {
                                try {
                                    if (null != en.sizeBox.getSelectedItem().toString()) {
                                        switch (en.sizeBox.getSelectedItem().toString()) {
                                            case "S":
                                                en.getEnvelope().getCharge();
                                                Buttons.totalCharge -= Buttons.parcel[i].getCharge();
                                                Buttons.parcel[i] = en.getEnvelope();
                                                cell[i].setIcon(Buttons.parcel[i].getImage());
                                                Buttons.totalCharge += Buttons.parcel[i].getCharge();
                                                break;
                                            case "M":
                                                en.getEnvelope().getCharge();
                                                Buttons.totalCharge -= Buttons.parcel[i].getCharge();
                                                Buttons.parcel[i] = en.getEnvelope();
                                                cell[i].setIcon(Buttons.parcel[i].getImage());
                                                Buttons.totalCharge += Buttons.parcel[i].getCharge();
                                                break;
                                            case "L":
                                                if (Buttons.lCount - 12 < 4) {
                                                    Buttons.totalCharge -= Buttons.parcel[i].getCharge();
                                                    Buttons.parcel[Buttons.lCount] = en.getEnvelope();
                                                    Cells.lCell[Buttons.lCount - 12].setIcon(Buttons.parcel[Buttons.lCount].getImage());
                                                    Buttons.totalCharge += Buttons.parcel[Buttons.lCount].getCharge();
                                                    while (i < 11 && Buttons.parcel[i + 1] != null) {
                                                        Buttons.parcel[i] = Buttons.parcel[i + 1];
                                                        cell[i].setIcon(cell[i + 1].getIcon());
                                                        i++;
                                                    }
                                                    Buttons.parcel[i] = null;
                                                    cell[i].setIcon(null);
                                                    Buttons.lCount++;
                                                    Buttons.sCount--;
                                                } else {
                                                    JOptionPane.showMessageDialog(new JFrame(), "Large cells are full.");
                                                }
                                                break;
                                        }
                                    }
                                } catch (NullPointerException | NumberFormatException n) {
                                    switch (en.sizeBox.getSelectedItem().toString()) {
                                        case "S":
                                            break;
                                        case "M":
                                            break;
                                        case "L":
                                            Buttons.totalCharge += Buttons.parcel[i].getCharge();
                                            break;
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "The bay is empty.");
                    }
                }
            }
            //check if the large bays are clicked
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == lCell[i]) {
                    if (Buttons.parcel[i + 12] != null) {

                        if ("class ica2.Box".equals(Buttons.parcel[i + 12].getClass().toString())) {//check if clicked parcel is a box
                            BoxPanel b = new BoxPanel();
                            if (b.result != JOptionPane.CANCEL_OPTION && b.result != JOptionPane.CLOSED_OPTION) {
                                try {
                                    if (("1".equals(b.zoneBox.getSelectedItem().toString())) || ("2".equals(b.zoneBox.getSelectedItem().toString()))) {
                                        if (Buttons.sCount < 12) {
                                            Buttons.totalCharge -= Buttons.parcel[i + 12].getCharge();
                                            Buttons.parcel[Buttons.sCount] = b.getBox();
                                            cell[Buttons.sCount].setIcon(Buttons.parcel[Buttons.sCount].getImage());
                                            Buttons.totalCharge += Buttons.parcel[Buttons.sCount].getCharge();
                                            while ((i + 12) < 15 && Buttons.parcel[i + 12 + 1] != null) {
                                                Buttons.parcel[i + 12] = Buttons.parcel[i + 12 + 1];
                                                lCell[i].setIcon(lCell[i + 1].getIcon());
                                                i++;
                                            }
                                            Buttons.parcel[i + 12] = null;
                                            lCell[i].setIcon(null);
                                            Buttons.lCount--;
                                            Buttons.sCount++;

                                        } else {
                                            JOptionPane.showMessageDialog(new JFrame(), "Small cells are full.");
                                        }
                                    } else if ("3".equals(b.zoneBox.getSelectedItem().toString())) {
                                        b.getBox().getCharge();
                                        Buttons.totalCharge -= Buttons.parcel[i].getCharge();
                                        Buttons.parcel[i] = b.getBox();
                                        lCell[i].setIcon(Buttons.parcel[i].getImage());
                                        Buttons.totalCharge += Buttons.parcel[i].getCharge();
                                    } else {
                                        JOptionPane.showMessageDialog(new JFrame(), "Large cells are full.");
                                    }
                                } catch (NullPointerException | NumberFormatException n) {
                                    if (("1".equals(b.zoneBox.getSelectedItem().toString())) || ("2".equals(b.zoneBox.getSelectedItem().toString()))) {
                                        Buttons.totalCharge += Buttons.parcel[i + 12].getCharge();
                                    }
                                }
                            }

                        } else if ("class ica2.Tube".equals(Buttons.parcel[i + 12].getClass().toString())) {//check if clicked parcel is a tube
                            TubePanel t = new TubePanel();
                            if (t.result != JOptionPane.CANCEL_OPTION && t.result != JOptionPane.CLOSED_OPTION) {
                                try {
                                    if (Double.parseDouble(t.TFlength.getText()) < 1.5) {
                                        if (Buttons.sCount < 12) {
                                            Buttons.totalCharge -= Buttons.parcel[i + 12].getCharge();
                                            Buttons.parcel[Buttons.sCount] = t.getTube();
                                            cell[Buttons.sCount].setIcon(Buttons.parcel[Buttons.sCount].getImage());
                                            Buttons.totalCharge += Buttons.parcel[Buttons.sCount].getCharge();
                                            while ((i + 12) < 15 && Buttons.parcel[i + 12 + 1] != null) {
                                                Buttons.parcel[i + 12] = Buttons.parcel[i + 12 + 1];
                                                lCell[i].setIcon(lCell[i + 1].getIcon());
                                                i++;
                                            }
                                            Buttons.parcel[i + 12] = null;
                                            lCell[i].setIcon(null);
                                            Buttons.lCount--;
                                            Buttons.sCount++;
                                        } else {
                                            JOptionPane.showMessageDialog(new JFrame(), "Small cells are full.");
                                        }
                                    } else if (Double.parseDouble(t.TFlength.getText()) > 2.5) {
                                        JOptionPane.showMessageDialog(new JFrame(), "Tube's size should not be larger than 2.5 metres.");
                                    } else {
                                        t.getTube().getCharge();
                                        Buttons.totalCharge -= Buttons.parcel[i].getCharge();
                                        Buttons.parcel[i] = t.getTube();
                                        lCell[i].setIcon(Buttons.parcel[i].getImage());
                                        Buttons.totalCharge += Buttons.parcel[i].getCharge();
                                    }
                                } catch (NullPointerException | NumberFormatException n) {
                                    try {
                                        if (Double.parseDouble(t.TFlength.getText()) < 1.5) {
                                            Buttons.totalCharge += Buttons.parcel[i + 12].getCharge();
                                        }
                                    } catch (NullPointerException | NumberFormatException x) {
                                    }
                                }
                            }

                        } else if ("class ica2.Envelope".equals(Buttons.parcel[i + 12].getClass().toString())) {//check if clicked parcel is an envelope
                            EnvelopePanel en = new EnvelopePanel();
                            if (en.result != JOptionPane.CANCEL_OPTION && en.result != JOptionPane.CLOSED_OPTION) {
                                try {
                                    if (null != en.sizeBox.getSelectedItem().toString()) {
                                        switch (en.sizeBox.getSelectedItem().toString()) {
                                            case "S":
                                                if (Buttons.sCount < 12) {
                                                    Buttons.totalCharge -= Buttons.parcel[i + 12].getCharge();
                                                    Buttons.parcel[Buttons.sCount] = en.getEnvelope();
                                                    cell[Buttons.sCount].setIcon(Buttons.parcel[Buttons.sCount].getImage());
                                                    Buttons.totalCharge += Buttons.parcel[Buttons.sCount].getCharge();
                                                    while ((i + 12) < 15 && Buttons.parcel[i + 12 + 1] != null) {
                                                        Buttons.parcel[i + 12] = Buttons.parcel[i + 12 + 1];
                                                        lCell[i].setIcon(lCell[i + 1].getIcon());
                                                        i++;
                                                    }
                                                    Buttons.parcel[i + 12] = null;
                                                    lCell[i].setIcon(null);
                                                    Buttons.lCount--;
                                                    Buttons.sCount++;
                                                } else {
                                                    JOptionPane.showMessageDialog(new JFrame(), "Small cells are full.");
                                                }
                                                break;
                                            case "M":
                                                if (Buttons.sCount < 12) {
                                                    Buttons.totalCharge -= Buttons.parcel[i + 12].getCharge();
                                                    Buttons.parcel[Buttons.sCount] = en.getEnvelope();
                                                    cell[Buttons.sCount].setIcon(Buttons.parcel[Buttons.sCount].getImage());
                                                    Buttons.totalCharge += Buttons.parcel[Buttons.sCount].getCharge();
                                                    while ((i + 12) < 15 && Buttons.parcel[i + 12 + 1] != null) {
                                                        Buttons.parcel[i + 12] = Buttons.parcel[i + 12 + 1];
                                                        lCell[i].setIcon(lCell[i + 1].getIcon());
                                                        i++;
                                                    }
                                                    Buttons.parcel[i + 12] = null;
                                                    lCell[i].setIcon(null);
                                                    Buttons.lCount--;
                                                    Buttons.sCount++;
                                                } else {
                                                    JOptionPane.showMessageDialog(new JFrame(), "Small cells are full.");
                                                }
                                                break;
                                            case "L":
                                                en.getEnvelope().getCharge();
                                                Buttons.totalCharge -= Buttons.parcel[i + 12].getCharge();
                                                Buttons.parcel[i + 12] = en.getEnvelope();
                                                lCell[i].setIcon(Buttons.parcel[i + 12].getImage());
                                                Buttons.totalCharge += Buttons.parcel[i + 12].getCharge();
                                                break;
                                        }
                                    }
                                } catch (NullPointerException | NumberFormatException n) {
                                    switch (en.sizeBox.getSelectedItem().toString()) {
                                        case "S":
                                            Buttons.totalCharge += Buttons.parcel[i + 12].getCharge();
                                            break;
                                        case "M":
                                            Buttons.totalCharge += Buttons.parcel[i + 12].getCharge();
                                            break;
                                        case "L":
                                            break;
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "The bay is empty.");
                    }
                }
            }

        } else if (e.getButton() == 3) {                                        //right click
            //check if the small bays are clicked
            for (int i = 0; i < 12; i++) {
                if (e.getSource() == cell[i]) {
                    if (Buttons.parcel[i] != null) {
                        int result = JOptionPane.showConfirmDialog(null, "Are you sure to remove the parcel?", "Remove Parcel", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if (result == JOptionPane.OK_OPTION) {
                            while (i < 11 && Buttons.parcel[i + 1] != null) {
                                Buttons.parcel[i] = Buttons.parcel[i + 1];
                                cell[i].setIcon(cell[i + 1].getIcon());
                                i++;
                            }
                            Buttons.parcel[i] = null;
                            cell[i].setIcon(null);
                            Buttons.sCount--;
                            Buttons.count--;
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "The bay is empty.");
                    }
                }
            }
            //check if the large bays are clicked
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == lCell[i]) {
                    if (Buttons.parcel[i + 12] != null) {
                        int result = JOptionPane.showConfirmDialog(null, "Are you sure to remove the parcel?", "Remove Parcel", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        if (result == JOptionPane.OK_OPTION) {
                            while ((i + 12) < 15 && Buttons.parcel[i + 12 + 1] != null) {
                                Buttons.parcel[i + 12] = Buttons.parcel[i + 12 + 1];
                                lCell[i].setIcon(lCell[i + 1].getIcon());
                                i++;
                            }
                            Buttons.parcel[i + 12] = null;
                            lCell[i].setIcon(null);
                            Buttons.lCount--;
                            Buttons.count--;
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "The bay is empty.");
                    }
                }
            }

        }

    }

    /**
     * no action
     *
     * @param e null
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * no action
     *
     * @param e null
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * no action
     *
     * @param e null
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * no action
     *
     * @param e null
     */
    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }
}
