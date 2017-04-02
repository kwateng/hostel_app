/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DatabaseAccess;

import entities.Payment;
import entities.Student;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import Models.ViewPayments;
import Models.studentHallAranngements;

/**
 *
 * @author Kavindya
 */
public class StudentDataHandler {
    private Connection connection;
    private final String connectionURL = "jdbc:mysql://localhost:3306/universityOfSumanadasa_hallManagement";
    ResultSet rs = null;
    PreparedStatement psmnt = null;
    private FileInputStream fis;
    private Student studentinfo;
    public boolean addStudent(Student info) throws SQLException
    {
   try {
   createDBConnection();
   Image image = info.getiImage().getImage();
   RenderedImage rendered = null;
   if (image instanceof RenderedImage)
   {
    rendered = (RenderedImage)image;
   }
   else
   {
    BufferedImage buffered = new BufferedImage(
        info.getiImage().getIconWidth(),
        info.getiImage().getIconHeight(),
        BufferedImage.TYPE_INT_RGB
    );
    Graphics2D g = buffered.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    rendered = buffered;
   }
    ImageIO.write(rendered, "JPEG", new File("image.jpg"));
    File image1 = new File("image.jpg");
    psmnt = connection.prepareStatement("INSERT INTO student (student_id,student_first_name,student_last_name,student_nameWithInitials,department,semester,academic_year,telephone_mobile,telephone_home, dateOfBirth, password,image, gender, registerDate)"+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    psmnt.setString(1,info.getIndexNo());
    psmnt.setString(2,info.getFirstName());
    psmnt.setString(3,info.getLastName());
    psmnt.setString(4,info.getNameWithInitials());
    psmnt.setString(5,info.getDepartment());
    psmnt.setString(6,info.getSemester());
    psmnt.setString(7,info.getAcademicYear());
    psmnt.setString(8,info.getPersonalMobile());
    psmnt.setString(9,info.getHomeLine());
    psmnt.setString(10,info.getDateOfBirth());
    psmnt.setString(11,info.getPassword());
    psmnt.setString(13,info.getGender());
    psmnt.setString(14,info.getRegDate());

    fis = new FileInputStream(image1);
    psmnt.setBinaryStream(12,fis,fis.available());
    int s = psmnt.executeUpdate();
    if(s>0) {
    System.out.println("Uploaded successfully !");
    return true;
            }
    else {
    System.out.println("unsucessfull to upload in adding student");
         }
    psmnt.close();
  }
  catch (Exception ex) {
  System.out.println("Found some error : adding student "+ex);
  return false;
  }
  finally {
  connection.close();
  }
  return false;
}
    public Student showStudent(String index) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException
    {
   try {
   createDBConnection();
   psmnt = connection.prepareStatement("SELECT * from student WHERE student_id=?");
   psmnt.setString(1,index);
   rs = psmnt.executeQuery();
   while (rs.next()){
       studentinfo=new Student();
       studentinfo.setIndexNo(index);
       studentinfo.setFirstName(rs.getString(2));
       studentinfo.setLastName(rs.getString(3));
       studentinfo.setNameWithInitials(rs.getString(4));
       studentinfo.setDepartment(rs.getString(5));
       studentinfo.setPassword(rs.getString(11));
       studentinfo.setSemester(rs.getString(6));
       studentinfo.setAcademicYear(rs.getString(7));
       studentinfo.setPersonalMobile(rs.getString(8));
       studentinfo.setHomeLine(rs.getString(9));
       studentinfo.setDateOfBirth(rs.getString(10));
       studentinfo.setGender(rs.getString(13));
       studentinfo.setRegDate(rs.getString(14));
       InputStream is = rs.getBinaryStream("image");
       BufferedImage image = ImageIO.read(is); //the image is read in
       ImageIO.write(image, "jpg", new FileOutputStream("recived.jpg"));
       ImageIcon       icon=new ImageIcon("recived.jpg");
       studentinfo.setiImage(icon);
        }
        }
        catch (Exception ex) {
        System.out.println("Exception in showing students"+ex);
                         }
        return studentinfo;
        }
        private void createDBConnection() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
        {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, "root", "");
        }
        private void terminateDBConnection() throws SQLException
        {
        connection.close();
        }
        public boolean removeStudent(String index) throws SQLException {
            boolean status = false;
        try
        {
        // Load JDBC driver "com.mysql.jdbc.Driver"
             createDBConnection();
             psmnt = connection.prepareStatement("SELECT * from student WHERE student_id=?");
             psmnt.setString(1,index);
             rs = psmnt.executeQuery();
             if(rs!=null){
             java.sql.Statement statement = connection.createStatement();
             statement.executeUpdate("delete from student where student_id='"+index+"'");
             status=true;
            }
             else
             {
                 status=false;
             }
        }
        catch (Exception ex) {
        System.out.println("Exception in removing student"+ex);
                       }
        finally
        {
       connection.close();
        }
        return status;
    }
   public void updateStudent(Student info) throws SQLException
    {
   try {
   createDBConnection();
   Image image = info.getiImage().getImage();
   RenderedImage rendered = null;
   if (image instanceof RenderedImage)
   {
    rendered = (RenderedImage)image;
   }
   else
   {
    BufferedImage buffered = new BufferedImage(
        info.getiImage().getIconWidth(),
        info.getiImage().getIconHeight(),
        BufferedImage.TYPE_INT_RGB
    );
    Graphics2D g = buffered.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    rendered = buffered;
   }
    ImageIO.write(rendered, "JPEG", new File("image.jpg"));
    File image1 = new File("image.jpg");
    psmnt = connection.prepareStatement("update student SET " +
        "student_first_name = ?, student_last_name = ?, student_nameWithInitials = ?, " +
        "telephone_mobile = ?, telephone_home= ?, password = ?,image=? where student_id = ?");
    psmnt.setString(1,info.getFirstName());
    psmnt.setString(2,info.getLastName());
    psmnt.setString(3,info.getNameWithInitials());
    psmnt.setString(4,info.getPersonalMobile());
    psmnt.setString(5,info.getHomeLine());
    psmnt.setString(6,info.getPassword());
    fis = new FileInputStream(image1);

    psmnt.setBinaryStream(7,fis,fis.available());
    psmnt.setString(8, info.getIndexNo());
    int s = psmnt.executeUpdate();
    if(s>0) {
    System.out.println("Uploaded successfully !");
            }
    else {
    System.out.println("unsucessfull to upload in adding student");
         }
    psmnt.close();
  }
  catch (Exception ex) {
  System.out.println("Found some error : adding student "+ex);
  }
  finally {
  connection.close();
  }
}


