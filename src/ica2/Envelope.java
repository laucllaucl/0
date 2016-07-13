/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica2;

import javax.swing.ImageIcon;

/**
 *
 * A class to store the envelope's information.
 */
public class Envelope extends Parcel {
    
    char size;
    
    /**
     * A constructor to create an envelope object.
     */
    public Envelope() {
        super();
    }
    
    /**
     * A constructor to create an envelope object with details.
     * @param id envelope's id
     * @param z envelope's zone
     * @param s envelope's size
     */
    public Envelope(int id, char z, char s) {
        super(id, z);
        size = s;
    }
    
    /**
     * A method to get an envelope's size
     * @return envelope's size
     */
    public char getSize() {
        return size;
    }
    
    /**
     * A method to calcuate the charge for an envelope.
     * @return envelope's charge
     */
    @Override
    public double getCharge() {
        if (size == 'S') {
            charge = 4.25;
        } else if (size == 'M') {
            charge = 8.25;
        } else if (size == 'L') {
            charge = 16.49;
        }
        if (zone == '1') {
            charge *= 1;
        } else if (zone == '2') {
            charge *= 1.5;
        } else if (zone == '3') {
            charge *= 2;
        }
        return charge;
    }
    
    /**
     * A method to generate an image for an envelope.
     * @return envelope's image
     */
    @Override
    public ImageIcon getImage() {
        ImageIcon image = null;
        switch (size) {
            case 'S':
                image = new ImageIcon("envelope-small.png");
                break;
            case 'M':
                image = new ImageIcon("envelope-medium.png");
                break;
            case 'L':
                image = new ImageIcon("envelope-large.png");
                break;
        }
        return image;
    }
    
    /**
     * A method to convert an envelope's details into string
     * @return envelope's details
     */
    @Override
    public String toString() {
        String str = "ID No.: " + idNum + "\nZone: " + zone + "\nCharge: £" + getCharge() + "\nSize: " + getSize();
        return str;
    }
    
}
