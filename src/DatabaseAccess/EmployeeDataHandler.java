/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseAccess;

import entities.Employee;
import entities.Employee_worksIn;
import entities.Student;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Kavindya
 */
public class EmployeeDataHandler {

    private Connection connection;
    private final String connectionURL = "jdbc:mysql://localhost:3306/universityOfSumanadasa_hallManagement";
    ResultSet rs = null;
    PreparedStatement psmnt = null;
    private FileInputStream fis;
    private Student studentinfo;


    private void createDBConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, "root", "");
    }

    private void terminateDBConnection() throws SQLException {
        connection.close();
    }

    

    public void addEmploy(Employee info) {//<<add employ>>
        try {
            createDBConnection();
            Image image = info.getiImage().getImage();
            RenderedImage rendered = null;

            if (image instanceof RenderedImage) {
                rendered = (RenderedImage) image;
            } else {
                BufferedImage buffered = new BufferedImage(info.getiImage().getIconWidth(), info.getiImage().getIconHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g = buffered.createGraphics();
                g.drawImage(image, 0, 0, null);
                g.dispose();
                rendered = buffered;
            }
            ImageIO.write(rendered, "JPEG", new File("image.jpg"));
            File image1 = new File("image.jpg");
            psmnt = connection.prepareStatement("INSERT INTO employee (employee_id,employee_first_name,employee_last_name,employee_name_withInitials,password,salary,position,address,dateOfBirth,gender,telephone_mobile,telephone_home,image)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            psmnt.setString(1, info.getEmployID());
            psmnt.setString(2, info.getFirstName());
            psmnt.setString(3, info.getLastName());
            psmnt.setString(4, info.getNameWithInitials());
            psmnt.setString(5, info.getPassword());
            psmnt.setString(6, info.getSalary());
            psmnt.setString(7, info.getPosition());
            psmnt.setString(8, info.getAddress());
            psmnt.setString(9, info.getDateOfBirth());
            psmnt.setString(10, info.getGender());
            psmnt.setString(11, info.getPersonalMobile());
            psmnt.setString(12, info.getHomeLine());
            //psmnt.setString(14,info.getRegDate());

            fis = new FileInputStream(image1);
            psmnt.setBinaryStream(13, fis, fis.available());
            int s = psmnt.executeUpdate();

            if (s > 0) {
                System.out.println("Uploaded successfully !");
            } else {
                System.out.println("unsucessfull to upload in adding employee");
            }
            psmnt.close();
        } catch (Exception ex) {
            System.out.println("Found some error : adding employee " + ex);
        }

        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }// end of the add employ..

    public Employee searchEmploy(String index) {//<<<<<<<<<<<<search employ return employ object>>>>>>>>>>>>>>>>>>>>
        Employee employInfo = null;
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT * from employee WHERE employee_id=?");
            psmnt.setString(1, index);
            rs = psmnt.executeQuery();

            while (rs.next()) {
                employInfo = new Employee();
                employInfo.setEmployID(index);
                employInfo.setFirstName(rs.getString(2));
                employInfo.setLastName(rs.getString(3));
                employInfo.setNameWithInitials(rs.getString(4));
                employInfo.setPassword(rs.getString(5));
                employInfo.setSalary(rs.getString(6));
                employInfo.setPosition(rs.getString(7));
                employInfo.setAddress(rs.getString(8));
                employInfo.setDateOfBirth(rs.getString(9));
                employInfo.setGender(rs.getString(10));
                employInfo.setPersonalMobile(rs.getString(11));
                employInfo.setHomeLine(rs.getString(12));
                //employInfo.setRegDate(rs.getString(14));
                InputStream is = rs.getBinaryStream("image");
                BufferedImage image = ImageIO.read(is); //the image is read in
                ImageIO.write(image, "jpg", new FileOutputStream("recived.jpg"));
                ImageIcon icon = new ImageIcon("recived.jpg");

                employInfo.setiImage(icon);
            }
        } catch (Exception ex) {
            System.out.println("Exception in showing employees" + ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employInfo;
    }// end of the search employ....

    public boolean removeEmploy(String index) {//<<<<<<<< remove employ from database >>>>>>>>>
        boolean status = false;
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT * from employee WHERE employee_id=?");
            psmnt.setString(1, index);
            rs = psmnt.executeQuery();
            if (rs != null) {
                java.sql.Statement statement = connection.createStatement();
                statement.executeUpdate("delete from employee where employee_id='" + index + "'");
                status = true;

            } else {
                status = false;
            }
        } catch (Exception ex) {
            System.out.println("Exception in removing employee" + ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }// end of the deleat student form database...

    public void editEmployInfo(Employee employ) {// <<<<<<<<<<<<<< edit employ info >>>>>>>>>>>>>>>>>>>>>
        try {
            createDBConnection();

            Image image = employ.getiImage().getImage();
            RenderedImage rendered = null;
            if (image instanceof RenderedImage) {
                rendered = (RenderedImage) image;
            } else {
                BufferedImage buffered = new BufferedImage(employ.getiImage().getIconWidth(), employ.getiImage().getIconHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g = buffered.createGraphics();
                g.drawImage(image, 0, 0, null);
                g.dispose();
                rendered = buffered;
            }
            ImageIO.write(rendered, "JPEG", new File("image.jpg"));
            File image1 = new File("image.jpg");

            psmnt = connection.prepareStatement("update employee SET "
                    + "employee_first_name = ?, employee_last_name = ?, employee_name_withInitials = ?, "
                    + "salary = ?, position= ?, address = ?,dateOfBirth = ?,gender = ?,"
                    + "telephone_mobile = ?,telephone_home = ?,image=? where employee_id = ?");
            psmnt.setString(1, employ.getFirstName());
            psmnt.setString(2, employ.getLastName());
            psmnt.setString(3, employ.getNameWithInitials());
            psmnt.setString(4, employ.getSalary());
            psmnt.setString(5, employ.getPosition());
            psmnt.setString(6, employ.getAddress());
            psmnt.setString(7, employ.getDateOfBirth());
            psmnt.setString(8, employ.getGender());
            psmnt.setString(9, employ.getPersonalMobile());
            psmnt.setString(10, employ.getHomeLine());
            fis = new FileInputStream(image1);

            psmnt.setBinaryStream(11, fis, fis.available());

            psmnt.setString(12, employ.getEmployID());

            int s = psmnt.executeUpdate();

            if (s > 0) {
                System.out.println("Uploaded successfully !");
            } else {
                System.out.println("unsucessfull to upload in adding employee");
            }
            psmnt.close();

        } catch (Exception ex) {
            System.out.println("Found some error : adding employee " + ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// end of the edit employ..

    public boolean passwordMatches(String employID, String previousPassword) {//<<<<<<<<<<<<<<< check password correct >>>>>>>>>>>>
        boolean matched = false;
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT password from employee WHERE employee_id=?");
            psmnt.setString(1, employID);
            rs = psmnt.executeQuery();
            if (rs != null) {

                while (rs.next()) {
                    if (previousPassword.equals(rs.getString(1))) {
                        matched = true;
                    }
                }

            } else {
                matched = false;
            }
        } catch (Exception ex) {
            System.out.println("check for match password correction failed ;" + ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return matched;
    }// password correction check is end

    public void changeEmployPassword(String employID, String password) {// <<<<<<<<<<<<<<<<<<<<<<change password >>>>>>>>>>>>>>>>>>
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("UPDATE (SElECT employee_id, password FROM employee WHERE employee_id = ?)as a SET a.password = ? WHERE employee_id= ?");
            psmnt.setString(1, employID);
            psmnt.setString(2, password);
            psmnt.setString(3, employID);

            int s = psmnt.executeUpdate();

            if (s > 0) {
                System.out.println("password changed");
            } else {
                System.out.println("couldn't change password");
            }
            psmnt.close();

        } catch (Exception ex) {
            System.out.println("password change failed ;" + ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Employee_worksIn> EmployRoomDetails(String employID) {
        ArrayList<Employee_worksIn> employWorksIn = new ArrayList<Employee_worksIn>();
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT * from works_in WHERE employee_id=?");
            psmnt.setString(1, employID);
            rs = psmnt.executeQuery();

            Employee_worksIn wi = new Employee_worksIn();
            while (rs.next()) {
                wi.setEmployId(employID);
                wi.setHallId(rs.getString(2));
                wi.setDate(rs.getString(3));

                employWorksIn.add(wi);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employWorksIn;
    }

    public ArrayList<Employee> viewEmployees(String ss) {
       ArrayList<Employee> temp=new  ArrayList<Employee>();
       Employee  employInfo;
       try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT * from employee WHERE position=?");
            psmnt.setString(1,ss);
            rs = psmnt.executeQuery();
            while (rs.next()) {
                employInfo = new Employee();
                employInfo.setEmployID(rs.getString(1));
                employInfo.setFirstName(rs.getString(2));
                temp.add(employInfo);
            }
        } catch (Exception ex) {
            System.out.println("Exception in showing employees" + ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       return temp;
    }

    public ArrayList<Employee> viewEmployeeset() {
        ArrayList<Employee> temp=new  ArrayList<Employee>();
       Employee  employInfo;
       try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT * from employee");
            rs = psmnt.executeQuery();
            while (rs.next()) {
                System.out.println("foundind");
                employInfo = new Employee();
                employInfo.setEmployID(rs.getString(1));
                employInfo.setFirstName(rs.getString(2));
                temp.add(employInfo);
            }
        } catch (Exception ex) {
            System.out.println("Exception in showing employees" + ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
       return temp;
    }
}