    public ArrayList<ViewPayments> getstudentDuePayments(String text) {
      ArrayList<ViewPayments> payments = new ArrayList<ViewPayments>();
      ViewPayments view;
         try {
   createDBConnection();
   psmnt = connection.prepareStatement("SELECT  academic_year,balance from lives_in WHERE student_id=? && isPaid=false");
   psmnt.setString(1,text);
   rs = psmnt.executeQuery();
   while (rs.next()){
       view=new ViewPayments();
       view.setAcademicYear(rs.getString(1));
       view.setDueAmonunt(rs.getDouble(2));
       payments.add(view);
        }
        }
        catch (Exception ex) {
        System.out.println("Exception in showing students"+ex);
                         }
      return payments;


    }

    public ArrayList<Payment> getHistoryPayments(String text) {
        ArrayList<Payment> payments = new ArrayList<Payment>();
        Payment view;
         try {
   createDBConnection();
   psmnt = connection.prepareStatement("SELECT* FROM payment WHERE student_id=?");
   psmnt.setString(1,text);
   rs = psmnt.executeQuery();
             System.out.println(text);
   while (rs.next()){
       System.out.println("found");
       view=new Payment();
       view.setStudentId(text);
       view.setPaymentId(rs.getString(1));
       view.setAcademicYear(rs.getString(2));
       view.setAmount(rs.getDouble(3));
       payments.add(view);
        }
        }
        catch (Exception ex) {
        System.out.println("Exception in showing students"+ex);
                         }
      return payments;
    }

    public studentHallAranngements getStudentHallArangments(String text) {
       studentHallAranngements ob = null;
       String statement;
        System.out.println(text);
       statement="SELECT hall.hall_id,hall.hall_type,hall.hall_name,hall.capacity,hall.numberOfRooms,room.room_number,room.room_type,room.cost FROM lives_in,hall,room WHERE lives_in.hall_id=hall.hall_id && lives_in.room_number=room.room_number && lives_in.student_id=?";
        try {
       createDBConnection();
        psmnt = connection.prepareStatement(statement);
        psmnt.setString(1,text);
        rs = psmnt.executeQuery();
             System.out.println(text);
       while (rs.next()){
       System.out.println("found");
       ob=new studentHallAranngements();
       ob.setHall_id(rs.getString(1));
       ob.setHall_type(rs.getString(2));
       ob.setHall_name(rs.getString(3));
       ob.setHall_capacity(rs.getString(4));
       ob.setHall_noOfRooms(rs.getString(5));
       ob.setRomm_no(rs.getString(6));
       ob.setRoom_type(rs.getString(6));
       ob.setRoom_cost(rs.getString(8));
        }
        }
        catch (Exception ex)
        {
        System.out.println("Exception in showing students"+ex);
        }
      return ob;
    }



}
