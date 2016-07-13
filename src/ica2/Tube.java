/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica2;

import javax.swing.ImageIcon;

/**
 *
 * A class to store the tube's information.
 */
public class Tube extends Parcel {
    
    double length;
    
    /**
     * A constructor to create a tube object.
     */
    public Tube() {
        super();
        length = 0;
    }
    
    /**
     * A constructor to create a tube object with details.
     * @param id tube's id
     * @param z tube's zone
     * @param l tube's length
     */
    public Tube(int id, char z, double l) {
        super(id, z);
        length = l;
    }
    
    /**
     * A method to calculate the tube's length in cm.
     * @return tube's length
     */
    public int getLength() {                                                  //to be confirmed
        int lengthInCM =(int) (length * 100);
        return lengthInCM;
    }
    
    /**
     * A method to calcuate the charge for a tube.
     * @return tube's charge
     */
    @Override
    public double getCharge() {
        if (length < 1.5) {
            charge = 6.50;
        } else {
            charge = 10.50;
        }
        return charge;
    }
    
    /**
     * A method to generate an image for a tube.
     * @return tube's image
     */
    @Override
    public ImageIcon getImage() {
        ImageIcon image = null;
        if (length < 1.5) {
            image = new ImageIcon("tube.png");
        } else {
            image = new ImageIcon("tube-large.png");
        }
        return image;
    }
    
    /**
     * A method to convert a tube's details into string.
     * @return tube's details
     */
    @Override
    public String toString() {
        String str = "ID No.: " + idNum + "\nZone: " + zone + "\nCharge: £" + getCharge() + "\nHeight: " + getLength() + "cm";
        return str;
    }
    
}
