/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ica2;

import javax.swing.ImageIcon;

/**
 * 
 * A class to store the box's information.
 */
public class Box extends Parcel {

    private int width;
    private int length;
    private int height;
    private double weight;

    /**
     * A constructor to create a box object.
     */
    public Box() {
        super();
        width = 0;
        length = 0;
        height = 0;
    }

    /**
     * A constructor to create a box object with details.
     * 
     * @param id box's id
     * @param z box's zone
     * @param w box's width
     * @param l box's length
     * @param h box's height
     */
    public Box(int id, char z, int w, int l, int h) {
        super(id, z);
        width = w;
        length = l;
        height = h;
        
        weight = (width * length * height) / 6000;
        
    }

    /**
     * A method to get the box's width.
     * @return box's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * A method to get the box's length.
     * @return box's length
     */
    public int getLength() {
        return length;
    }

    /**
     * A method to get the box's height.
     * @return box's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * A method to calcuate the charge for a box.
     * @return box's charge
     */
    @Override
    public double getCharge() {
        switch (zone) {
            case '1':
                charge = weight * 1.70;
                break;
            case '2':
                charge = weight * 2.20;
                break;
            case '3':
                charge = weight * 3.05;
                break;
        }
        return charge;
    }

    /**
     * A method to generate an image for a box.
     * @return box's image
     */
    @Override
    public ImageIcon getImage() {
        ImageIcon image = null;
        switch (zone) {
            case '1':
                image = new ImageIcon("box.png");
                break;
            case '2':
                image = new ImageIcon("box.png");
                break;
            case '3':
                image = new ImageIcon("box-large.png");
                break;
        }
        return image;
    }

    /**
     * A method to convert a box's details into string.
     * @return box's details
     */
    @Override
    public String toString() {
        String str = "ID No.: " + idNum + "\nZone: " + zone + "\nCharge: £" + getCharge() + "\nWeight: " + weight + "kg";
        return str;
    }

}
