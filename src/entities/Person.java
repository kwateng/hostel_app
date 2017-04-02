/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author Kavindya
 */
public class Person implements Serializable {
private String firstName;
private String lastName;
private String gender;
private String address;
private String personalMobile;
private String homeLine;
private String dateOfBirth;
private String nameWithInitials;
private ImageIcon iImage;
private String password;

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

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the personalMobile
     */
    public String getPersonalMobile() {
        return personalMobile;
    }

    /**
     * @param personalMobile the personalMobile to set
     */
    public void setPersonalMobile(String personalMobile) {
        this.personalMobile = personalMobile;
    }

    /**
     * @return the homeLine
     */
    public String getHomeLine() {
        return homeLine;
    }

    /**
     * @param homeLine the homeLine to set
     */
    public void setHomeLine(String homeLine) {
        this.homeLine = homeLine;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the nameWithInitials
     */
    public String getNameWithInitials() {
        return nameWithInitials;
    }

    /**
     * @param nameWithInitials the nameWithInitials to set
     */
    public void setNameWithInitials(String nameWithInitials) {
        this.nameWithInitials = nameWithInitials;
    }

    /**
     * @return the iImage
     */
    public ImageIcon getiImage() {
        return iImage;
    }

    /**
     * @param iImage the iImage to set
     */
    public void setiImage(ImageIcon iImage) {
        this.iImage = iImage;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
