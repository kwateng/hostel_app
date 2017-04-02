/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Admin;
//Logon data
import java.io.Serializable;

/**
 *
 * @author Kavindya
 */
public class LoginData{
    private String username;
    private String password;
    private boolean isAdmin;
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

   


}
