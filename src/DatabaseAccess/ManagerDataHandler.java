/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseAccess;

import Admin.Admin;
import Admin.LoginData;
import Models.HallManagerView;
import Models.RoomAvailability;
import Models.allocateEmployToHall;
import entities.Employee;
import entities.Employee_worksIn;
import entities.Student;
import entities.Student_livesIn;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
//import relations.ViewPayments;
//import relations.studentHallAranngements;

/**
 *
 * @author Kavindya
 */
public class ManagerDataHandler {

    private Connection connection;
    private final String connectionURL = "jdbc:mysql://localhost:3306/universityOfSumanadasa_hallManagement";
    ResultSet rs = null;
    PreparedStatement psmnt = null;
    private FileInputStream fis;

    private void createDBConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, "root", "");
    }

    private void terminateDBConnection() throws SQLException {
        connection.close();
    }

    public ArrayList<Student> getStudentInfo() {
        ArrayList<Student> slist = new ArrayList<Student>();

        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT * from student ");
            //psmnt.setString(1,index);
            rs = psmnt.executeQuery();
            while (rs.next()) {

                Student temp = new Student();

                temp.setIndexNo(rs.getString(1));
                temp.setFirstName(rs.getString(2));
                temp.setLastName(rs.getString(3));
                temp.setNameWithInitials(rs.getString(4));
                temp.setDepartment(rs.getString(5));
                temp.setSemester(rs.getString(6));
                temp.setAcademicYear(rs.getString(7));
                temp.setPersonalMobile(rs.getString(8));
                temp.setHomeLine(rs.getString(9));
                temp.setDateOfBirth(rs.getString(10));
                temp.setPassword(rs.getString(11));
                temp.setGender(rs.getString(13));
                temp.setRegDate(rs.getString(14));

                slist.add(temp);

                /*InputStream is = rs.getBinaryStream("image");
                BufferedImage image = ImageIO.read(is); //the image is read in
                ImageIO.write(image, "jpg", new FileOutputStream("recived.jpg"));
                ImageIcon       icon=new ImageIcon("recived.jpg");
                temp.setiImage(icon);*/
            }
            psmnt.close();
            rs.close();
        } catch (Exception ex) {
            //System.out.println("Exception in showing students"+ex);
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
        try {
            terminateDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return slist;
    }

    public ArrayList<Employee> getEmployInfo() {
        ArrayList<Employee> Elist = new ArrayList<Employee>();

        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT * from employee ");
            //psmnt.setString(1,index);
            rs = psmnt.executeQuery();
            while (rs.next()) {

                Employee temp = new Employee();

                temp.setEmployID(rs.getString(1));
                temp.setFirstName(rs.getString(2));
                temp.setLastName(rs.getString(3));
                temp.setNameWithInitials(rs.getString(4));
                temp.setPassword(rs.getString(5));
                temp.setSalary(rs.getString(6));
                temp.setPosition(rs.getString(7));
                temp.setAddress(rs.getString(8));
                temp.setDateOfBirth(rs.getString(9));
                temp.setGender(rs.getString(10));
                temp.setPersonalMobile(rs.getString(11));
                temp.setHomeLine(rs.getString(12));
                //temp.setRegDate(rs.getString(14));

                Elist.add(temp);

                /*InputStream is = rs.getBinaryStream("image");
                BufferedImage image = ImageIO.read(is); //the image is read in
                ImageIO.write(image, "jpg", new FileOutputStream("recived.jpg"));
                ImageIcon       icon=new ImageIcon("recived.jpg");
                temp.setiImage(icon);*/
            }
            psmnt.close();
            rs.close();
        } catch (Exception ex) {
            //System.out.println("Exception in showing students"+ex);
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
        try {
            terminateDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Elist;
    }

    public ArrayList<allocateEmployToHall> getHallAllocationDetails(String text) {
        ArrayList<allocateEmployToHall> aEH = new ArrayList<allocateEmployToHall>();

        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT employee.employee_id,employee.position,work_date from employee employee, works_in where employee.employee_id = works_in.employee_id and works_in.hall_id =?");
            psmnt.setString(1, text);
            rs = psmnt.executeQuery();
            while (rs.next()) {

                allocateEmployToHall temp = new allocateEmployToHall();

                temp.setEmployID(rs.getString(1));
                temp.setPosition(rs.getString(2));
                temp.setDate(rs.getString(3));
                temp.setHallId(text);

                aEH.add(temp);

            }
            psmnt.close();
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Hall Id is incorrect  or " + ex);
        }
        try {
            terminateDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aEH;
    }

    public ArrayList<allocateEmployToHall> getHallAllocationDetails(String text, String date) {
        ArrayList<allocateEmployToHall> aEH = new ArrayList<allocateEmployToHall>();

        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT employee.employee_id,employee.position,work_date from employee employee, works_in where employee.employee_id = works_in.employee_id and works_in.hall_id =? and work_date = ?");
            psmnt.setString(1, text);
            psmnt.setString(2, date);
            rs = psmnt.executeQuery();
            while (rs.next()) {

                allocateEmployToHall temp = new allocateEmployToHall();

                temp.setEmployID(rs.getString(1));
                temp.setPosition(rs.getString(2));
                temp.setDate(rs.getString(3));
                temp.setHallId(text);

                aEH.add(temp);

            }
            psmnt.close();
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
        try {
            terminateDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aEH;
    }

    public ArrayList<HallManagerView> hallManagerDetails(String text) {
        ArrayList<HallManagerView> hMV = new ArrayList<HallManagerView>();

        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT employee.employee_id,employee.position,work_date from employee employee, works_for where employee.employee_id = works_for.worker_id and works_for.manager_id =?");
            psmnt.setString(1, text);
            System.out.println("DnePP" + text);
            rs = psmnt.executeQuery();
            while (rs.next()) {

                HallManagerView temp = new HallManagerView();

                temp.setEmployID(rs.getString(1));
                temp.setPosition(rs.getString(2));

                temp.setDate(rs.getString(3));
                //temp.setHallID(rs.getString(4));
                temp.setManagerId(text);

                hMV.add(temp);

            }
            psmnt.close();
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Manager Id is incorrect  or " + ex);
        }
        try {
            terminateDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hMV;
    }

    public ArrayList<HallManagerView> hallManagerDetails(String text, String date) {
        ArrayList<HallManagerView> hMV = new ArrayList<HallManagerView>();

        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT employee.employee_id,employee.position,work_date from employee employee, works_for where employee.employee_id = works_for.worker_id and works_for.manager_id =? and works_for.work_date =?");
            psmnt.setString(1, text);
            psmnt.setString(2, date);
            rs = psmnt.executeQuery();
            while (rs.next()) {

                HallManagerView temp = new HallManagerView();

                temp.setEmployID(rs.getString(1));
                temp.setPosition(rs.getString(2));
                temp.setDate(rs.getString(3));
                //temp.setHallID(rs.getString(4));
                temp.setManagerId(text);

                hMV.add(temp);

            }
            psmnt.close();
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Manager Id is incorrect  or " + ex);
        }
        try {
            terminateDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hMV;
    }

    public void setWorksIn(Employee_worksIn ob) throws SQLException {
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("INSERT INTO works_in(hall_id,employee_id,work_date)" + "values(?,?,?)");
            psmnt.setString(1, ob.getHallId());
            psmnt.setString(2, ob.getEmployId());
            java.sql.Date date = getCurrentJavaSqlDate();
            System.out.println(date);
            psmnt.setDate(3, date);
            psmnt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Adding hall" + ex);
        }
        connection.close();
    }

    public static java.sql.Date getCurrentJavaSqlDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    public void addAdmin(Admin admin) throws SQLException {
        try {

            createDBConnection();
            psmnt = connection.prepareStatement("insert into admin(name,username,mobile,email,gender,password) " + "values(?,?,?,?,?,?)");
            psmnt.setString(1, admin.getName());
            psmnt.setString(2, admin.getUsername());
            psmnt.setString(3, admin.getMobileNo());
            psmnt.setString(4, admin.getEmail());
            psmnt.setString(5, admin.getGender());
            psmnt.setString(6, admin.getPassword());
            int s = psmnt.executeUpdate();
            if (s > 0) {
                System.out.println("Uploaded successfully !");
            } else {
                System.out.println("unsucessfull to upload");
            }
            psmnt.close();
            PreparedStatement pt = connection.prepareStatement("insert into login(name,pass,isadmin)" + "values(?,?,?)");
            pt.setString(1, admin.getUsername());
            pt.setString(2, admin.getPassword());
            pt.setBoolean(3, true);
            pt.executeUpdate();
            pt.close();
        } // catch if found any exception during rum time.
        catch (Exception ex) {
            System.out.println("Found some error in adding a admin: " + ex);
        } finally {
// close all the connections.
            connection.close();

        }

    }

    public boolean getLogin(String username, String password) {
        LoginData login = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "");
            psmnt = connection.prepareStatement("SELECT*  from login WHERE name=? and pass=?");
            psmnt.setString(1, username);
            psmnt.setString(2, password);
            rs = psmnt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } // catch if found any exception during rum time.
        catch (Exception ex) {
            System.out.println("Exception in getting login data" + ex);
// create a file object for image by specifying full path of image as parameter.
        }

        return false;
    }

    public void addStudentToRoom(Student_livesIn temp) throws SQLException {
        Student_livesIn temp1 = temp;
        try {
            // Load JDBC driver "com.mysql.jdbc.Driver"
            boolean full = false;
            int count = 0;
            createDBConnection();
            psmnt = connection.prepareStatement("INSERT INTO lives_in (student_id,hall_id,room_number,balance,academic_year,isPaid)" + "values(?,?,?,?,?,?)");
            psmnt.setString(1, temp.getStudentID());
            psmnt.setString(2, temp.getHallID());
            psmnt.setInt(3, temp.getRoomNumber());
            psmnt.setDouble(4, temp.getDueAmount());
            psmnt.setString(5, temp.getAcademicYear());
            psmnt.setBoolean(6, false);
            psmnt.executeUpdate();
            psmnt = connection.prepareStatement("SELECT count(*) from lives_in WHERE hall_id=? and room_number=? ");
            psmnt.setString(1, temp.getHallID());
            psmnt.setInt(2, temp.getRoomNumber());
            rs = psmnt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    full = false;
                    count = 1;
                }
                if (rs.getInt(1) == 2) {
                    full = true;
                    count = 2;
                }

            }
            boolean isdouble = false;
            psmnt = connection.prepareStatement("SELECT * from room WHERE hall_id=? and room_number=? ");
            psmnt.setString(1, temp.getHallID());
            psmnt.setInt(2, temp.getRoomNumber());
            rs = psmnt.executeQuery();
            while (rs.next()) {
                if (rs.getString(2).equalsIgnoreCase("single")) {
                    isdouble = false;
                }
                if (rs.getString(2).equalsIgnoreCase("double")) {
                    isdouble = true;
                }
            }
            if (count == 1 && !isdouble) {
                psmnt = connection.prepareStatement("Update room SET isFull=? WHERE hall_id=? and room_number=? ");
                psmnt.setBoolean(1, true);
                psmnt.setString(2, temp.getHallID());
                psmnt.setInt(3, temp.getRoomNumber());
                psmnt.executeUpdate();
            }
            if (count == 2 && isdouble) {
                psmnt = connection.prepareStatement("Update room SET isFull=? WHERE hall_id=? and room_number=? ");
                psmnt.setBoolean(1, true);
                psmnt.setString(2, temp.getHallID());
                psmnt.setInt(3, temp.getRoomNumber());
                psmnt.executeUpdate();
            }



        } catch (Exception ex) {
            System.out.println("Exception in add student to room" + ex);
        } finally {
            connection.close();
        }
