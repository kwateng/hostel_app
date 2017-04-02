/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

/**
 *
 * @author Kavindya
 */
public class HallStudentAllocations {
private int room_number;
private String stident_id;
private String student_name;
private String department;
private String semester;
private String gender;

    /**
     * @return the room_number
     */
    public int getRoom_number() {
        return room_number;
    }

    /**
     * @param room_number the room_number to set
     */
    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    /**
     * @return the stident_id
     */
    public String getStident_id() {
        return stident_id;
    }

    /**
     * @param stident_id the stident_id to set
     */
    public void setStident_id(String stident_id) {
        this.stident_id = stident_id;
    }

    /**
     * @return the student_name
     */
    public String getStudent_name() {
        return student_name;
    }

    /**
     * @param student_name the student_name to set
     */
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

}
