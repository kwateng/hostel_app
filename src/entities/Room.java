/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author jayendra
 */
public class Room implements Serializable{
    private int roomNumber;
    private String type;
    private double cost;
    private String hallID;
    private boolean full;

    /**
     * @return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    public Room() {
        this.full =false;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return the hallID
     */
    public String getHallID() {
        return hallID;
    }

    /**
     * @param hallID the hallID to set
     */
    public void setHallID(String hallID) {
        this.hallID = hallID;
    }

    /**
     * @return the full
     */
    public boolean isFull() {
        return full;
    }

    /**
     * @param full the full to set
     */
    public void setFull(boolean full) {
        this.full = full;
    }
    
    
    
}
