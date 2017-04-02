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
public class Hall implements Serializable{

    private String hallID;
    private String hallName;
    private int Capacity;
    private String Type;
    private int noOfRooms;

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
     * @return the hallName
     */
    public String getHallName() {
        return hallName;
    }

    /**
     * @param hallName the hallName to set
     */
    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    /**
     * @return the Capacity
     */
    public int getCapacity() {
        return Capacity;
    }

    /**
     * @param Capacity the Capacity to set
     */
    public void setCapacity(int Capacity) {
        this.Capacity = Capacity;
    }

    /**
     * @return the Type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param Type the Type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return the noOfRooms
     */
    public int getNoOfRooms() {
        return noOfRooms;
    }

    /**
     * @param noOfRooms the noOfRooms to set
     */
    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }
}
