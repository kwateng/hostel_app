/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;

/**
 *
 * @author Kavindya
 */
public class Employee extends Person implements Serializable {
    private String employID;
    private String position;
    private String salary;
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
     * @return the salary
     */
    public String getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

}
