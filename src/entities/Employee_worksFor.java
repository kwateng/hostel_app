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
public class Employee_worksFor implements Serializable {
        private String employee_id;
        private String manager_id;
        private String date;

    /**
     * @return the employee_id
     */
    public String getEmployee_id() {
        return employee_id;
    }

    /**
     * @param employee_id the employee_id to set
     */
    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    /**
     * @return the manager_id
     */
    public String getManager_id() {
        return manager_id;
    }

    /**
     * @param manager_id the manager_id to set
     */
    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
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
}
