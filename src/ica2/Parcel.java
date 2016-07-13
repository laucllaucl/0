/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica2;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * A class to store the parcel's information.
 */
public abstract class Parcel implements Serializable {

    /**
     * parcel's id
     */
    protected int idNum;
    
    /**
     * parcel's zone
     */
    protected char zone;
    
    /**
     * parcel's charge
     */
    protected double charge;

    /**
     * A constructor to create a parcel object.
     */
    public Parcel() {
        idNum = 0;
        charge = 0;
    }

    /**
     * 
     * A constructor to create a parcel object with details.
     * 
     * @param id parcel's id
     * @param z parcel's zone
     */
    public Parcel(int id, char z) {
        idNum = id;
        zone = z;
        charge = 0;
    }
    
    /**
     * A method to calcuate the charge for a parcel.
     * @return parcel's charge
     */
    public abstract double getCharge();
    
    /**
     * A method to generate an image for a parcel.
     * @return parcel's image
     */
    public abstract ImageIcon getImage();
    
    /**
     * A method to convert a parcel's details into string.
     * @return parcel's details
     */
    @Override
    public String toString() {
        String str = idNum + " " + zone + " " + charge;
        return str;
    }

}