//        try {
//            boolean full = false;
//            DecimalFormat df = new DecimalFormat("0.0");
//            java.sql.Statement statement = connection.createStatement();
//            try {
//                createDBConnection();
//                psmnt = connection.prepareStatement("select hall_id , room_number,isFull,room_type,members FROM room WHERE hall_id = ? and room_number = ?");
//                psmnt.setString(1, temp.getHallID());
//                psmnt.setInt(2, Integer.parseInt(temp.getRoomNumber()));
//                rs = psmnt.executeQuery();
//                if (rs != null) {
//
//                    if (!rs.getBoolean(3)) {
//
//                        //if (rs.getString(4).equals("Single")) {
//                        //full = true;
//                        statement.executeUpdate("INSERT INTO lives_in VALUES ('" + temp.getStudentID() + "','" + temp.getHallID() + "','" + Integer.parseInt(temp.getRoomNumber()) + "'," + df.format(temp.getDueAmount()) + ",'" + temp.getAcademicYear() + "," + true + ")");
//                        //statement.executeUpdate("update room SET isFull = " + true + ", members = " + 1 + ")");
//
//                        //} else if (rs.getString(4).equals("Double")) {
//                        // if (rs.getInt(5) == 0) {
//
//                        //statement.executeUpdate("INSERT INTO lives_in VALUES ('" + temp.getStudentID() + "','" + temp.getHallID() + "','" + Integer.parseInt(temp.getRoomNumber()) + "'," + df.format(temp.getDueAmount()) + ",'" + temp.getAcademicYear() + "," + true + ")");
//                        //statement.executeUpdate("update room SET isFull = " + false + ", members = " + 1 + ")");
//                        //} else if (rs.getInt(5) == 1) {
//
//                        // statement.executeUpdate("INSERT INTO lives_in VALUES ('" + temp.getStudentID() + "','" + temp.getHallID() + "','" + Integer.parseInt(temp.getRoomNumber()) + "'," + df.format(temp.getDueAmount()) + ",'" + temp.getAcademicYear() + "," + true + ")");
//                        //statement.executeUpdate("update room SET isFull = " + true + ", members = " + 2 + ")");
//                        //}
//                        //}
//
//                    } else {
//                        JOptionPane.showMessageDialog(new JFrame(), "room is full cannot add student to that room");
//                    }
//
//                }
//                JOptionPane.showMessageDialog(new JFrame(), "There is no such room");
//
//                psmnt.close();
//                rs.close();
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(new JFrame(), ex);
//            }
//            try {
//                terminateDBConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public ArrayList<Student_livesIn> getStudentsLivesInDetails() {
        ArrayList<Student_livesIn> sLivesIn = new ArrayList<Student_livesIn>();

        try {
            createDBConnection();
            psmnt = connection.prepareStatement("select s.student_id,l.hall_id,l.room_number,l.balance,l.academic_year FROM student as s LEFT OUTER JOIN lives_in as l ON s.student_id = l.student_id");
            //psmnt.setString(1,index);
            rs = psmnt.executeQuery();
            while (rs.next()) {

                Student_livesIn temp = new Student_livesIn();

                temp.setStudentID(rs.getString(1));
                temp.setHallID(rs.getString(2));
                temp.setRoomNumber(rs.getInt(3));
                temp.setDueAmount(rs.getDouble(4));
                temp.setAcademicYear(rs.getString(5));

                sLivesIn.add(temp);
            }
            psmnt.close();
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
        try {
            terminateDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sLivesIn;
    }

    public ArrayList<RoomAvailability> getRoomAvailabilityList(String hallId) {
        ArrayList<RoomAvailability> roomList = new ArrayList<RoomAvailability>();

        try {
            createDBConnection();
            psmnt = connection.prepareStatement("select * FROM room WHERE hall_id = ?");
            psmnt.setString(1, hallId);
            rs = psmnt.executeQuery();
            while (rs.next()) {

                RoomAvailability temp = new RoomAvailability();

                temp.setRoomNumber(Integer.toString(rs.getInt(1)));
                temp.setType(rs.getString(2));
                temp.setCost(rs.getString(3));
                temp.setHallId(hallId);

                temp.setFull(new Boolean(rs.getBoolean(5)).toString());


                roomList.add(temp);
            }
            psmnt.close();
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
        try {
            terminateDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return roomList;
    }

    public void UpdateStudentToRoom(Student_livesIn temp) throws SQLException {
        Student_livesIn temp1 = temp;

        try {
            // Load JDBC driver "com.mysql.jdbc.Driver"
            boolean full = false;
            int count = 0;
            createDBConnection();
            psmnt = connection.prepareStatement("Update lives_in SET hall_id=?,room_number=?,balance=? WHERE student_id=?");
            psmnt.setString(1, temp.getHallID());
            psmnt.setInt(2, temp.getRoomNumber());
            psmnt.setDouble(3, temp.getDueAmount());
            psmnt.setString(4, temp.getStudentID());
            psmnt.executeUpdate();
            psmnt = connection.prepareStatement("SELECT count(*) from lives_in WHERE hall_id=? and room_number=? ");
            psmnt.setString(1, temp.getHallID());
            psmnt.setInt(2, temp.getRoomNumber());
            rs = psmnt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    full = false;
                    count = 1;
                }
                if (rs.getInt(1) == 2) {
                    full = true;
                    count = 2;
                }

            }
            boolean isdouble = false;
            psmnt = connection.prepareStatement("SELECT * from room WHERE hall_id=? and room_number=? ");
            psmnt.setString(1, temp.getHallID());
            psmnt.setInt(2, temp.getRoomNumber());
            rs = psmnt.executeQuery();
            while (rs.next()) {
                if (rs.getString(2).equalsIgnoreCase("single")) {
                    isdouble = false;
                }
                if (rs.getString(2).equalsIgnoreCase("double")) {
                    isdouble = true;
                }
            }
            if (count == 1 && !isdouble) {
                psmnt = connection.prepareStatement("Update room SET isFull=? WHERE hall_id=? and room_number=? ");
                psmnt.setBoolean(1, true);
                psmnt.setString(2, temp.getHallID());
                psmnt.setInt(3, temp.getRoomNumber());
                psmnt.executeUpdate();
            }
            if (count == 2 && isdouble) {
                psmnt = connection.prepareStatement("Update room SET isFull=? WHERE hall_id=? and room_number=? ");
                psmnt.setBoolean(1, true);
                psmnt.setString(2, temp.getHallID());
                psmnt.setInt(3, temp.getRoomNumber());
                psmnt.executeUpdate();
            }



        } catch (Exception ex) {
            System.out.println("Exception in add student to room" + ex);
        } finally {
            connection.close();
        }
//        try {
//            boolean full = false;
//            //DecimalFormat df = new DecimalFormat("0.0");
//            java.sql.Statement statement = connection.createStatement();
//            try {
//                createDBConnection();
////                psmnt = connection.prepareStatement("delete FROM lives_in WHERE student_id = ? and hall_id =?");
////                psmnt.setString(1, temp.getStudentID());
////                psmnt.setString(2, temp.getHallID());
////                psmnt.executeUpdate();
//
//                psmnt = connection.prepareStatement("select hall_id , room_number,isFull,room_type,members FROM room WHERE hall_id = ? and room_number = ?");
//                psmnt.setString(1, temp.getHallID());
//                psmnt.setInt(2, temp.getRoomNumber());
//                psmnt.executeUpdate();
//
//                rs = psmnt.executeQuery();
//                if (rs != null) {
//
//                    if (!rs.getBoolean(3)) {
//
//                        //if (rs.getString(4).equals("Single")) {
//                        //full = true;
//                        statement.executeUpdate("UPDATE lives_in SET student_id='" + temp.getStudentID() + "',hall_id = '" + temp.getHallID() + "',room_number = '" + temp.getRoomNumber() + "',balance = " + temp.getDueAmount() + ",academic_year= '" + temp.getAcademicYear() + "',isPaid = " + true + " WHERE student_id = '"+temp.getStudentID()+"')");
//                        //statement.executeUpdate("update room SET isFull = " + true + ", members = " + 1 + ")");
//
//                        //} else if (rs.getString(4).equals("Double")) {
//                        // if (rs.getInt(5) == 0) {
//
//                        //statement.executeUpdate("INSERT INTO lives_in VALUES ('" + temp.getStudentID() + "','" + temp.getHallID() + "','" + Integer.parseInt(temp.getRoomNumber()) + "'," + df.format(temp.getDueAmount()) + ",'" + temp.getAcademicYear() + "," + true + ")");
//                        //statement.executeUpdate("update room SET isFull = " + false + ", members = " + 1 + ")");
//                        //} else if (rs.getInt(5) == 1) {
//
//                        // statement.executeUpdate("INSERT INTO lives_in VALUES ('" + temp.getStudentID() + "','" + temp.getHallID() + "','" + Integer.parseInt(temp.getRoomNumber()) + "'," + df.format(temp.getDueAmount()) + ",'" + temp.getAcademicYear() + "," + true + ")");
//                        //statement.executeUpdate("update room SET isFull = " + true + ", members = " + 2 + ")");
//                        //}
//                        //}
//
//                    } else {
//                        JOptionPane.showMessageDialog(new JFrame(), "room is full cannot add student to that room");
//                    }
//
//                }
//                JOptionPane.showMessageDialog(new JFrame(), "There is no such room");
//
//                psmnt.close();
//                rs.close();
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(new JFrame(), ex);
//            }
//            try {
//                terminateDBConnection();
//            } catch (SQLException ex) {
//                Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public void removeStudentAllocation(String text) {
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("DELETE from lives_in where student_id=?");
            psmnt.setString(1, text);

            psmnt.close();
            rs.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(new JFrame(), ex);
        }
        try {
            terminateDBConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerDataHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setInitialAdminLogin(String username, String password) throws SQLException {
        try {
            createDBConnection();
            PreparedStatement pty = connection.prepareStatement("DELETE from login");
            pty.executeUpdate();
            PreparedStatement pt = connection.prepareStatement("insert into login(name,pass,isadmin)" + "values(?,?,?)");
            pt.setString(1, username);
            pt.setString(2, password);
            pt.setBoolean(3, true);
            pt.executeUpdate();
            System.out.println("Registred");
        } // catch if found any exception during rum time.
        catch (Exception ex) {
            System.out.println("Exception in Setting login data" + ex);
// create a file object for image by specifying full path of image as parameter.
        }
        connection.close();
    }

    public void upDateRoom(String searchStudentIDInRealocations) throws SQLException {
        try {
            System.out.println(searchStudentIDInRealocations);
            createDBConnection();
            String hall = null;
            int room = 0;
            psmnt = connection.prepareStatement("SELECT hall_id,room_number FROM lives_in WHERE student_id=?");
            psmnt.setString(1,searchStudentIDInRealocations);
            
            rs = psmnt.executeQuery();
            while(rs.next())
            {
                System.out.println("Found");
                hall=rs.getString(1);
                room=rs.getInt(2);
                System.out.println(hall+room);
            }
            psmnt = connection.prepareStatement("Update room SET isFull=? WHERE hall_id=? and room_number=?");
            psmnt.setBoolean(1, true);
            psmnt.setString(2, hall);
            psmnt.setInt(3, room);
            psmnt.executeUpdate();

        } // catch if found any exception during rum time.
        catch (Exception ex) {
            System.out.println("Exception in update room" + ex);
// create a file object for image by specifying full path of image as parameter.
        }
        connection.close();
    }
}
