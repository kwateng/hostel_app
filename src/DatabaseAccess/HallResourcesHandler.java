/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseAccess;

import Models.AllDuePayments;
import Models.HallStudentAllocations;
import entities.Hall;
import entities.Payment;
import entities.Room;
import entities.Student;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kavindya
 */
public class HallResourcesHandler {

    private Connection connection;
    private final String connectionURL = "jdbc:mysql://localhost:3306/universityOfSumanadasa_hallManagement";
    ResultSet rs = null;
    PreparedStatement psmnt = null;
    private FileInputStream fis;
    private Student studentinfo;

    public void addRooms(ArrayList<Room> room) throws SQLException {
        try {
            createDBConnection();
            for (int i = 0; i < room.size(); i++) {
                psmnt = connection.prepareStatement("INSERT INTO room ( room_number,room_type,cost,hall_id,isFull)" + "values(?,?,?,?,?)");
                psmnt.setInt(1, room.get(i).getRoomNumber());
                psmnt.setString(2, room.get(i).getType());
                psmnt.setDouble(3, room.get(i).getCost());
                psmnt.setString(4, room.get(i).getHallID());
                psmnt.setBoolean(5, room.get(i).isFull());
                psmnt.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Adding rooms" + ex);
        }
        connection.close();
    }

    private void createDBConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, "root", "");
    }

    public boolean addHall(Hall hall) {
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("INSERT INTO hall ( hall_id,hall_name,capacity,hall_type,numberOfRooms)" + "values(?,?,?,?,?)");
            psmnt.setString(1, hall.getHallID());
            psmnt.setString(2, hall.getHallName());
            psmnt.setInt(3, hall.getCapacity());
            psmnt.setString(4, hall.getType());
            psmnt.setInt(5, hall.getNoOfRooms());
            psmnt.executeUpdate();

        } catch (Exception ex) {
            System.out.println("Adding hall" + ex);
            return false;
        }
        return true;
    }

    public Hall viewHall(String text) throws SQLException {
        Hall hall = null;
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT* FROM hall where hall_id=" + text);
            rs = psmnt.executeQuery();
            while (rs.next()) {
                System.out.println("found");
                hall = new Hall();
                hall.setHallID(rs.getString(1));
                hall.setHallName(rs.getString(2));
                hall.setCapacity(rs.getInt(3));
                hall.setType(rs.getString(4));
                hall.setNoOfRooms(rs.getInt(5));
            }
        } catch (Exception ex) {
            System.out.println("Exception in showing payments" + ex);
        }
        connection.close();
        return hall;
    }

    public void updateHall(Hall hall) throws SQLException {
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("update hall SET hall_name=?,capacity=?,hall_type=?,numberOfRooms=?  where hall_id=?");
            psmnt.setString(1, hall.getHallName());
            psmnt.setInt(2, hall.getCapacity());
            psmnt.setString(3, hall.getType());
            psmnt.setInt(4, hall.getNoOfRooms());
            psmnt.setString(5, hall.getHallID());
            psmnt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Do update hall" + ex);
        }
        connection.close();
    }

    public ArrayList<Room> viewHallRooms(String text) throws SQLException {
        ArrayList<Room> room = new ArrayList<Room>();
        Room temp;
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT* FROM room where hall_id=" + text);
            rs = psmnt.executeQuery();
            while (rs.next()) {
                System.out.println("found");
                temp = new Room();
                temp.setRoomNumber(rs.getInt(1));
                temp.setType(rs.getString(2));
                temp.setCost(rs.getDouble(3));
                temp.setHallID(rs.getString(4));
                temp.setFull(rs.getBoolean(5));
                room.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exception in showing rooms" + ex);
        }
        connection.close();
        return room;
    }

    public Room viewSingleRoom(String text, String text0) throws SQLException {
        Room temp = null;
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("SELECT* FROM room where hall_id=" + text + "&& room_number=" + Integer.parseInt(text0));
            rs = psmnt.executeQuery();
            while (rs.next()) {
                System.out.println("found");
                temp = new Room();
                temp.setRoomNumber(rs.getInt(1));
                temp.setType(rs.getString(2));
                temp.setCost(rs.getDouble(3));
                temp.setHallID(rs.getString(4));
                temp.setFull(rs.getBoolean(5));

            }
        } catch (Exception ex) {
            System.out.println("Exception in showing rooms" + ex);
        }
        connection.close();
        return temp;
    }

    public void updateSingleRoom(Room room) throws SQLException {
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("update room SET room_type=?,cost=?,isFull=?  where hall_id=? && room_number=?");
            psmnt.setString(1, room.getType());
            psmnt.setDouble(2, room.getCost());
            psmnt.setBoolean(3, room.isFull());
            psmnt.setString(4, room.getHallID());
            psmnt.setInt(5, room.getRoomNumber());
            psmnt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Do update room" + ex);
        }
        connection.close();
    }

    public void updateRooms(ArrayList<Room> room) throws SQLException {
        try {
            createDBConnection();
            psmnt = connection.prepareStatement("update room SET room_type=?,cost=?,isFull=?  where hall_id=? && room_number=?");
            for (int i = 0; i < room.size(); i++) {
                psmnt.setString(1, room.get(i).getType());
                psmnt.setDouble(2, room.get(i).getCost());
                psmnt.setBoolean(3, room.get(i).isFull());
                psmnt.setString(4, room.get(i).getHallID());
                psmnt.setInt(5, room.get(i).getRoomNumber());
                psmnt.executeUpdate();

            }
        } catch (Exception ex) {
            System.out.println("Do update room" + ex);
        }
        connection.close();
    }

    public void addSingleRoom(Room room) throws SQLException {
        try {
            createDBConnection();

            psmnt = connection.prepareStatement("INSERT INTO room ( room_number,room_type,cost,hall_id,isFull)" + "values(?,?,?,?,?)");
            psmnt.setInt(1, room.getRoomNumber());
            psmnt.setString(2, room.getType());
            psmnt.setDouble(3, room.getCost());
            psmnt.setString(4, room.getHallID());
            psmnt.setBoolean(5, room.isFull());
            psmnt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Adding rooms" + ex);
        }
        connection.close();
    }

    public ArrayList<HallStudentAllocations> viewStudentHallAllocations(String text) throws SQLException {
        String statment;
        ArrayList<HallStudentAllocations> allocate = new ArrayList<HallStudentAllocations>();
        HallStudentAllocations temp;
        try {
            createDBConnection();
            statment = "SELECT lives_in.room_number,student.student_id,student.student_first_name,student.gender,student.semester,student.department FROM lives_in,student WHERE lives_in.student_id=student.student_id && lives_in.hall_id=?";
            psmnt = connection.prepareStatement(statment);
            psmnt.setString(1, text);
            rs = psmnt.executeQuery();
            while (rs.next()) {
                System.out.println("find");
                temp = new HallStudentAllocations();
                temp.setRoom_number(rs.getInt(1));
                temp.setStident_id(rs.getString(2));
                temp.setStudent_name(rs.getString(3));
                temp.setGender(rs.getString(4));
                temp.setSemester(rs.getString(5));
                temp.setDepartment(rs.getString(6));
                allocate.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exception in showing studebt allocations" + ex);
        }
        connection.close();
        return allocate;
    }
       public ArrayList<Hall> viewHalls() throws SQLException {
        String statment;
        ArrayList<Hall> hall = new ArrayList<Hall>();
        Hall temp;
        try {
            createDBConnection();
            statment = "SELECT* from hall";
            psmnt = connection.prepareStatement(statment);
            rs = psmnt.executeQuery();
            while (rs.next()) {
                System.out.println("find");
                temp = new Hall();
                temp.setHallID(rs.getString(1));
                hall.add(temp);
            }
        } catch (Exception ex) {
            System.out.println("Exception in showing studebt allocations" + ex);
        }
        connection.close();
        return hall ;
    }
}
