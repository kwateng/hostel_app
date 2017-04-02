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
public class Employee_worksIn{
    private String hallId;
    private String employId;
    private String Date;

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

    
    /**
     * @return the employId
     */
    public String getEmployId() {
        return employId;
    }

    /**
     * @param employId the employId to set
     */
    public void setEmployId(String employId) {
        this.employId = employId;
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    

}
