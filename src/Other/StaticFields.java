/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Other;

/**
 *
 * @author Kavindya
 */
public  class StaticFields {
private static String date;
    private static String username;
    private static String user = "Administrator";

    /**
     * @return the date
     */
    public static String getDate() {
        return date;
    }

    /**
     * @param aDate the date to set
     */
    public static void setDate(String aDate) {
        date = aDate;
    }

    /**
     * @return the username
     */
    public static String getUsername() {
        return username;
    }

    /**
     * @param aUsername the username to set
     */
    public static void setUsername(String aUsername) {
        username = aUsername;
    }

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(String aUser) {
        user = aUser;
    }
}
