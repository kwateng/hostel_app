/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

/**
 *
 * @author jayendra
 */
public class allocateEmployToHall {
    private String employID;
    private String position;
    private String date;
    private String hallId;

    /**
     * @return the employID
     */
    public String getEmployID() {
        return employID;
    }

    /**
     * @param employID the employID to set
     */
    public void setEmployID(String employID) {
        this.employID = employID;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the hallId
     */
    public String getHallId() {
        return hallId;
    }

    /**
     * @param hallId the hallId to set
     */
    public void setHallId(String hallId) {
        this.hallId = hallId;
    }
}
